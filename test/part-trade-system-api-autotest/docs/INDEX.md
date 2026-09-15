# 项目上下文索引
<!-- maintained by: project-context-index -->

本索引用于帮助开发者和 AI 快速定位接口自动化测试工程中的关键代码，避免每次读取全部文件。

- 项目类型：Python + Pytest + Requests + Allure
- 接口文档：`../api-docs/default_OpenAPI.json`
- 接口覆盖：`ENDPOINT-COVERAGE.md`
- 验证说明：`../VALIDATION.md`
- 最近更新：2026-09-15

## 模块地图

| 模块 | 索引 | 主要路径 | 用途 |
| --- | --- | --- | --- |
| 配置与基础设施 | `modules/core.md` | `config/`、`common/`、`conftest.py`、`run.py` | 环境、请求、鉴权、日志、断言、运行入口 |
| 接口封装 | `modules/apis.md` | `apis/` | 49 个接口的 Python 封装 |
| 测试用例 | `modules/testcases.md` | `testcases/` | 接口断言和 Pytest 标记 |
| 测试数据 | `modules/data.md` | `data/` | 登录、用户、订单、零件和综合测试数据 |

## 常见修改入口

- 修改服务地址或鉴权方式：`config/config.yaml`、`common/request_util.py`
- 新增或调整接口：对应文件 `apis/*_api.py`
- 调整断言规则：`common/assertion.py`
- 调整测试用例：对应文件 `testcases/test_*.py`
- 调整测试数据：对应文件 `data/*.yaml`
- 检查接口覆盖：`tools/check_endpoint_coverage.py`

## 维护规则

1. 新增接口时，同时更新 API 封装、测试用例、数据和归属模块索引。
2. 修改配置文件时，更新 `modules/core.md`。
3. 修改接口封装时，更新 `modules/apis.md` 和 `ENDPOINT-COVERAGE.md`。
4. 修改测试用例时，更新 `modules/testcases.md`。
5. 修改测试数据时，更新 `modules/data.md`。
6. 完成代码修改后运行静态覆盖检查，并提醒使用者将索引与代码一起提交。
