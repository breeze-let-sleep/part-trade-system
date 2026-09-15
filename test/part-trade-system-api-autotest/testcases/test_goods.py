import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


def test_get_goods(goods_api, misc_data):
    goods = misc_data["goods"]
    data = assert_result(goods_api.get_goods(goods["cursor"], goods["size"]))
    assert data is not None, "商品分页结果不应为 null"


def test_like_goods(goods_api, misc_data):
    goods = misc_data["goods"]
    data = assert_result(
        goods_api.like_goods(goods["input_merchant"], goods["input_part"])
    )
    assert data is not None, "商品模糊查询结果不应为 null"
