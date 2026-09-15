import hashlib
import time
from copy import deepcopy
from typing import Any


def md5_text(value: str) -> str:
    return hashlib.md5(value.encode("utf-8")).hexdigest()


def unique_text(prefix: str = "auto") -> str:
    return f"{prefix}_{int(time.time() * 1000)}"


def render_data(value: Any, variables: dict[str, Any]) -> Any:
    if isinstance(value, dict):
        return {key: render_data(item, variables) for key, item in value.items()}
    if isinstance(value, list):
        return [render_data(item, variables) for item in value]
    if isinstance(value, str):
        rendered = value
        for name, replacement in variables.items():
            pattern = "$" + "{" + name + "}"
            rendered = rendered.replace(pattern, str(replacement))
        return rendered
    return deepcopy(value)
