import pytest

from common.assertion import assert_result, assert_stream_success
from config.settings import PROJECT_ROOT

pytestmark = pytest.mark.regression


def test_get_report(misc_api):
    data = assert_result(misc_api.get_report())
    assert data is not None, "报表数据不应为 null"


def test_get_logs(misc_api, misc_data):
    logs = misc_data["logs"]
    data = assert_result(misc_api.get_logs(logs["current_page"], logs["page_size"]))
    assert data is not None, "操作日志分页结果不应为 null"


@pytest.mark.write
def test_upload(misc_api, misc_data, write_enabled):
    response = misc_api.upload(PROJECT_ROOT / misc_data["upload"]["file_path"])
    assert_result(response)


@pytest.mark.stream
def test_chat(misc_api, misc_data):
    chat = misc_data["chat"]
    response = misc_api.chat(chat["prompt"], chat["chat_id"], chat["cur_id"])
    content = assert_stream_success(response)
    assert content.strip(), "AI 流式响应不应为空"
