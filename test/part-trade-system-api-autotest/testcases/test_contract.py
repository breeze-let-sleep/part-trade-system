import pytest

from common.assertion import assert_result

pytestmark = [pytest.mark.regression, pytest.mark.contract]


def test_get_orders(contract_api, order_data):
    common = order_data["common"]
    data = assert_result(
        contract_api.get_orders(common["current_page"], common["page_size"])
    )
    assert data is not None, "待签名订单分页结果不应为 null"


def test_get_order_detail(contract_api, order_data):
    data = assert_result(contract_api.get_order_detail(order_data["order"]["id"]))
    assert data is not None, "合同订单详情不应为 null"


@pytest.mark.write
def test_signature(contract_api, order_data, write_enabled):
    signature = order_data["contract"]["signature"]
    response = contract_api.signature(signature["order_id"], signature["value"])
    assert_result(response)
