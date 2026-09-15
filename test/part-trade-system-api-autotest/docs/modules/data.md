# 测试数据
<!-- maintained by: project-context-index -->

- 状态：active
- 最近更新：2026-09-15
- 关键路径：`data/`

## 文件职责

| 文件 | 内容 |
| --- | --- |
| `login_data.yaml` | 登录、注册、验证码 |
| `user_data.yaml` | 顾客、供应商、管理员及 ID |
| `order_data.yaml` | 订单、订单详情、合同签名 |
| `part_data.yaml` | 零件和过滤条件 |
| `misc_data.yaml` | 报表、日志、商品、上传、AI 对话 |
| `upload_sample.txt` | 上传接口的示例文件 |

## 数据约定

- 字符串中的 `${suffix}` 会在运行时替换为时间戳后缀。
- ID、电话号码和关联字段必须与目标测试库一致。
- 密码默认发送 YAML 原文；启用 MD5 后登录密码会自动转换。
- 修改关联 ID 时，应同时检查订单、收藏和查询用例。

## 典型修改场景

- 更换登录账号：修改 `login_data.yaml`。
- 更换详情查询 ID：修改对应资源的 `id`。
- 扩大或缩小模糊查询：修改各资源的 `filter`。
- 调整 AI 对话：修改 `misc_data.yaml` 的 `chat`。
