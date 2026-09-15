from __future__ import annotations

from contextlib import contextmanager
from typing import Any, Iterable

from common.logger import get_logger

try:
    import pymysql
except ImportError:  # 依赖未安装时仍允许导入测试模块
    pymysql = None


class DatabaseUtil:
    """可选的数据库辅助类，仅供需要准备或清理测试数据时使用。"""

    def __init__(self, config: dict) -> None:
        if pymysql is None:
            raise RuntimeError("未安装 PyMySQL，无法使用数据库辅助功能")
        self.config = config
        self.logger = get_logger()
        self.connection = pymysql.connect(
            host=config.get("host", "localhost"),
            port=int(config.get("port", 3306)),
            user=config.get("username", "root"),
            password=config.get("password", ""),
            database=config.get("database"),
            charset=config.get("charset", "utf8mb4"),
            autocommit=True,
            cursorclass=pymysql.cursors.DictCursor,
        )

    def fetch_one(self, sql: str, params: Iterable[Any] | None = None) -> dict | None:
        with self.connection.cursor() as cursor:
            cursor.execute(sql, params or ())
            return cursor.fetchone()

    def fetch_all(self, sql: str, params: Iterable[Any] | None = None) -> list[dict]:
        with self.connection.cursor() as cursor:
            cursor.execute(sql, params or ())
            return list(cursor.fetchall())

    def execute(self, sql: str, params: Iterable[Any] | None = None) -> int:
        with self.connection.cursor() as cursor:
            affected = cursor.execute(sql, params or ())
        self.logger.info("SQL 执行完成，影响行数=%s", affected)
        return affected

    @contextmanager
    def transaction(self):
        try:
            self.connection.begin()
            yield self.connection
            self.connection.commit()
        except Exception:
            self.connection.rollback()
            raise

    def close(self) -> None:
        self.connection.close()
