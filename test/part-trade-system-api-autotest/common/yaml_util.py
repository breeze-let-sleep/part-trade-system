from copy import deepcopy
from pathlib import Path
from typing import Any, Mapping

import yaml

from config.settings import DEFAULT_CONFIG_PATH


def load_yaml(path: str | Path) -> Any:
    file_path = Path(path)
    if not file_path.is_absolute():
        file_path = Path.cwd() / file_path
    with file_path.open("r", encoding="utf-8") as stream:
        return yaml.safe_load(stream) or {}


def load_config(path: str | Path = DEFAULT_CONFIG_PATH, environment: str | None = None) -> dict:
    raw = load_yaml(path)
    selected_env = environment or raw.get("environment", "test")
    environments = raw.get("environments", {})
    if selected_env not in environments:
        available = ", ".join(environments) or "(空)"
        raise KeyError(f"环境 {selected_env!r} 不存在，可选环境：{available}")

    config = deepcopy(raw)
    config["active_environment"] = selected_env
    config["base_url"] = environments[selected_env]["base_url"]
    config["timeout"] = environments[selected_env].get("timeout", 15)
    config["verify_ssl"] = environments[selected_env].get("verify_ssl", False)
    return config


def deep_merge(base: Mapping, override: Mapping) -> dict:
    result = deepcopy(dict(base))
    for key, value in override.items():
        if isinstance(value, Mapping) and isinstance(result.get(key), Mapping):
            result[key] = deep_merge(result[key], value)
        else:
            result[key] = deepcopy(value)
    return result
