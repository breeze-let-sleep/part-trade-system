from __future__ import annotations

from typing import Any, Type

import requests


def assert_http_ok(response: requests.Response, expected_status: int = 200) -> None:
    assert response.status_code == expected_status, (
        f"HTTP 状态码不符合预期：期望 {expected_status}，实际 {response.status_code}，"
        f"响应={response.text[:500]}"
    )


def assert_result(
    response: requests.Response,
    expected_code: int = 1,
    expected_msg: str = "success",
    expected_data_type: Type | tuple[Type, ...] | None = None,
    allow_missing_data: bool = True,
) -> Any:
    """断言统一 Result 响应，并返回 data。"""
    assert_http_ok(response, 200)
    try:
        body = response.json()
    except ValueError as exc:
        raise AssertionError(f"响应不是合法 JSON：{response.text[:500]}") from exc

    assert isinstance(body, dict), f"Result 应为对象，实际：{type(body).__name__}"
    assert body.get("code") == expected_code, f"code 不符合预期：{body}"
    assert body.get("msg") == expected_msg, f"msg 不符合预期：{body}"

    data = body.get("data")
    if not allow_missing_data:
        assert "data" in body, f"响应缺少 data 字段：{body}"
    if expected_data_type is not None:
        assert isinstance(data, expected_data_type), (
            f"data 类型不符合预期：期望 {expected_data_type}，实际 {type(data).__name__}，data={data}"
        )
    return data


def assert_success_result(response: requests.Response) -> Any:
    return assert_result(response)


def assert_stream_success(response: requests.Response, min_bytes: int = 1) -> bytes:
    assert_http_ok(response, 200)
    content = b"".join(response.iter_content(chunk_size=1024))
    assert len(content) >= min_bytes, f"流式响应内容为空或不足 {min_bytes} 字节"
    return content
