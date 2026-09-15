from typing import Any

from apis.base_api import BaseApi
from common.request_util import compact_params


class OrderApi(BaseApi):
    def add_order(self, payload: dict[str, Any]):
        """[POST] /orders/addOrder"""
        return self.client.post("/orders/addOrder", json=payload)

    def pay_order(self, order_id: int):
        """[PUT] /orders/payOrder/{orderId}"""
        return self.client.put(f"/orders/payOrder/{order_id}")

    def remove_order(self, order_id: int):
        """[DELETE] /orders/removeOrder/{orderId}"""
        return self.client.delete(f"/orders/removeOrder/{order_id}")

    def get_paid_message(self, current_page: int, page_size: int):
        """[GET] /orders/getPaidMessage"""
        return self.client.get(
            "/orders/getPaidMessage",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def get_end_like_orders(
        self,
        part_name: str | None = None,
        order_id: int | None = None,
        star: int | None = None,
        start_time: str | None = None,
        end_time: str | None = None,
        current_page: int | None = None,
        page_size: int | None = None,
    ):
        """[GET] /orders/getEndLikeOrders"""
        params = compact_params(
            partName=part_name,
            orderId=order_id,
            star=star,
            startTime=start_time,
            endTime=end_time,
            currentPage=current_page,
            pageSize=page_size,
        )
        return self.client.get("/orders/getEndLikeOrders", params=params)

    def evaluate_order(self, payload: dict[str, Any]):
        """[PUT] /details/evaluateOrder"""
        return self.client.put("/details/evaluateOrder", json=payload)

    def deliver(self, detail_id: int):
        """[PUT] /details/deliver/{id}"""
        return self.client.put(f"/details/deliver/{detail_id}")

    def get_processing_orders(self, current_page: int, page_size: int):
        """[GET] /details/getProcessingOrders"""
        return self.client.get(
            "/details/getProcessingOrders",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def get_end_orders(self, current_page: int, page_size: int):
        """[GET] /details/getEndOrders"""
        return self.client.get(
            "/details/getEndOrders",
            params={"currentPage": current_page, "pageSize": page_size},
        )
