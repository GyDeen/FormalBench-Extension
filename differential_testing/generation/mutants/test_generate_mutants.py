"""Check Major orchestration independently of a local Major installation."""

import contextlib
import io
import json
import subprocess
import tempfile
import unittest
from pathlib import Path
from unittest.mock import patch

from .generate_mutants import DEFAULT_MML, generate_one, load_selection, main


class MutantGenerationTests(unittest.TestCase):
    def test_selection_preserves_code_and_rejects_missing_or_duplicate_classes(self):
        with tempfile.TemporaryDirectory() as tmp:
            selection = Path(tmp) / "selection.jsonl"
            record = {"class_name": "A", "code": "class A {}"}
            selection.write_text(json.dumps(record) + "\n")
            self.assertEqual(load_selection(selection, ["A"])[0]["code"], record["code"])
            with self.assertRaisesRegex(ValueError, "absent"):
                load_selection(selection, ["B"])
            selection.write_text((json.dumps(record) + "\n") * 2)
            with self.assertRaisesRegex(ValueError, "Duplicate"):
                load_selection(selection, None)

    def test_cli_isolates_classes_and_repeated_runs(self):
        with tempfile.TemporaryDirectory(prefix="major test ") as tmp:
            root = Path(tmp)
            major = root / "major"
            # Exercise the subprocess boundary, including paths with spaces,
            # without requiring the external mutation engine.
            major.write_text(
                "#!/bin/sh\n"
                'test "$1" = "--mml" || exit 2\n'
                'test -f "$2" || exit 3\n'
                'test "$4" = "--export" || exit 4\n'
                'test "$5" = "export.mutants" || exit 5\n'
                'mkdir -p mutants/1\n'
                'cp "$3" "mutants/1/$(basename "$3")"\n'
                'echo test-export\n'
            )
            major.chmod(0o755)
            selection = root / "selection.jsonl"
            records = [{"class_name": name, "code": f"class {name} {{}}"} for name in ("A", "B")]
            selection.write_text("\n".join(json.dumps(record) for record in records))
            output = root / "output"
            args = ["--selection", str(selection), "--major-bin", str(major),
                    "--output-dir", str(output)]
            self.assertTrue(DEFAULT_MML.is_file())
            with contextlib.redirect_stdout(io.StringIO()):
                main(args)
                main(args)
            summaries = list(output.glob("*/generation_summary.json"))
            self.assertEqual(len(summaries), 2)
            for path in summaries:
                summary = json.loads(path.read_text())
                self.assertEqual(len(summary["programs"]), 2)
                for record in summary["programs"]:
                    self.assertEqual(record["status"], "success")
                    self.assertEqual(record["mutant_count"], 1)
                    self.assertEqual(Path(record["mutant_source_files"][0]).read_text(),
                                     Path(record["source_file"]).read_text())
                    self.assertIn("test-export", Path(record["log"]).read_text())

    def test_failure_timeout_and_zero_mutants_are_distinct(self):
        with tempfile.TemporaryDirectory() as tmp:
            root = Path(tmp)
            for label, return_code, status in [("failed", 1, "failed"), ("empty", 0, "no_mutants")]:
                with patch("subprocess.run", return_value=subprocess.CompletedProcess([], return_code)):
                    result = generate_one(root / "A.java", root / label, root / "major",
                                          DEFAULT_MML, {}, 1)
                self.assertEqual(result["status"], status)
            with patch("subprocess.run", side_effect=subprocess.TimeoutExpired("major", 1)):
                result = generate_one(root / "A.java", root / "timeout", root / "major",
                                      DEFAULT_MML, {}, 1)
            self.assertEqual(result["status"], "failed")
            self.assertIn("timed out", result["error"])


if __name__ == "__main__":
    unittest.main()
