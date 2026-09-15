# 接口自动化测试验证文档

## 1. 本轮范围

本轮只完成代码、测试用例、测试数据和验证文档，未执行以下操作：

- 未启动后端服务
- 未发送任何 HTTP 接口请求
- 未执行 Pytest 接口用例
- 未连接或修改 MySQL 数据库
- 未上传文件、调用 AI 接口或生成真实测试数据

## 2. 静态验证结果

验证日期：2026-09-15

| 检查项 | 结果 | 说明 |
| --- | --- | --- |
| OpenAPI 接口总数 | 49 | 来源：`api-docs/default_OpenAPI.json` |
| 接口封装数 | 49 | 与 OpenAPI 路径和 HTTP 方法完全一致 |
| 接口覆盖差异 | 0 | 无缺失、无文档外接口 |
| Python 语法编译 | 通过 | 使用 `python -m compileall -q .` |
| 测试函数数量 | 49 | 与 49 个文档接口一一对应 |
| YAML 文件清单 | 5 | 已生成配置和测试数据文件 |
| YAML 解析执行 | 未执行 | 当前验证机未安装 PyYAML，安装依赖后需复验 |
| Pytest 收集 | 未执行 | 当前验证机未安装 pytest，且用户要求暂不运行时执行 |
| 接口运行验证 | 未执行 | 后端未启动，按用户要求由用户后续执行 |

接口覆盖结果可通过以下命令复查：

```powershell
python tools/check_endpoint_coverage.py --openapi api-docs/default_OpenAPI.json
```

预期输出：

```text
文档接口数：49
封装接口数：49
结果：接口封装与 OpenAPI 文档完全一致。
```

## 3. 覆盖清单

按接口模块统计：

| 模块 | 接口数 |
| --- | ---: |
| 登录相关 | 3 |
| 顾客、供应商、管理员 | 18 |
| 订单、订单详情 | 9 |
| 零件 | 6 |
| 商品 | 2 |
| 收藏 | 4 |
| 合同 | 3 |
| 报表、日志、上传、AI | 4 |
| 合计 | 49 |

完整清单见 `docs/ENDPOINT-COVERAGE.md`。

## 4. 后续运行验证步骤

### 4.1 安装依赖

```powershell
python -m pip install -r requirements.txt
```

### 4.2 配置环境

确认 `config/config.yaml` 中：

- `base_url` 指向可访问的测试服务器
- `token_header` 为 `token`
- `auth.auto_fetch_code` 与验证码获取方式一致
- `auth.password_mode` 与登录密码处理方式一致
- `upload.mode` 与上传接口实现一致
- `database` 指向允许测试的数据库

### 4.3 先执行只读用例

```powershell
python run.py --env test --marker "not write"
```

验收标准：

- 登录成功并获取 JWT
- 所有只读接口返回 HTTP 200
- 统一 Result 接口满足 `code=1`、`msg=success`
- 需要数据的查询接口返回非 null 数据
- AI 对话流式响应至少返回 1 字节内容

### 4.4 验证写接口

确认数据库可回滚或可使用专用测试库后，将 `test_data.run_write_tests` 改为 `true`：

```powershell
python run.py --env test -m write
```

验收标准：

- 新增、修改、删除接口均返回成功 Result
- 支付、签名、发货、评价接口均返回成功 Result
- 上传接口返回成功 Result
- 执行前后核对数据库数据，确认无残留测试数据

### 4.5 生成报告

```powershell
python run.py --env test --allure
```

验收标准：

- `reports/allure-results` 包含原始结果
- `reports/allure-report` 可正常打开并查看用例详情

## 5. 安全开关

- 所有新增、修改、删除、支付、签名、发货、评价、上传用例默认跳过。
- `test_data.run_write_tests` 默认为 `false`。
- `database.enabled` 默认为 `false`。
- 数据库工具不会在导入或测试收集阶段创建连接。
- 日志不会主动打印 JWT；请求日志只记录 URL、参数、状态码和响应预览。

## 6. 已采用的实现约定

- 统一返回结构按“后端所有非 AI 接口均返回 Result”实现。
- 登录 JWT 从 `Result.data` 提取，并使用请求头 `token` 传递。
- `/code` 返回的验证码按字符串处理。
- 密码默认按明文发送，可通过配置切换 MD5。
- 上传默认采用 multipart/form-data，可通过配置切换 query binary。
- AI 对话按流式响应处理，断言内容非空，不限定具体事件格式。
- 查询接口只断言 Result 成功以及 data 非 null，不绑定后端分页字段结构。
- 写接口使用 YAML 中的测试 ID 和实体数据，运行前应由使用者按测试库实际数据调整。

## 7. 建议的正式验收记录

用户后续执行时，建议在本文档末尾追加：

```text
执行时间：
执行环境：
后端版本：
只读用例：通过数 / 总数
写接口用例：通过数 / 总数
失败用例：
数据库残留检查：
Allure 报告路径：
备注：
```
