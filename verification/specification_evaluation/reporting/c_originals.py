"""Promote matching completed C original reruns into an existing experiment."""
from __future__ import annotations

import argparse
from collections import Counter
from datetime import datetime, timezone
from pathlib import Path
import shutil

from verification.specification_evaluation.manifest import load_population, sha256
from verification.specification_evaluation.workflow import read_json, write_json, summarize

PROGRAMS = ('MoveFirst', 'MultiplyElements', 'NextPowerOf2', 'PairWise')


def integrate(output: Path, rerun: Path):
    config = read_json(output / 'run.json')
    diagnostic = read_json(rerun / 'summary.json')
    assert diagnostic['complete']
    assert set(diagnostic['cases']) == {p + '/original' for p in PROGRAMS}
    assert all(r.get('task_generation') == 'passed' and not r.get('failure_stage')
               and not r.get('omitted_annotations') for r in diagnostic['results'])
    assert diagnostic['goal_timeout'] == config['settings']['goal_timeout']
    assert diagnostic['case_timeout'] == config['settings']['timeout']
    assert diagnostic['selection_manifest_sha256'] == config['selection_manifest_sha256']
    population = load_population(Path(config['selection_manifest']),
                                 Path(config['java_originals']), Path(config['c_originals']))
    archive = output / 'original_verification' / rerun.name
    archive.mkdir(parents=True, exist_ok=True)
    snapshots = output / 'original_verification' / 'source_records'
    snapshots.mkdir(exist_ok=True)
    results = []
    for program in PROGRAMS:
        relative = Path('cases') / program / 'original' / 'c'
        source = rerun / relative
        target = output / relative
        previous = read_json(target / 'record.json')
        record = read_json(source / 'record.json')
        assert record['attempt_status'] == 'complete' and record['verification_started']
        assert record['role'] == 'original' and record['program'] == program
        for key in ('raw_source_sha256', 'frozen_spec_sha256', 'annotated_source_sha256',
                    'selection_manifest_sha256', 'settings', 'support_sha256', 'input_fingerprint'):
            assert record[key] == previous[key], (program, key)
        assert record['settings'] == config['settings']
        assert record['support_sha256'] == config['jarray_support_sha256']
        assert record['verifier']['sha256'] == config['verifiers']['c']['sha256']
        assert sha256(population.original(program, 'c')) == record['raw_source_sha256']
        assert sha256(output / 'frozen_specs/c' / (program + '.json')) == record['frozen_spec_sha256']
        assert sha256(source / (program + '.c')) == sha256(target / (program + '.c')) == record['annotated_source_sha256']
        existing=previous.get('verification_integration',{})
        old_hash=(existing['previous_record_sha256']
                  if existing.get('source_record_sha256')==sha256(source / 'record.json')
                  else sha256(target / 'record.json'))
        prior_snapshot = snapshots / (old_hash + '.json')
        if not prior_snapshot.exists():
            shutil.copy2(target / 'record.json', prior_snapshot)
        assert sha256(prior_snapshot) == old_hash
        archived = archive / relative
        archived.mkdir(parents=True, exist_ok=True)
        names = ('record.json', 'wp-report.json')
        for name in names:
            if (source / name).resolve() != (archived / name).resolve():
                shutil.copy2(source / name, archived / name)
            assert sha256(source / name) == sha256(archived / name)
        provenance = {'rerun': rerun.name,
                      'source_record': (archived / 'record.json').relative_to(output).as_posix(),
                      'source_record_sha256': sha256(source / 'record.json'),
                      'previous_record': prior_snapshot.relative_to(output).as_posix(),
                      'previous_record_sha256': old_hash,
                      'invocation_paths_preserved': True}
        if previous.get('migration_provenance'):
            record['migration_provenance'] = previous['migration_provenance']
        record['annotated_source'] = str(target / (program + '.c'))
        record['verification_integration'] = provenance
        # Commands and goal locations retain the paths actually used by Frama-C.
        for name in ('wp-report.json',):
            shutil.copy2(source / name, target / name)
        write_json(target / 'record.json', record)
        results.append({'program': program, 'outcome': record['outcome'],
                        'elapsed_seconds': record['elapsed_seconds'],
                        'goals': dict(Counter(g['state'] for g in record['goals'])),
                        'timed_out': record['timed_out'],
                        'raw_source_sha256': record['raw_source_sha256'],
                        'frozen_spec_sha256': record['frozen_spec_sha256'],
                        'annotated_source_sha256': record['annotated_source_sha256'],
                        'record': (target / 'record.json').relative_to(output).as_posix(),
                        'record_sha256': sha256(target / 'record.json'), **provenance})
    for name in ('summary.json', 'transfer_preflight.json'):
        if (rerun / name).resolve() != (archive / name).resolve():
            shutil.copy2(rerun / name, archive / name)
        assert sha256(rerun / name) == sha256(archive / name)
    integration = {'complete': True, 'passed': True,
                   'generated_utc': datetime.now(timezone.utc).isoformat(),
                   'rerun': rerun.name, 'summary': (archive / 'summary.json').relative_to(output).as_posix(),
                   'source_summary_sha256': sha256(rerun / 'summary.json'),
                   'settings_match': True, 'results': results,
                   'interpretation': 'Four completed C original WP invocations promoted after matching source, frozen contract, settings, support and verifier hashes. Mutant WP invocations were not rerun.'}
    write_json(output / 'original_verification/integration.json', integration)
    old_summary = read_json(output / 'summary.json')
    summary = summarize(output, population)
    for key in ('c_counterexample_evidence', 'c_run_archives'):
        if key in old_summary:
            summary[key] = old_summary[key]
    summary['c_original_verification_integration'] = integration
    write_json(output / 'summary.json', summary)
    return integration


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output', type=Path, required=True)
    parser.add_argument('--rerun', type=Path, required=True)
    args = parser.parse_args()
    result = integrate(args.output.resolve(), args.rerun.resolve())
    print({r['program']: {'outcome': r['outcome'], 'goals': r['goals']} for r in result['results']})


if __name__ == '__main__':
    main()
