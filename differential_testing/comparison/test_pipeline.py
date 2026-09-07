"""Regression coverage for evidence collection and manual reclassification."""

import json
import shutil
import subprocess
import tempfile
import unittest
from copy import deepcopy
from pathlib import Path
from unittest.mock import patch

from differential_testing.comparison.extract_exception_mismatches import (
    extract_exception_mismatches,
)
from differential_testing.comparison.merge_sanitizer_results import (
    merge_sanitizer_assessments,
)
from differential_testing.comparison.result_comparator import compare_execution_results
from differential_testing.comparison.run_comparison import main
from differential_testing.comparison.sanitizer_report import build_sanitizer_report
from differential_testing.execution.run_c_sanitizer import (
    run_sanitized_test,
    run_sanitizer_checks,
)

INPUTS = {
    'kind': 'evosuite_test_inputs',
    'tests': [{'id': 'Demo.test0', 'fixtures': [], 'steps': [
        {'id': 'call_0', 'class': 'Demo', 'function': 'run',
         'arguments': [{'type': 'int32', 'value': 3}],
         'result': {'id': 'value', 'type': 'int32'}},
    ]}],
}


def fixture():
    java = {'kind': 'test_execution_results', 'language': 'java', 'tests': [
        {'id': 'Demo.test0', 'steps': [{'id': 'call_0', 'status': 'error',
         'error': {'kind': 'arbitrary_error', 'message': 'original Java diagnostic'}}]},
    ]}
    c = deepcopy(java)
    c['language'] = 'c'
    c['tests'][0]['steps'][0] = {
        'id': 'call_0', 'status': 'returned',
        'return': {'type': 'int32', 'value': 1508}, 'state_after': [],
    }
    raw = compare_execution_results(INPUTS, java, c)
    cases = extract_exception_mismatches(raw, java, c, INPUTS)
    evidence = [{'test': 'Demo.test0', 'step': 'call_0', 'status': 'executed',
                 'exit_code': -6, 'stdout': '', 'stderr': 'raw sanitizer diagnostic'}]
    return raw, cases, build_sanitizer_report(raw, cases, evidence), java, c


class ComparisonPipelineTest(unittest.TestCase):
    def test_extracts_any_java_error_and_retains_input_references(self):
        raw, cases, report, java, c = fixture()
        self.assertEqual(len(cases), 1)
        self.assertEqual(cases[0]['java']['error']['kind'], 'arbitrary_error')
        self.assertEqual(cases[0]['arguments'], INPUTS['tests'][0]['steps'][0]['arguments'])
        self.assertEqual(cases[0]['input_test'], INPUTS['tests'][0])
        java['tests'][0]['steps'][0] = deepcopy(c['tests'][0]['steps'][0])
        java['tests'][0]['steps'][0]['return']['value'] = 42
        raw = compare_execution_results(INPUTS, java, c)
        self.assertEqual(extract_exception_mismatches(raw, java, c, INPUTS), [])

    def test_unreviewed_evidence_never_changes_mismatch(self):
        raw, _, report, _, _ = fixture()
        final = merge_sanitizer_assessments(raw, report)
        self.assertEqual(final['tests'][0]['status'], 'mismatch')
        self.assertFalse(final['summary']['overall_equivalent'])

    def test_manual_statuses_and_raw_preservation(self):
        for status in ('exception_equivalent', 'mismatch', 'unclear'):
            with self.subTest(status=status):
                raw, _, report, _, _ = fixture()
                original = deepcopy(raw)
                report['cases'][0]['assessment'] = {'status': status, 'reason': 'Reviewed source and raw diagnostics.'}
                final = merge_sanitizer_assessments(raw, report)
                self.assertEqual(raw, original)
                self.assertEqual(final['tests'][0]['status'], status)
                step = final['tests'][0]['steps'][0]
                self.assertEqual(step['original_comparison'], raw['tests'][0]['steps'][0])
                self.assertEqual(step['sanitizer_evidence']['c_normal']['return']['value'], 1508)
                self.assertFalse(final['summary']['overall_match'])
                self.assertEqual(final['summary']['overall_equivalent'], status == 'exception_equivalent')
                self.assertEqual(final['summary']['mismatched_steps'], int(status == 'mismatch'))

    def test_rejects_invalid_and_stale_assessments(self):
        for assessment in ({'status': 'match', 'reason': 'No'}, {'status': 'unclear', 'reason': ''}):
            raw, _, report, _, _ = fixture()
            report['cases'][0]['assessment'] = assessment
            with self.assertRaises(ValueError):
                merge_sanitizer_assessments(raw, report)
        raw, _, report, _, _ = fixture()
        raw['summary']['extra_c_tests'] = ['unexpected']
        with self.assertRaises(ValueError):
            merge_sanitizer_assessments(raw, report)

    def test_structural_mismatches_survive_assessment(self):
        _, _, _, java, c = fixture()
        c['tests'][0]['steps'].append({'id': 'extra', 'status': 'returned'})
        c['tests'].append({'id': 'extra_test', 'steps': []})
        inputs = deepcopy(INPUTS)
        inputs['tests'].append({'id': 'missing', 'steps': [{'id': 'missing_call'}]})
        raw = compare_execution_results(inputs, java, c)
        cases = extract_exception_mismatches(raw, java, c, inputs)
        report = build_sanitizer_report(raw, cases, [
            {'test': 'Demo.test0', 'step': 'call_0', 'status': 'executed'},
        ])
        report['cases'][0]['assessment'] = {'status': 'exception_equivalent', 'reason': 'Reviewed.'}
        final = merge_sanitizer_assessments(raw, report)
        self.assertEqual(final['summary']['mismatched_steps'], 1)
        self.assertEqual(final['summary']['mismatched_tests'], 2)
        self.assertFalse(final['summary']['overall_equivalent'])

    def test_failed_compilation_cannot_establish_equivalence(self):
        raw, _, report, _, _ = fixture()
        report['cases'][0]['c_sanitized']['status'] = 'compile_error'
        report['cases'][0]['assessment'] = {'status': 'exception_equivalent', 'reason': 'Reviewed.'}
        with self.assertRaises(ValueError):
            merge_sanitizer_assessments(raw, report)

    def test_timeout_retains_partial_diagnostics(self):
        with patch('subprocess.run', side_effect=subprocess.TimeoutExpired(
            ['runner'], 2, output=b'partial stdout', stderr=b'partial stderr',
        )):
            result = run_sanitized_test(['runner'])
        self.assertTrue(result['timed_out'])
        self.assertIsNone(result['exit_code'])
        self.assertEqual(result['stderr'], 'partial stderr')

    @unittest.skipUnless(shutil.which('clang'), 'clang required')
    def test_prefix_replay_and_recoverable_ubsan_output(self):
        _, cases, _, _, _ = fixture()
        case = cases[0]
        second = deepcopy(case['input_test']['steps'][0])
        second.update(id='call_1', result={'id': 'second', 'type': 'int32'})
        case['input_test']['steps'].append(second)
        case['step'] = 'call_1'
        with tempfile.TemporaryDirectory() as temporary:
            root = Path(temporary)
            (root / 'Demo.c').write_text('int run(int index) { static int count = 0; return ++count; }')
            evidence = run_sanitizer_checks(cases, root, root / 'prefix')[0]
            self.assertEqual(evidence['replayed_steps'], ['call_0', 'call_1'])
            self.assertEqual(json.loads(evidence['stdout'])['return']['value'], 2)
            self.assertEqual(evidence['stderr'], '')
            (root / 'Demo.c').write_text('int run(int index) { volatile int max = 2147483647; return max + index; }')
            evidence = run_sanitizer_checks(cases, root, root / 'ubsan')[0]
            self.assertEqual(evidence['exit_code'], 0)
            self.assertIn('runtime error:', evidence['stderr'])
            self.assertNotIn('assessment', evidence)

    @unittest.skipUnless(shutil.which('clang'), 'clang required')
    def test_real_sanitizer_and_cli_audit_files(self):
        _, cases, _, java, c = fixture()
        with tempfile.TemporaryDirectory() as temporary:
            root = Path(temporary)
            (root / 'Demo.c').write_text('int run(int index) { int values[2] = {1, 2}; return values[index]; }\n')
            evidence = run_sanitizer_checks(cases, root, root / 'build')
            self.assertEqual(evidence[0]['status'], 'executed')
            self.assertNotEqual(evidence[0]['exit_code'], 0)
            self.assertIn('AddressSanitizer', evidence[0]['stderr'])
            self.assertIn('-fsanitize=address,undefined', evidence[0]['compilation']['command'])
            self.assertNotIn('assessment', evidence[0])
            for name, document in (('inputs', INPUTS), ('java', java), ('c', c)):
                (root / f'{name}.json').write_text(json.dumps(document))
            args = ['--inputs', str(root / 'inputs.json'), '--java-results', str(root / 'java.json'),
                    '--c-results', str(root / 'c.json'), '--c-dir', str(root), '--output-dir', str(root / 'out')]
            with self.assertRaises(SystemExit) as result:
                main(args)
            self.assertEqual(result.exception.code, 1)
            raw_path = root / 'out/comparison_raw.json'
            before = raw_path.read_bytes()
            report = json.loads((root / 'out/sanitizer_report.json').read_text())
            self.assertIsNone(report['cases'][0]['assessment'])
            with self.assertRaises(SystemExit) as result:
                main(args)
            self.assertEqual(result.exception.code, 2)
            self.assertEqual(raw_path.read_bytes(), before)
            (root / 'Demo.c').write_text('invalid C code')
            evidence = run_sanitizer_checks(cases, root, root / 'bad_build')
            self.assertEqual(evidence[0]['status'], 'compile_error')
            self.assertTrue(evidence[0]['compilation']['stderr'])


if __name__ == '__main__':
    unittest.main()
