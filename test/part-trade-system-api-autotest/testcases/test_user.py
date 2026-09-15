import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


@pytest.mark.write
def test_add_customer(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["customer"]["add"])
    response = user_api.add_customer(payload)
    assert_result(response)


@pytest.mark.write
def test_update_customer(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["customer"]["update"])
    response = user_api.update_customer(payload)
    assert_result(response)


@pytest.mark.write
def test_delete_customer(user_api, user_data, write_enabled):
    response = user_api.delete_customer(user_data["customer"]["add"]["id"])
    assert_result(response)


def test_get_customer_info(user_api, user_data):
    data = assert_result(user_api.get_customer_info(user_data["customer"]["id"]))
    assert data is not None, "顾客详情不应为 null"


def test_get_customers(user_api, user_data):
    common = user_data["common"]
    data = assert_result(
        user_api.get_customers(common["current_page"], common["page_size"])
    )
    assert data is not None, "顾客分页结果不应为 null"


def test_like_customers(user_api, user_data):
    filters = user_data["customer"]["filter"]
    data = assert_result(
        user_api.like_customers(
            filters["like_name"],
            filters["like_address"],
            filters["start_time"],
            filters["end_time"],
        )
    )
    assert data is not None, "顾客模糊查询结果不应为 null"


@pytest.mark.write
def test_add_merchant(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["merchant"]["add"])
    response = user_api.add_merchant(payload)
    assert_result(response)


@pytest.mark.write
def test_update_merchant(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["merchant"]["update"])
    response = user_api.update_merchant(payload)
    assert_result(response)


@pytest.mark.write
def test_delete_merchant(user_api, user_data, write_enabled):
    response = user_api.delete_merchant(user_data["merchant"]["add"]["id"])
    assert_result(response)


def test_get_merchant_info(user_api, user_data):
    data = assert_result(user_api.get_merchant_info(user_data["merchant"]["id"]))
    assert data is not None, "供应商详情不应为 null"


def test_get_merchants(user_api, user_data):
    common = user_data["common"]
    data = assert_result(
        user_api.get_merchants(common["current_page"], common["page_size"])
    )
    assert data is not None, "供应商分页结果不应为 null"


def test_like_merchants(user_api, user_data):
    filters = user_data["merchant"]["filter"]
    data = assert_result(
        user_api.like_merchants(
            filters["like_name"],
            filters["like_address"],
            filters["start_time"],
            filters["end_time"],
        )
    )
    assert data is not None, "供应商模糊查询结果不应为 null"


@pytest.mark.write
def test_add_admin(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["admin"]["add"])
    response = user_api.add_admin(payload)
    assert_result(response)


@pytest.mark.write
def test_update_admin(user_api, user_data, rendered_payload, write_enabled):
    payload = rendered_payload(user_data["admin"]["update"])
    response = user_api.update_admin(payload)
    assert_result(response)


@pytest.mark.write
def test_delete_admin(user_api, user_data, write_enabled):
    response = user_api.delete_admin(user_data["admin"]["add"]["id"])
    assert_result(response)


def test_get_admin_info(user_api, user_data):
    data = assert_result(user_api.get_admin_info(user_data["admin"]["id"]))
    assert data is not None, "管理员详情不应为 null"


def test_get_admins(user_api, user_data):
    common = user_data["common"]
    data = assert_result(
        user_api.get_admins(common["current_page"], common["page_size"])
    )
    assert data is not None, "管理员分页结果不应为 null"


def test_like_admins(user_api, user_data):
    filters = user_data["admin"]["filter"]
    data = assert_result(
        user_api.like_admins(
            filters["input"],
            filters["start_time"],
            filters["end_time"],
        )
    )
    assert data is not None, "管理员模糊查询结果不应为 null"
