"""Regression checks for input-level classification and reconstruction."""
import json
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch
from .input_completeness import PRIMARY, classify, reconstruct, statistics, strict_verdict, strict_summary


def java(value):
    return {'inputs': {'x': 1}, 'preconditions_satisfied': True,
            'observation': {'status': 'returned', 'category': 'returned', 'return': value}}


def c(value, failed=(), nested=False):
    run = {'status': 'returned', 'observation': {'result': value}}
    trial = {'inputs': {'x': 1}, 'preconditions_satisfied': True,
             'failed_clauses': list(failed), 'validated': True}
    trial.update({'observation': run} if nested else run)
    return trial


def saved_java_runtime(root, mutant_rows):
    lang = root / 'java'
    (lang / 'inputs').mkdir(parents=True)
    (lang / 'summary.json').write_text(json.dumps({'per_program': [{'program': 'Example', 'selected_mutants': 1}]}))
    pool = [{'inputs': {'x': x}, 'preconditions_satisfied': True} for x in (1, 2)]
    (lang / 'inputs/Example.json').write_text(json.dumps({'candidates': pool}))
    for role, rows in [('original', [java(1), java(2)]), ('mutant_1', mutant_rows)]:
        directory = lang / 'cases/Example' / role / 'java'
        directory.mkdir(parents=True)
        (directory / 'record.json').write_text('{}')
        for row, candidate in zip(rows, pool):
            row['inputs'] = candidate['inputs']
        (directory / 'trials.jsonl').write_text(''.join(json.dumps(row)+'\n' for row in rows))


class InputCompletenessChecks(unittest.TestCase):
    def test_same_mutant_rejects_one_fault_but_accepts_another(self):
        rejected = java(2)
        rejected['observation'] = {'status': 'failure', 'category': 'postcondition_violation'}
        rejected['audit'] = {'fresh_interpreted_rac': {'category': 'postcondition_violation'},
                             'original_passed': True,
                             'uninstrumented_mutant': {'status': 'returned', 'return': 2}}
        self.assertEqual(classify('java', java(1), rejected)['classification'], 'different_output_rejected')
        self.assertEqual(classify('java', java(1), java(3))['classification'], 'different_output_accepted')
        self.assertEqual(statistics({'different_output_rejected': 1, 'different_output_accepted': 1})['observed_rejection_rate'], .5)

    def test_strict_verdict_requires_every_faulty_output(self):
        self.assertEqual(strict_verdict({'different_output_rejected': 99, 'different_output_accepted': 1}), 'failed')
        self.assertEqual(strict_verdict({'different_output_rejected': 99, 'missing_mutant_trial': 1}), 'incomplete')
        self.assertEqual(strict_verdict({'different_output_rejected': 99, 'same_output': 5}), 'passed')
        self.assertEqual(strict_verdict({'same_output': 5}), 'no_differing_returned_output')
        self.assertIsNone(strict_summary([{'strict_verdict': 'passed'}, {'strict_verdict': 'incomplete'}])['strict_mutant_rejection_rate'])

    def test_primary_runner_fails_mutant_that_rejects_only_one_faulty_output(self):
        from .completeness import run_completeness
        from . import java_completeness, c_completeness
        rejected = java(3)
        rejected['observation'] = {'status': 'failure', 'category': 'postcondition_violation'}
        rejected['audit'] = {'fresh_interpreted_rac': {'category': 'postcondition_violation'},
                             'original_passed': True,
                             'uninstrumented_mutant': {'status': 'returned', 'return': 3}}
        with tempfile.TemporaryDirectory() as folder:
            root = Path(folder); output = root / 'primary'
            def execute(study, runtime, *args, **kwargs):
                self.assertTrue(kwargs['exhaustive'])
                self.assertTrue(kwargs['omit_fresh'])
                saved_java_runtime(runtime.parent, [rejected, java(3)])
            with patch.object(java_completeness, 'run_java', side_effect=execute) as run_java, \
                 patch.object(c_completeness, 'run_c') as run_c:
                result = run_completeness(root / 'study', output, languages=('java',), workers=2)
            run_java.assert_called_once(); run_c.assert_not_called()
            self.assertTrue(result['complete'])
            self.assertEqual(result['languages'][0]['strict_mutant_verdicts'], {'failed': 1})
            self.assertEqual(result['languages'][0]['strict_mutant_rejection_rate'], 0)
            self.assertEqual(json.loads((output / 'summary.json').read_text()), result)

    def test_primary_collection_withholds_rate_for_missing_inputs(self):
        from .completeness import collect, runtime_directory
        from .input_completeness import DEFAULT
        with tempfile.TemporaryDirectory() as folder:
            root = Path(folder); runtime = root / 'runtime'
            saved_java_runtime(runtime, [java(1)])
            result = collect(root / 'primary', runtime)
            self.assertFalse(result['complete'])
            self.assertIsNone(result['languages'][0]['strict_mutant_rejection_rate'])
        with self.assertRaises(ValueError):
            runtime_directory(DEFAULT)

    def test_workflow_completion_requires_full_strict_coverage(self):
        from types import SimpleNamespace
        from verification.specification_evaluation.workflow import summarize
        population = SimpleNamespace(programs=('Example',), pairs=[])
        with tempfile.TemporaryDirectory() as folder:
            output = Path(folder)
            for language in ('java', 'c'):
                directory = output / 'cases/Example/original' / language
                directory.mkdir(parents=True)
                (directory / 'record.json').write_text(json.dumps({'outcome': 'proved'}))
            runtime = output / 'mutant_detection/summary.json'
            runtime.parent.mkdir()
            runtime.write_text(json.dumps({'full_input_evaluation_complete': False}))
            self.assertFalse(summarize(output, population)['complete'])
            runtime.write_text(json.dumps({'full_input_evaluation_complete': True}))
            self.assertTrue(summarize(output, population)['complete'])

    def test_missing_inputs_not_counted_as_rejections(self):
        self.assertEqual(classify('java', java(1), None)['classification'], 'missing_mutant_trial')
        stats = statistics({'same_output': 1, 'missing_mutant_trial': 3})
        self.assertIsNone(stats['observed_rejection_rate'])
        self.assertFalse(stats['all_input_pairs_resolved'])

    def test_arrays_compare_contents(self):
        self.assertEqual(classify('java', java([1, 2]), java([1, 2]))['classification'], 'same_output')
        self.assertEqual(classify('java', java([1, 2]), java([2, 1]))['classification'], 'different_output_accepted')

    def test_both_c_archive_schemas(self):
        for nested in (False, True):
            self.assertEqual(classify('c', c(1), c(2, [PRIMARY], nested))['classification'], 'different_output_rejected')
            self.assertEqual(classify('c', c(1), c(2, nested=nested))['classification'], 'different_output_accepted')

    def test_unvalidated_failure_is_unresolved(self):
        mutant = c(2, [PRIMARY]); mutant['validated'] = False
        self.assertEqual(classify('c', c(1), mutant)['classification'], 'different_output_unresolved')

    def test_safety_and_original_failure_are_separate(self):
        mutant = java(2); mutant['observation'] = {'status': 'failure', 'category': 'runtime_safety_failure'}
        self.assertEqual(classify('java', java(1), mutant)['classification'], 'safety_failure')
        self.assertEqual(classify('java', mutant, java(2))['classification'], 'original_not_passing')

    def test_exhaustive_collector_does_not_stop_after_first_violation(self):
        from . import c_completeness as collector
        with tempfile.TemporaryDirectory() as folder:
            root = Path(folder); raw = root / 'Example.c'; raw.write_text('int example(void){return 2;}')
            pool = [{'inputs': {'x': i}, 'preconditions_satisfied': True} for i in (1, 2)]
            baseline = {'status': 'original_passed_all_inputs', 'search_binary': 'original'}
            def execute(binary, program, inputs, timeout):
                return {'status': 'returned', 'observation': {'result': 1 if str(binary) == 'original' else 2}}
            def violations(program, inputs, observation):
                return [PRIMARY] if observation['result'] == 2 else []
            with patch.object(collector, 'read_json', return_value={}), \
                 patch.object(collector, 'matches_frozen_contract', return_value=True), \
                 patch.object(collector, 'native_harness', return_value=''), \
                 patch.object(collector, 'compile_native', return_value=Path('mutant')), \
                 patch.object(collector, 'execute', side_effect=execute), \
                 patch.object(collector, 'violations', side_effect=violations):
                record = collector.case(root, root / 'out', 'Example', '1', raw, pool, {}, baseline, exhaustive=True)
            self.assertTrue(record['specification_detected'])
            self.assertEqual(record['checked_inputs'], 2)
            self.assertTrue(record['search_exhausted'])

    def test_reconstruction_keeps_missing_pair_and_evidence(self):
        with tempfile.TemporaryDirectory() as folder:
            root = Path(folder); source = root / 'source'; output = root / 'rebuilt'
            lang = source / 'java'; (lang / 'inputs').mkdir(parents=True)
            (lang / 'summary.json').write_text(json.dumps({'per_program': [{'program': 'Example', 'selected_mutants': 1}]}))
            pool = [{'inputs': {'x': x}, 'preconditions_satisfied': True} for x in (1, 2)]
            (lang / 'inputs/Example.json').write_text(json.dumps({'candidates': pool}))
            for role in ('original', 'mutant_1'):
                directory = lang / 'cases/Example' / role / 'java'; directory.mkdir(parents=True)
                (directory / 'record.json').write_text('{}')
                rows = [java(1), java(2)] if role == 'original' else [java(3)]
                for row, candidate in zip(rows, pool): row['inputs'] = candidate['inputs']
                (directory / 'trials.jsonl').write_text(''.join(json.dumps(row)+'\n' for row in rows))
            result = reconstruct(source, output)
            self.assertFalse(result['full_input_evaluation_complete'])
            self.assertEqual(result['languages'][0]['counts'], {'different_output_accepted': 1, 'missing_mutant_trial': 1})
            rows = [json.loads(line) for line in (output / 'java/Example.jsonl').read_text().splitlines()]
            self.assertTrue(rows[0]['mutant_evidence'].endswith(':1'))
            self.assertIsNone(rows[1]['mutant_evidence'])


if __name__ == '__main__':
    unittest.main()
