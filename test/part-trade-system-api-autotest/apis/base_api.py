from common.request_util import RequestUtil


class BaseApi:
    def __init__(self, client: RequestUtil) -> None:
        self.client = client
