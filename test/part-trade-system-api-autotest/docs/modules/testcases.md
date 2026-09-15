# 测试用例
<!-- maintained by: project-context-index -->

- 状态：active
- 最近更新：2026-09-15
- 关键路径：`testcases/`

## 文件职责

| 文件 | 覆盖范围 | 断言重点 |
| --- | --- | --- |
| `test_login.py` | 验证码、登录、注册 | JWT 非空、Result 成功 |
| `test_user.py` | 顾客、供应商、管理员 | 增删改查、模糊查询 |
| `test_order.py` | 订单、订单详情 | 下单、支付、删除、发货、评价、查询 |
| `test_part.py` | 零件 | 新增、更新、上下线、删除、分页、模糊查询 |
| `test_goods.py` | 商品 | 分页和模糊查询 |
| `test_collect.py` | 收藏 | 收藏、取消、分页、模糊查询 |
| `test_contract.py` | 合同 | 未签订单、详情、签名 |
| `test_misc.py` | 报表、日志、上传、AI | 非 null、Result 成功、流式内容非空 |

## 标记

- `smoke`：验证码和登录
- `regression`：常规回归
- `write`：修改服务端数据，默认跳过
- `contract`：合同接口
- `stream`：AI 流式接口

## 典型修改场景

- 调整请求数据：优先修改 `data/*.yaml`。
- 增加业务断言：在测试中读取 `assert_result` 返回值后追加断言。
- 编写写接口用例：必须添加 `@pytest.mark.write` 并依赖 `write_enabled`。
