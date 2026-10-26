"""Audited compatibility workarounds for OpenJML 21.0.27."""
from __future__ import annotations
import re
import subprocess
import zipfile
from pathlib import Path
from verification.specification_evaluation.manifest import REPO, InputError
EXPORTS = ['--add-exports=jdk.compiler/com.sun.tools.javac.' + part + '=ALL-UNNAMED' for part in ('code', 'tree')]

def normalize_annotation(text: str) -> tuple[str, list[str]]:
    """Rewrite standalone JML loop counters to a numeric identity and log edits."""
    pattern = '"(?:\\\\.|[^"\\\\])*"|\'(?:\\\\.|[^\'\\\\])*\'|\\\\count\\b'
    count = 0

    def replace(match):
        """Preserve literals and expand only a matched standalone loop counter."""
        nonlocal count
        if match.group() != '\\count':
            return match.group()
        count += 1
        return '(\\count + 0)'
    result = re.sub(pattern, replace, text)
    return (result, [f'numeric loop counter identity: \\count -> (\\count + 0), occurrences={count}'] if count else [])

def compatibility(verifier: dict) -> dict:
    """Build the OpenJML 21.0.27 plugin and bundled solver."""
    home = Path(verifier['path']).parent
    source = Path(__file__).parents[1] / 'openjml/NumericBitPredicates.java'
    compiler = home / 'jdk/bin/javac'
    directory = REPO / '.tools/openjml-compat/current-21.0.27'
    jar = directory / 'numeric-bit-predicates.jar'
    if not jar.exists():
        classes = directory / 'classes'
        classes.mkdir(parents=True, exist_ok=True)
        cmd = [str(compiler), '-java', *EXPORTS, '-d', str(classes), str(source)]
        built = subprocess.run(cmd, capture_output=True, text=True, timeout=60)
        if built.returncode:
            raise InputError(f'Cannot build OpenJML compatibility plugin: {built.stdout}{built.stderr}')
        with zipfile.ZipFile(jar, 'w') as archive:
            for compiled in sorted(classes.rglob('*.class')):
                archive.writestr(zipfile.ZipInfo(compiled.relative_to(classes).as_posix()), compiled.read_bytes())
            archive.writestr(zipfile.ZipInfo('META-INF/services/com.sun.source.util.Plugin'), 'verification.compat.NumericBitPredicates\n')
    solver = home / 'Solvers-linux/z3-4.10.2'
    if not solver.is_file():
        raise InputError(f'OpenJML 21.0.27 compatibility requires the bundled solver: {solver}')
    version = subprocess.run([str(solver), '--version'], capture_output=True, text=True, timeout=10)
    if version.returncode:
        raise InputError(f'Cannot run bundled Z3: {version.stderr}')
    return {'name': 'OpenJML 21.0.27 numeric bit predicates and bundled Z3 repair', 'source': str(source), 'plugin': str(jar), 'solver': {'path': str(solver), 'version': version.stdout.strip()}, 'scope': 'primitive int local/parameter predicates (x ^ 1) == 0 and (x | 1) == 0'}

def command_options(verifier: dict, source: Path, prover: str) -> list[str]:
    """Return JVM plugin and solver arguments when a compatibility build exists."""
    compat = verifier.get('compatibility', {})
    if 'plugin' not in compat:
        return []
    options = ['-J' + flag for flag in EXPORTS]
    options += ['-processorpath', compat['plugin'], '-Xplugin:NumericBitPredicates ' + source.resolve().as_uri()]
    if prover == 'z3-4.3.X':
        options.append('--exec=' + compat['solver']['path'])
    return options
