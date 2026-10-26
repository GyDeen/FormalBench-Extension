"""Extract the existing helper bodies and attach fixed contracts without digests."""
from __future__ import annotations
import argparse
import json
import sys
from pathlib import Path

WORKSPACE = Path(__file__).resolve().parents[1]
PROJECT = WORKSPACE.parents[1]
sys.path.insert(0, str(PROJECT))
from verification.specification_evaluation.specifications.annotations import scan, _function_spans

SOURCE_ROOT = PROJECT / 'FormalBench-data/FilteredData/translated_c/seed_726_per_category_10_653ade686f'
HELPERS = ('java_add', 'java_sub', 'java_mul', 'java_div', 'java_abs', 'java_min', 'java_max', 'java_shl')
CONTRACT = WORKSPACE / 'contracts/int32.acsl.h'


def helpers(source):
    tokens, annotations = scan(source)
    functions = []
    for name, start, body, end in _function_spans(tokens, source):
        if not name.startswith('java_'):
            continue
        if name not in HELPERS:
            raise ValueError(f'Unsupported Java helper: {name}')
        functions.append({'name': name, 'start': tokens[start].start,
                          'end': tokens[end].start + len(tokens[end].text),
                          'boundary': start, 'tokens': [v.text for v in tokens[start:end+1]]})
    return functions, annotations


def inventory(source_root=SOURCE_ROOT):
    definitions, usages, programs = {}, {name: [] for name in HELPERS}, {}
    sources = sorted(Path(source_root).glob('*.c'))
    if not sources:
        raise ValueError(f'No translated C originals in {source_root}')
    for path in sources:
        source = path.read_text(encoding='utf-8')
        functions, _ = helpers(source)
        programs[path.stem] = [function['name'] for function in functions]
        for function in functions:
            name = function['name']
            if name in definitions and definitions[name]['tokens'] != function['tokens']:
                raise ValueError(f'Helper implementation differs between originals: {name} in {path}')
            if name not in definitions:
                definitions[name] = {'source': path.relative_to(PROJECT).as_posix(),
                                     'body': source[function['start']:function['end']],
                                     'tokens': function['tokens']}
            usages[name].append(path.stem)
    if set(definitions) != set(HELPERS):
        raise ValueError(f'Missing helpers: {sorted(set(HELPERS)-set(definitions))}')
    manifest = {'schema_version': '1.0', 'originals': len(sources),
                'originals_with_helpers': sum(bool(v) for v in programs.values()),
                'helpers': {name: {'source': definitions[name]['source'],
                                  'original_count': len(usages[name]), 'programs': usages[name]}
                            for name in HELPERS}, 'per_program': programs,
                'comparison': 'Exact executable token lists; comments and whitespace ignored'}
    return definitions, manifest


def prepare(check=False, source_root=SOURCE_ROOT):
    definitions, manifest = inventory(source_root)
    implementation = '#include "../contracts/int32.acsl.h"\n\n'
    implementation += '\n\n'.join(definitions[name]['body'] for name in HELPERS) + '\n'
    outputs = {'helpers.c': implementation, 'manifest.json': json.dumps(manifest, indent=2)+'\n'}
    for name, content in outputs.items():
        target = WORKSPACE / 'generated' / name
        if check:
            if not target.is_file() or target.read_text(encoding='utf-8') != content:
                raise ValueError(f'Generated support differs; run prepare.py first: {target}')
        else:
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_text(content, encoding='utf-8')
    return manifest


def attach(source, definitions):
    import re
    blocks = {name: block for block, name in re.findall(
        r'(/\*@.*?\*/)\s*static int32_t (java_\w+)\(', CONTRACT.read_text(encoding='utf-8'), re.S)}
    functions, annotations = helpers(source)
    insertions = []
    for function in functions:
        name = function['name']
        if function['tokens'] != definitions[name]['tokens']:
            raise ValueError(f'Helper {name} differs from the fixed implementation; do not trust its summary')
        if any(a.boundary == function['boundary'] for a in annotations):
            raise ValueError(f'Helper {name} already has annotations; reconcile explicitly in a new experiment')
        insertions.append((function['start'], blocks[name]+'\n'))
    result = source
    for offset, block in sorted(insertions, reverse=True):
        result = result[:offset] + block + result[offset:]
    if [v.text for v in scan(result)[0]] != [v.text for v in scan(source)[0]]:
        raise ValueError('Contract attachment changed executable tokens')
    return result, [v['name'] for v in functions]


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--check', action='store_true')
    parser.add_argument('--source-root', type=Path, default=SOURCE_ROOT)
    parser.add_argument('--attach', type=Path, help='Write a separately annotated source; input is never edited')
    parser.add_argument('--output', type=Path)
    args = parser.parse_args()
    try:
        if args.attach:
            if args.output is None or args.output.resolve() == args.attach.resolve():
                raise ValueError('--attach requires a different --output path')
            definitions, _ = inventory(args.source_root)
            annotated, names = attach(args.attach.read_text(encoding='utf-8'), definitions)
            args.output.parent.mkdir(parents=True, exist_ok=True)
            args.output.write_text(annotated, encoding='utf-8')
            args.output.with_suffix('.support.json').write_text(json.dumps({
                'source': str(args.attach), 'fixed_contracts': str(CONTRACT),
                'helper_functions': names, 'role': 'Fixed support dependencies; exclude helper goals from benchmark score',
                'body_match': 'Exact executable tokens'}, indent=2)+'\n', encoding='utf-8')
            print(json.dumps({'output': str(args.output), 'helpers': names}))
        else:
            manifest = prepare(args.check, args.source_root)
            print(json.dumps({'originals': manifest['originals'],
                              'originals_with_helpers': manifest['originals_with_helpers'],
                              'helper_original_counts': {k:v['original_count'] for k,v in manifest['helpers'].items()}}))
        return 0
    except (OSError, ValueError) as error:
        print(f'JArithmetic preparation error: {error}', file=sys.stderr)
        return 2


if __name__ == '__main__':
    raise SystemExit(main())
