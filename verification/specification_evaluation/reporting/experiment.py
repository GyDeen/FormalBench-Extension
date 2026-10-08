"""Report original consistency and the primary runtime completeness result."""
import argparse
from pathlib import Path
from verification.specification_evaluation.manifest import DEFAULT_MANIFEST,DEFAULT_JAVA,DEFAULT_C,load_population
from verification.specification_evaluation.workflow import summarize


def build(output, selection=None):
    summary=summarize(Path(output),load_population(DEFAULT_MANIFEST,DEFAULT_JAVA,DEFAULT_C))
    lines=['# Original consistency','',
           'OpenJML ESC and Frama-C WP evaluate originals only. Primary mutant completeness uses runtime checks of frozen contracts; safety is excluded from its postcondition count.','',
           '| Program | Java | C |','|---|---|---|']
    lines += [f"| {r['program']} | {r['java']} | {r['c']} |" for r in summary['original_records']]
    lines += ['', 'Current runtime completeness: [primary results](../execution_based/README.md). Historical verifier mutant evidence is retained separately and does not contribute to runtime completeness.']
    (Path(output)/'README.md').write_text('\n'.join(lines)+'\n')
    return summary


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output',type=Path,required=True)
    args=parser.parse_args();build(args.output)


if __name__=='__main__':main()
