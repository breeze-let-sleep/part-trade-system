from apis.base_api import BaseApi
from common.request_util import compact_params


class GoodsApi(BaseApi):
    def get_goods(self, cursor: int, size: int):
        """[GET] /goods/getGoods"""
        return self.client.get(
            "/goods/getGoods",
            params={"cursor": cursor, "size": size},
        )

    def like_goods(self, input_merchant: str, input_part: str):
        """[GET] /goods/likeGoods"""
        params = compact_params(inputMerchant=input_merchant, inputPart=input_part)
        return self.client.get("/goods/likeGoods", params=params)
