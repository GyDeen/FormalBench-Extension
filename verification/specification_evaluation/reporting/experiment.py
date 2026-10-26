"""Report original consistency and the primary runtime completeness result."""
import argparse
import os
from pathlib import Path
from verification.specification_evaluation.manifest import DEFAULT_MANIFEST,DEFAULT_JAVA,DEFAULT_C,load_population
from verification.specification_evaluation.workflow import summarize


def build(output, selection=None):
    summary=summarize(Path(output),load_population(DEFAULT_MANIFEST,DEFAULT_JAVA,DEFAULT_C))
    lines=['# Original consistency','',
           'OpenJML ESC and Frama-C WP evaluate originals only. Primary mutant completeness requires rejection of every differing normally returned output across the full tested input pool; safety failures are separate.','',
           '| Program | Java | C |','|---|---|---|']
    lines += [f"| {r['program']} | {r['java']} | {r['c']} |" for r in summary['original_records']]
    primary = Path(output) / 'mutant_detection/README.md'
    if not primary.exists():
        primary = Path(__file__).resolve().parents[1] / 'STRICT_COMPLETENESS_RESULTS_20261026.md'
    link = os.path.relpath(primary, Path(output))
    lines += ['', f'Current strict completeness: [primary results]({link}). Historical first-witness and verifier-mutant evidence does not define the primary score.']
    (Path(output)/'README.md').write_text('\n'.join(lines)+'\n')
    return summary


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output',type=Path,required=True)
    args=parser.parse_args();build(args.output)


if __name__=='__main__':main()
