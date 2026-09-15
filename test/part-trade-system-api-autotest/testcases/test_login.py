import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


@pytest.mark.smoke
def test_get_code(login_api):
    data = assert_result(login_api.get_code(), expected_data_type=str)
    assert data.strip(), "验证码不应为空"


@pytest.mark.smoke
def test_login(auth_token):
    assert isinstance(auth_token, str)
    assert auth_token.strip(), "登录成功后应返回 JWT"


@pytest.mark.write
def test_register(login_api, login_data, rendered_payload, write_enabled):
    payload = rendered_payload(login_data["register"]["customer"])
    payload["code"] = assert_result(login_api.get_code(), expected_data_type=str)
    response = login_api.register(payload)
    assert_result(response)
