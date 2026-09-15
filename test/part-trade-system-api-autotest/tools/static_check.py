from __future__ import annotations

import compileall
import subprocess
import sys
from pathlib import Path

import yaml

from check_endpoint_coverage import main as coverage_main


def project_root() -> Path:
    return Path(__file__).resolve().parents[1]


def validate_yaml(root: Path) -> None:
    for file_path in sorted((root / "data").glob("*.yaml")):
        with file_path.open("r", encoding="utf-8") as stream:
            yaml.safe_load(stream)
        print(f"YAML OK: {file_path.name}")


def main() -> int:
    root = project_root()
    compile_ok = compileall.compile_dir(str(root), quiet=1, force=True)
    print(f"Python 编译：{'通过' if compile_ok else '失败'}")
    validate_yaml(root)

    sys.argv = [sys.argv[0], "--openapi", str(root / "api-docs" / "default_OpenAPI.json")]
    coverage_ok = coverage_main() == 0
    return 0 if compile_ok and coverage_ok else 1


if __name__ == "__main__":
    raise SystemExit(main())
