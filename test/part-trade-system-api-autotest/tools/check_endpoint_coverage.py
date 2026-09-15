from __future__ import annotations

import argparse
import json
import re
from pathlib import Path

HTTP_METHODS = {"get", "post", "put", "patch", "delete", "head", "options"}
DOC_PATTERN = re.compile(r"\[(GET|POST|PUT|PATCH|DELETE|HEAD|OPTIONS)\]\s+(/[A-Za-z0-9_/{}-]+)")


def project_root() -> Path:
    return Path(__file__).resolve().parents[1]


def find_openapi(explicit: str | None) -> Path:
    if explicit:
        path = Path(explicit)
        if path.is_file():
            return path
        raise FileNotFoundError(f"OpenAPI 文件不存在：{path}")

    root = project_root()
    candidates = [
        root / "api-docs" / "default_OpenAPI.json",
        root.parent / "api-docs" / "default_OpenAPI.json",
    ]
    for candidate in candidates:
        if candidate.is_file():
            return candidate
    raise FileNotFoundError("未找到 api-docs/default_OpenAPI.json，请通过 --openapi 指定")


def load_expected(path: Path) -> set[tuple[str, str]]:
    document = json.loads(path.read_text(encoding="utf-8"))
    expected = set()
    for endpoint, operations in document.get("paths", {}).items():
        for method, operation in operations.items():
            if method.lower() in HTTP_METHODS:
                expected.add((method.upper(), endpoint))
    return expected


def load_implemented(root: Path) -> tuple[set[tuple[str, str]], dict[str, int]]:
    implemented = set()
    counts: dict[str, int] = {}
    for file_path in sorted((root / "apis").glob("*_api.py")):
        text = file_path.read_text(encoding="utf-8")
        matches = DOC_PATTERN.findall(text)
        counts[file_path.name] = len(matches)
        for method, endpoint in matches:
            implemented.add((method.upper(), endpoint))
    return implemented, counts


def main() -> int:
    parser = argparse.ArgumentParser(description="校验接口封装与 OpenAPI 文档是否完全一致")
    parser.add_argument("--openapi", default=None, help="OpenAPI JSON 文件路径")
    options = parser.parse_args()

    root = project_root()
    openapi_path = find_openapi(options.openapi)
    expected = load_expected(openapi_path)
    implemented, counts = load_implemented(root)

    print(f"OpenAPI：{openapi_path}")
    print(f"文档接口数：{len(expected)}")
    print(f"封装接口数：{len(implemented)}")
    for file_name, count in counts.items():
        print(f"  {file_name}: {count}")

    missing = sorted(expected - implemented)
    extra = sorted(implemented - expected)
    if missing:
        print("\n缺失接口：")
        for method, endpoint in missing:
            print(f"  {method} {endpoint}")
    if extra:
        print("\n文档外接口：")
        for method, endpoint in extra:
            print(f"  {method} {endpoint}")

    if missing or extra:
        return 1
    print("\n结果：接口封装与 OpenAPI 文档完全一致。")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
