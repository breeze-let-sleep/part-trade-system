from pathlib import Path

from apis.base_api import BaseApi


class MiscApi(BaseApi):
    def __init__(self, client, upload_mode: str = "multipart") -> None:
        super().__init__(client)
        self.upload_mode = upload_mode

    def get_report(self):
        """[GET] /report"""
        return self.client.get("/report")

    def get_logs(self, current_page: int, page_size: int):
        """[GET] /logs/getLogs"""
        return self.client.get(
            "/logs/getLogs",
            params={"currentPage": current_page, "pageSize": page_size},
        )

    def upload(self, file_path: str | Path):
        """[POST] /upload"""
        file_path = Path(file_path)
        if self.upload_mode == "query":
            return self.client.post("/upload", params={"file": str(file_path)})
        with file_path.open("rb") as file_stream:
            return self.client.post(
                "/upload",
                files={"file": (file_path.name, file_stream, "application/octet-stream")},
            )

    def chat(self, prompt: str, chat_id: str, cur_id: int):
        """[GET] /chat"""
        return self.client.get(
            "/chat",
            params={"prompt": prompt, "chatId": chat_id, "curId": cur_id},
            stream=True,
        )
