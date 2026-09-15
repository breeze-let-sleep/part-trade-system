from apis.base_api import BaseApi
from common.request_util import compact_params


class CollectApi(BaseApi):
    def add_collect(self, customer_id: int, merchant_id: int):
        """[POST] /collects/addCollect/{customerId}/{merchantId}"""
        return self.client.post(f"/collects/addCollect/{customer_id}/{merchant_id}")

    def remove_collect(self, customer_id: int, merchant_id: int):
        """[DELETE] /collects/removeCollect/{customerId}/{merchantId}"""
        return self.client.delete(f"/collects/removeCollect/{customer_id}/{merchant_id}")

    def get_collects(self, current_page: int, page_size: int):
        """[GET] /collects/getCollects"""
        return self.client.get(
            "/collects/getCollects",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def like_collects(
        self,
        input_merchant: str | None = None,
        customer_id: int | None = None,
        current_page: int | None = None,
        page_size: int | None = None,
    ):
        """[GET] /collects/likeCollects"""
        params = compact_params(
            inputMerchant=input_merchant,
            customerId=customer_id,
            currentPage=current_page,
            pageSize=page_size,
        )
        return self.client.get("/collects/likeCollects", params=params)
