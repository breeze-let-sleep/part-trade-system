from apis.base_api import BaseApi


class ContractApi(BaseApi):
    def get_orders(self, current_page: int, page_size: int):
        """[GET] /contracts/getOrders"""
        return self.client.get(
            "/contracts/getOrders",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def get_order_detail(self, order_id: int):
        """[GET] /contracts/getOrderDetail/{orderId}"""
        return self.client.get(f"/contracts/getOrderDetail/{order_id}")

    def signature(self, order_id: int, value: str):
        """[PUT] /contracts/signature"""
        return self.client.put(
            "/contracts/signature",
            params={"orderId": order_id, "value": value},
        )
