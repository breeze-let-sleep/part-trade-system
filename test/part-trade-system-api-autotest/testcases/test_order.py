import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


@pytest.mark.write
def test_add_order(order_api, order_data, write_enabled):
    response = order_api.add_order(order_data["order"]["add"])
    assert_result(response)


@pytest.mark.write
def test_pay_order(order_api, order_data, write_enabled):
    response = order_api.pay_order(order_data["order"]["id"])
    assert_result(response)



def test_get_paid_message(order_api, order_data):
    common = order_data["common"]
    data = assert_result(
        order_api.get_paid_message(common["current_page"], common["page_size"])
    )
    assert data is not None, "待支付订单信息不应为 null"


def test_get_end_like_orders(order_api, order_data):
    common = order_data["common"]
    filters = order_data["order"]["filter"]
    data = assert_result(
        order_api.get_end_like_orders(
            part_name=filters["part_name"],
            star=filters["star"],
            start_time=filters["start_time"],
            end_time=filters["end_time"],
            current_page=common["current_page"],
            page_size=common["page_size"],
        )
    )
    assert data is not None, "已完成订单模糊查询结果不应为 null"


@pytest.mark.write
def test_deliver(order_api, order_data, write_enabled):
    response = order_api.deliver(order_data["detail"]["id"])
    assert_result(response)


@pytest.mark.write
def test_evaluate_order(order_api, order_data, write_enabled):
    response = order_api.evaluate_order(order_data["detail"]["evaluate"])
    assert_result(response)


def test_get_processing_orders(order_api, order_data):
    common = order_data["common"]
    data = assert_result(
        order_api.get_processing_orders(common["current_page"], common["page_size"])
    )
    assert data is not None, "处理中订单分页结果不应为 null"


def test_get_end_orders(order_api, order_data):
    common = order_data["common"]
    data = assert_result(
        order_api.get_end_orders(common["current_page"], common["page_size"])
    )
    assert data is not None, "已结束订单分页结果不应为 null"


@pytest.mark.write
def test_remove_order(order_api, order_data, write_enabled):
    response = order_api.remove_order(order_data["order"]["id"])
    assert_result(response)
