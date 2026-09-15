import pytest

from common.assertion import assert_result

pytestmark = pytest.mark.regression


@pytest.mark.write
def test_add_part(part_api, part_data, rendered_payload, write_enabled):
    payload = rendered_payload(part_data["part"]["add"])
    response = part_api.add_part(payload)
    assert_result(response)


@pytest.mark.write
def test_update_part(part_api, part_data, write_enabled):
    response = part_api.update_part(part_data["part"]["update"])
    assert_result(response)


@pytest.mark.write
def test_publish_part(part_api, part_data, write_enabled):
    response = part_api.publish_part(
        part_data["part"]["add"]["id"],
        part_data["part"]["publish"]["is_publish"],
    )
    assert_result(response)


@pytest.mark.write
def test_delete_part(part_api, part_data, write_enabled):
    response = part_api.delete_part(part_data["part"]["add"]["id"])
    assert_result(response)


def test_get_parts(part_api, part_data):
    common = part_data["common"]
    data = assert_result(
        part_api.get_parts(common["current_page"], common["page_size"])
    )
    assert data is not None, "分页结果不应为 null"


def test_like_parts(part_api, part_data):
    filters = part_data["part"]["filter"]
    data = assert_result(
        part_api.like_parts(
            part_name=filters["part_name"],
            weight=filters["weight"],
            color=filters["color"],
            start_time=filters["start_time"],
            end_time=filters["end_time"],
            is_publish=filters["is_publish"],
        )
    )
    assert data is not None, "模糊查询结果不应为 null"
