from typing import Any

from apis.base_api import BaseApi
from common.request_util import compact_params


class UserApi(BaseApi):
    def add_customer(self, payload: dict[str, Any]):
        """[POST] /customers/addCustomer"""
        return self.client.post("/customers/addCustomer", json=payload)

    def update_customer(self, payload: dict[str, Any]):
        """[PUT] /customers/updateCustomer"""
        return self.client.put("/customers/updateCustomer", json=payload)

    def delete_customer(self, customer_id: int):
        """[DELETE] /customers/deleteCustomer/{id}"""
        return self.client.delete(f"/customers/deleteCustomer/{customer_id}")

    def get_customer_info(self, customer_id: int):
        """[GET] /customers/getInfo/{id}"""
        return self.client.get(f"/customers/getInfo/{customer_id}")

    def get_customers(self, current_page: int, page_size: int):
        """[GET] /customers/getCustomers"""
        return self.client.get(
            "/customers/getCustomers",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def like_customers(
        self,
        like_name: str | None = None,
        like_address: str | None = None,
        start_time: str | None = None,
        end_time: str | None = None,
    ):
        """[GET] /customers/likeCustomers"""
        params = compact_params(
            likeName=like_name,
            likeAddress=like_address,
            startTime=start_time,
            endTime=end_time,
        )
        return self.client.get("/customers/likeCustomers", params=params)

    def add_merchant(self, payload: dict[str, Any]):
        """[POST] /merchants/addMerchant"""
        return self.client.post("/merchants/addMerchant", json=payload)

    def update_merchant(self, payload: dict[str, Any]):
        """[PUT] /merchants/updateMerchant"""
        return self.client.put("/merchants/updateMerchant", json=payload)

    def delete_merchant(self, merchant_id: int):
        """[DELETE] /merchants/deleteMerchant/{id}"""
        return self.client.delete(f"/merchants/deleteMerchant/{merchant_id}")

    def get_merchant_info(self, merchant_id: int):
        """[GET] /merchants/getInfo/{id}"""
        return self.client.get(f"/merchants/getInfo/{merchant_id}")

    def get_merchants(self, current_page: int, page_size: int):
        """[GET] /merchants/getMerchants"""
        return self.client.get(
            "/merchants/getMerchants",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def like_merchants(
        self,
        like_name: str | None = None,
        like_address: str | None = None,
        start_time: str | None = None,
        end_time: str | None = None,
    ):
        """[GET] /merchants/likeMerchants"""
        params = compact_params(
            likeName=like_name,
            likeAddress=like_address,
            startTime=start_time,
            endTime=end_time,
        )
        return self.client.get("/merchants/likeMerchants", params=params)

    def add_admin(self, payload: dict[str, Any]):
        """[POST] /admins/addAdmin"""
        return self.client.post("/admins/addAdmin", json=payload)

    def update_admin(self, payload: dict[str, Any]):
        """[PUT] /admins/updateAdmin"""
        return self.client.put("/admins/updateAdmin", json=payload)

    def delete_admin(self, admin_id: int):
        """[DELETE] /admins/deleteAdmin/{id}"""
        return self.client.delete(f"/admins/deleteAdmin/{admin_id}")

    def get_admin_info(self, admin_id: int):
        """[GET] /admins/getInfo/{id}"""
        return self.client.get(f"/admins/getInfo/{admin_id}")

    def get_admins(self, current_page: int | None = None, page_size: int | None = None):
        """[GET] /admins/getAdmins"""
        return self.client.get(
            "/admins/getAdmins",
            params=compact_params(currentPage=current_page, pageSize=page_size),
        )

    def like_admins(
        self,
        input_text: str | None = None,
        start_time: str | None = None,
        end_time: str | None = None,
    ):
        """[GET] /admins/likeAdmins"""
        params = compact_params(
            input=input_text,
            startTime=start_time,
            endTime=end_time,
        )
        return self.client.get("/admins/likeAdmins", params=params)
