from __future__ import annotations

import argparse
import shutil
import subprocess
import sys
from pathlib import Path

import pytest

from config.settings import ALLURE_REPORT_DIR, ALLURE_RESULTS_DIR, PROJECT_ROOT


def build_args(options: argparse.Namespace) -> list[str]:
    args = [str(PROJECT_ROOT / "testcases")]
    if options.marker:
        args.extend(["-m", options.marker])
    if options.keyword:
        args.extend(["-k", options.keyword])
    if options.allure:
        args.append(f"--alluredir={ALLURE_RESULTS_DIR}")
    return args


def generate_allure_report() -> None:
    allure_bin = shutil.which("allure")
    if not allure_bin:
        raise RuntimeError("未找到 allure 命令，请先安装 Allure CLI 或跳过 --allure")
    subprocess.run(
        [
            allure_bin,
            "generate",
            str(ALLURE_RESULTS_DIR),
            "-o",
            str(ALLURE_REPORT_DIR),
            "--clean",
        ],
        check=True,
        cwd=PROJECT_ROOT,
    )


def main() -> int:
    parser = argparse.ArgumentParser(description="零件交易系统接口自动化测试入口")
    parser.add_argument("--env", default=None, help="环境名称")
    parser.add_argument("--marker", default=None, help="pytest 标记表达式，例如 smoke")
    parser.add_argument("--keyword", default=None, help="pytest -k 表达式")
    parser.add_argument("--allure", action="store_true", help="生成 Allure 原始结果和 HTML 报告")
    options = parser.parse_args()

    args = build_args(options)
    if options.env:
        args.extend(["--env", options.env])

    exit_code = pytest.main(args)
    if exit_code == 0 and options.allure:
        generate_allure_report()
    return int(exit_code)


if __name__ == "__main__":
    sys.exit(main())
