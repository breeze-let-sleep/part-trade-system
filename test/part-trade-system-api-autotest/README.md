# 零件交易系统接口自动化测试

基于 Pytest + Requests + Allure 实现的接口自动化测试项目，覆盖接口文档中的全部 49 个接口。

## 目录说明

```text
part-trade-system-api-autotest/
├── config/                 环境配置、全局常量
├── common/                 请求、YAML、日志、断言、数据库工具
├── apis/                   接口封装层
├── data/                   测试数据
├── testcases/              Pytest 测试用例
├── logs/                   日志输出
├── reports/                Allure 原始结果与 HTML 报告
├── docs/                   项目索引、接口覆盖和验证文档
├── tools/                  静态校验工具
├── conftest.py             全局 fixture
├── pytest.ini              Pytest 配置
├── requirements.txt        依赖列表
├── run.py                  测试入口
└── VALIDATION.md           静态验证与后续执行说明
```

## 快速开始

1. 安装依赖：

```powershell
python -m pip install -r requirements.txt
```

2. 修改 `config/config.yaml`：

- `environments.test.base_url`：被测服务地址，默认为 `http://localhost:8080`
- `database`：测试数据库连接参数
- `auth.password_mode`：按后端实际密码规则设置为 `plain` 或 `md5`
- `upload.mode`：按后端上传接口实现设置为 `multipart` 或 `query`
- `test_data.run_write_tests`：默认 `false`，会跳过新增、修改、删除、支付、签名、发货、评价和上传用例

3. 准备测试数据：

- `data/login_data.yaml`：登录账号和验证码
- `data/user_data.yaml`：顾客、供应商、管理员及查询条件
- `data/order_data.yaml`：订单、订单详情、合同签名
- `data/part_data.yaml`：零件及查询条件
- `data/misc_data.yaml`：报表、日志、商品、上传、AI 对话

4. 执行只读回归测试：

```powershell
python run.py --env test --marker "not write"
```

5. 在独立测试库中启用写接口测试：

```yaml
test_data:
  run_write_tests: true
```

然后执行：

```powershell
python run.py --env test
```

6. 生成 Allure HTML 报告：

```powershell
python run.py --env test --allure
```

需要本机已安装 Allure CLI。原始结果写入 `reports/allure-results`，HTML 报告输出到 `reports/allure-report`。

## 鉴权说明

`/login` 成功时 `Result.data` 为 JWT 字符串。登录后，`RequestUtil` 会在后续请求中自动添加：

```http
token: <JWT>
```

默认登录流程会先调用 `/code` 获取验证码，再调用 `/login`。如使用固定验证码，可设置：

```yaml
auth:
  auto_fetch_code: false
```

## 接口模块

| 模块 | 覆盖范围 |
| --- | --- |
| `apis/login_api.py` | 验证码、注册、登录 |
| `apis/user_api.py` | 顾客、供应商、管理员 |
| `apis/order_api.py` | 订单、订单详情 |
| `apis/part_api.py` | 零件 |
| `apis/goods_api.py` | 商品 |
| `apis/collect_api.py` | 收藏 |
| `apis/contract_api.py` | 合同 |
| `apis/misc_api.py` | 报表、日志、上传、AI 对话 |

完整映射见 `docs/ENDPOINT-COVERAGE.md`。

## 数据库工具

`common/db_util.py` 提供可选的 MySQL 查询、执行和事务封装。默认不启用，也不会自动读写数据库。需要准备或清理测试数据时，将 `database.enabled` 改为 `true`，并在测试 fixture 中显式使用。

## 静态校验

```powershell
python tools/check_endpoint_coverage.py --openapi api-docs/default_OpenAPI.json
python -m compileall -q .
```

详细验证步骤和已知假设见 `VALIDATION.md`。
