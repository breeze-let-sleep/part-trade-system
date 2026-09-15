from typing import Any

from apis.base_api import BaseApi


class LoginApi(BaseApi):
    def get_code(self):
        """[GET] /code"""
        return self.client.get("/code")

    def register(self, payload: dict[str, Any]):
        """[POST] /register"""
        return self.client.post("/register", json=payload)

    def login(self, payload: dict[str, Any]):
        """[POST] /login"""
        return self.client.post("/login", json=payload)
