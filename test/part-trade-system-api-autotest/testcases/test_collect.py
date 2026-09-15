import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


@pytest.mark.write
def test_add_collect(collect_api, user_data, write_enabled):
    response = collect_api.add_collect(
        user_data["customer"]["id"],
        user_data["merchant"]["id"],
    )
    assert_result(response)


@pytest.mark.write
def test_remove_collect(collect_api, user_data, write_enabled):
    response = collect_api.remove_collect(
        user_data["customer"]["id"],
        user_data["merchant"]["id"],
    )
    assert_result(response)


def test_get_collects(collect_api, user_data):
    common = user_data["common"]
    data = assert_result(
        collect_api.get_collects(common["current_page"], common["page_size"])
    )
    assert data is not None, "收藏分页结果不应为 null"


def test_like_collects(collect_api, user_data):
    common = user_data["common"]
    merchant = user_data["merchant"]
    data = assert_result(
        collect_api.like_collects(
            input_merchant=merchant["filter"]["like_name"],
            customer_id=user_data["customer"]["id"],
            current_page=common["current_page"],
            page_size=common["page_size"],
        )
    )
    assert data is not None, "收藏模糊查询结果不应为 null"
