from typing import Any

from apis.base_api import BaseApi
from common.request_util import compact_params


class PartApi(BaseApi):
    def add_part(self, payload: dict[str, Any]):
        """[POST] /parts/addPart"""
        return self.client.post("/parts/addPart", json=payload)

    def update_part(self, payload: dict[str, Any]):
        """[PUT] /parts/updatePart"""
        return self.client.put("/parts/updatePart", json=payload)

    def publish_part(self, part_id: int, is_publish: int):
        """[PUT] /parts/publishPart/{id}/{isPublish}"""
        return self.client.put(f"/parts/publishPart/{part_id}/{is_publish}")

    def delete_part(self, part_id: int):
        """[DELETE] /parts/deletePart/{id}"""
        return self.client.delete(f"/parts/deletePart/{part_id}")

    def get_parts(self, current_page: int, page_size: int):
        """[GET] /parts/getParts"""
        return self.client.get(
            "/parts/getParts",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def like_parts(
        self,
        part_id: int | None = None,
        part_name: str | None = None,
        weight: float | None = None,
        color: int | None = None,
        start_time: str | None = None,
        end_time: str | None = None,
        is_publish: int | None = None,
    ):
        """[GET] /parts/likeParts"""
        params = compact_params(
            partId=part_id,
            partName=part_name,
            weight=weight,
            color=color,
            startTime=start_time,
            endTime=end_time,
            isPublish=is_publish,
        )
        return self.client.get("/parts/likeParts", params=params)
