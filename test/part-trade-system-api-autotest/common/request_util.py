from __future__ import annotations

import json
from typing import Any

import requests

from common.logger import get_logger


class RequestUtil:
    """基于 requests.Session 的 HTTP 封装，统一处理基础地址、JWT、超时和日志。"""

    def __init__(
        self,
        base_url: str,
        timeout: float = 15,
        verify_ssl: bool = False,
        token_header: str = "token",
        log_body: bool = True,
    ) -> None:
        self.base_url = base_url.rstrip("/")
        self.timeout = timeout
        self.verify_ssl = verify_ssl
        self.token_header = token_header
        self.log_body = log_body
        self.token: str | None = None
        self.session = requests.Session()
        self.logger = get_logger()

    def set_token(self, token: str) -> None:
        self.token = token

    def clear_token(self) -> None:
        self.token = None

    def _url(self, path: str) -> str:
        if path.startswith(("http://", "https://")):
            return path
        return f"{self.base_url}/{path.lstrip('/')}"

    def request(self, method: str, path: str, *, stream: bool = False, **kwargs: Any) -> requests.Response:
        headers = dict(kwargs.pop("headers", {}) or {})
        if self.token and self.token_header not in headers:
            headers[self.token_header] = self.token

        url = self._url(path)
        self.logger.info("HTTP %s %s params=%s", method.upper(), url, kwargs.get("params"))
        response = self.session.request(
            method=method.upper(),
            url=url,
            headers=headers,
            timeout=kwargs.pop("timeout", self.timeout),
            verify=kwargs.pop("verify", self.verify_ssl),
            stream=stream,
            **kwargs,
        )
        if self.log_body and not stream:
            preview = response.text[:1000].replace("\n", " ")
            self.logger.info(
                "HTTP %s %s -> %s body=%s",
                method.upper(),
                url,
                response.status_code,
                preview,
            )
        elif stream:
            self.logger.info("HTTP %s %s -> %s stream=True", method.upper(), url, response.status_code)
        return response

    def get(self, path: str, **kwargs: Any) -> requests.Response:
        return self.request("GET", path, **kwargs)

    def post(self, path: str, **kwargs: Any) -> requests.Response:
        return self.request("POST", path, **kwargs)

    def put(self, path: str, **kwargs: Any) -> requests.Response:
        return self.request("PUT", path, **kwargs)

    def delete(self, path: str, **kwargs: Any) -> requests.Response:
        return self.request("DELETE", path, **kwargs)

    def close(self) -> None:
        self.session.close()


def compact_params(**params: Any) -> dict[str, Any]:
    """移除值为 None 的查询参数。"""
    return {key: value for key, value in params.items() if value is not None}


def to_json_text(value: Any) -> str:
    return json.dumps(value, ensure_ascii=False)
