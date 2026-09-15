# 配置与基础设施
<!-- maintained by: project-context-index -->

- 状态：active
- 最近更新：2026-09-15
- 关键路径：`config/`、`common/`、`conftest.py`、`run.py`、`pytest.ini`

## 文件职责

| 文件 | 职责 |
| --- | --- |
| `config/config.yaml` | 环境地址、鉴权、上传模式、数据库和写测试开关 |
| `config/settings.py` | 项目绝对路径和默认配置路径 |
| `common/request_util.py` | requests.Session、JWT header、超时、日志 |
| `common/assertion.py` | Result 响应、HTTP 状态、流式响应断言 |
| `common/yaml_util.py` | YAML 和环境配置加载 |
| `common/logger.py` | 控制台与滚动文件日志 |
| `common/data_util.py` | 动态数据、MD5、变量渲染 |
| `common/db_util.py` | 可选 MySQL 查询、执行和事务 |
| `conftest.py` | 配置、客户端、登录、鉴权、数据、数据库 fixture |
| `run.py` | Pytest 和 Allure 入口 |

## 典型修改场景

- 切换环境：修改 `config/config.yaml` 或运行 `python run.py --env <name>`。
- 修改鉴权：调整 `auth_token` fixture 或 `RequestUtil`。
- 新增公共断言：在 `common/assertion.py` 增加函数并在测试中调用。
- 使用数据库：先将 `database.enabled` 改为 `true`，通过 `database` fixture 显式调用。

## 运行入口

```powershell
python run.py --env test --marker "not write"
python run.py --env test --allure
```
