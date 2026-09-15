# 接口封装
<!-- maintained by: project-context-index -->

- 状态：active
- 最近更新：2026-09-15
- 关键路径：`apis/`
- 覆盖结果：49 / 49

## 模块划分

| 文件 | 接口数 | 范围 |
| --- | ---: | --- |
| `login_api.py` | 3 | 验证码、注册、登录 |
| `user_api.py` | 18 | 顾客、供应商、管理员 |
| `order_api.py` | 9 | 订单管理、订单详情 |
| `part_api.py` | 6 | 零件 |
| `goods_api.py` | 2 | 商品 |
| `collect_api.py` | 4 | 收藏 |
| `contract_api.py` | 3 | 合同 |
| `misc_api.py` | 4 | 报表、日志、上传、AI |

## 约定

- 每个公开方法使用文档字符串第一行标记 `[METHOD] /path`。
- 路径参数直接拼接到 URL 路径。
- 查询参数通过 `compact_params` 移除 None。
- JSON 请求体使用 `json=payload`。
- 接口方法只负责请求，不直接断言。
- 统一鉴权由 `RequestUtil` 注入 `token` 请求头。

## 典型修改场景

- 新增接口：增加方法并保留方法标记，随后更新 `docs/ENDPOINT-COVERAGE.md`。
- 修改请求格式：优先修改对应 `*_api.py`，不要改测试用例。
- 修改上传方式：调整 `MiscApi.upload` 和 `config.upload.mode`。

## 覆盖校验

```powershell
python tools/check_endpoint_coverage.py --openapi api-docs/default_OpenAPI.json
```
