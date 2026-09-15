import os
from copy import deepcopy

import pytest

from apis import CollectApi, ContractApi, GoodsApi, LoginApi, MiscApi, OrderApi, PartApi, UserApi
from common.assertion import assert_result
from common.data_util import md5_text, render_data, unique_text
from common.db_util import DatabaseUtil
from common.request_util import RequestUtil
from common.yaml_util import load_config, load_yaml
from config.settings import DATA_DIR, DEFAULT_CONFIG_PATH


def pytest_addoption(parser):
    parser.addoption(
        "--env",
        action="store",
        default=os.getenv("API_TEST_ENV"),
        help="config/config.yaml 中的环境名称，例如 test、local",
    )


@pytest.fixture(scope="session")
def config(request):
    config_path = os.getenv("API_TEST_CONFIG", str(DEFAULT_CONFIG_PATH))
    environment = request.config.getoption("--env")
    return load_config(config_path, environment)


@pytest.fixture(scope="session")
def api_client(config):
    client = RequestUtil(
        base_url=config["base_url"],
        timeout=config.get("timeout", 15),
        verify_ssl=config.get("verify_ssl", False),
        token_header=config.get("http", {}).get("token_header", "token"),
        log_body=config.get("http", {}).get("log_body", True),
    )
    yield client
    client.close()


@pytest.fixture(scope="session")
def authenticated_client(api_client, auth_token):
    return api_client


@pytest.fixture(scope="session")
def login_api(api_client):
    return LoginApi(api_client)


@pytest.fixture(scope="session")
def user_api(authenticated_client):
    return UserApi(authenticated_client)


@pytest.fixture(scope="session")
def order_api(authenticated_client):
    return OrderApi(authenticated_client)


@pytest.fixture(scope="session")
def part_api(authenticated_client):
    return PartApi(authenticated_client)


@pytest.fixture(scope="session")
def goods_api(authenticated_client):
    return GoodsApi(authenticated_client)


@pytest.fixture(scope="session")
def collect_api(authenticated_client):
    return CollectApi(authenticated_client)


@pytest.fixture(scope="session")
def contract_api(authenticated_client):
    return ContractApi(authenticated_client)


@pytest.fixture(scope="session")
def misc_api(authenticated_client, config):
    return MiscApi(authenticated_client, upload_mode=config.get("upload", {}).get("mode", "multipart"))


def _load_data(file_name: str) -> dict:
    return load_yaml(DATA_DIR / file_name)


@pytest.fixture(scope="session")
def login_data():
    return _load_data("login_data.yaml")


@pytest.fixture(scope="session")
def user_data():
    return _load_data("user_data.yaml")


@pytest.fixture(scope="session")
def order_data():
    return _load_data("order_data.yaml")


@pytest.fixture(scope="session")
def part_data():
    return _load_data("part_data.yaml")


@pytest.fixture(scope="session")
def misc_data():
    return _load_data("misc_data.yaml")


@pytest.fixture(scope="session")
def render_variables():
    return {"suffix": unique_text("auto")}


@pytest.fixture(scope="session")
def auth_token(api_client, login_api, login_data, config):
    payload = deepcopy(login_data["login"]["valid"])
    if config.get("auth", {}).get("auto_fetch_code", True):
        code = assert_result(login_api.get_code(), expected_data_type=str)
        payload["code"] = code
    if config.get("auth", {}).get("password_mode", "plain") == "md5":
        payload["password"] = md5_text(str(payload["password"]))

    token = assert_result(login_api.login(payload), expected_data_type=str).strip()
    assert token, "登录成功但 token 为空"
    api_client.set_token(token)
    return token


@pytest.fixture
def write_enabled(config):
    if not config.get("test_data", {}).get("run_write_tests", False):
        pytest.skip("写接口默认关闭；确认测试库可安全写入后设置 test_data.run_write_tests=true")


@pytest.fixture
def rendered_payload(render_variables):
    def _render(payload: dict) -> dict:
        return render_data(payload, render_variables)

    return _render


@pytest.fixture(scope="session")
def database(config):
    db_config = config.get("database", {})
    if not db_config.get("enabled", False):
        yield None
        return
    database_util = DatabaseUtil(db_config)
    yield database_util
    database_util.close()
