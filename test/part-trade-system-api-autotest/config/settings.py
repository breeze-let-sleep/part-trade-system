from pathlib import Path

PROJECT_ROOT = Path(__file__).resolve().parents[1]
CONFIG_DIR = PROJECT_ROOT / "config"
DATA_DIR = PROJECT_ROOT / "data"
LOG_DIR = PROJECT_ROOT / "logs"
REPORT_DIR = PROJECT_ROOT / "reports"
ALLURE_RESULTS_DIR = REPORT_DIR / "allure-results"
ALLURE_REPORT_DIR = REPORT_DIR / "allure-report"
DEFAULT_CONFIG_PATH = CONFIG_DIR / "config.yaml"
