# 后端项目上下文索引

> 面向 AI agent 的后端导航文件。开始任务前先读本文件，按需阅读 `modules/` 中的模块文件与被引用文档；修改代码后更新对应模块文件。
> updated: 93545f1 · maintained by: project-context-index

## 1. 项目概览

- 定位: 零件交易系统后端，提供登录鉴权、用户/零件/订单/合同、报表、日志、AI 客服和文件上传 REST API。
- 技术栈: Java 17 · Spring Boot 3.4.5 · Spring MVC/AI/AOP · MyBatis + MyBatis-Plus · MySQL · Redis · JWT · Maven。
- 常用命令: build=`.\mvnw.cmd clean package` · test=`.\mvnw.cmd test` · lint=未配置 · run=`.\mvnw.cmd spring-boot:run`。
- 顶层结构: `part-trade-system/` 为 Maven 工程；`src/main/java/com/hyltest/` 为业务代码；`src/main/resources/` 为配置与 MyBatis XML；根目录 SQL 定义数据库结构和视图。
- 相关文档: 仓库根 [README.md](../../README.md) 说明系统功能、环境与运行步骤；[part_trade_system.sql](../../part_trade_system.sql) 是数据库建表/视图/测试数据来源。

## 2. 模块地图

| 模块 | 路径 | 职责 | 详情 | updated |
| --- | --- | --- | --- | --- |
| app-foundation | `src/main/java/com/hyltest/{config,filter,handler,exception,constant,utils}` | 启动、配置、鉴权过滤器、统一异常与基础设施 | [modules/app-foundation.md](modules/app-foundation.md) | 93545f1 |
| identity-users | `controller/{Login,Admin,Customer,Merchant}Controller.java` 及对应 service/mapper | 登录注册、三种用户角色 CRUD 与身份信息 | [modules/identity-users.md](modules/identity-users.md) | 93545f1 |
| parts-collect | `controller/{Part,Goods,Collect}Controller.java` 及对应 service/mapper | 零件供应、商品浏览、收藏与库存相关查询 | [modules/parts-collect.md](modules/parts-collect.md) | 93545f1 |
| orders-contracts | `controller/{Order,OrderDetail,Contract}Controller.java` 及对应 service/mapper | 订单创建/支付/流转/评价与电子合同签署 | [modules/orders-contracts.md](modules/orders-contracts.md) | 93545f1 |
| logs-reporting | `controller/{Log,Report}Controller.java`、`aop/`、对应 service/mapper | 管理员操作审计与交易统计报表 | [modules/logs-reporting.md](modules/logs-reporting.md) | 93545f1 |
| ai-upload | `controller/{AIChat,Upload}Controller.java`、`tools/`、`utils/AliyunOSS*` | Qwen 流式客服、工具调用与阿里云 OSS 上传 | [modules/ai-upload.md](modules/ai-upload.md) | 93545f1 |
| data-model | `pojo/`、`mapper/`、`resources/com/hyltest/mapper/` | 实体/VO/查询对象、Mapper 接口与 SQL 映射 | [modules/data-model.md](modules/data-model.md) | 93545f1 |

## 3. 全局约束与规范

- 业务 API 通常返回 `Result`，分页/跨表查询常返回 `PageResult`；新增接口应保持该响应约定。
- 鉴权由 `TokenFilter` + JWT 完成，前端通过 `token` 请求头传递；AI、报表等接口使用 Swagger `Authorization` 安全声明。
- 数据访问以 MyBatis Mapper 为主，复杂查询和视图查询在 `src/main/resources/com/hyltest/mapper/*.xml` 中实现。
- 外部配置依赖环境变量：`QWEN_API_KEY`、`ENDPOINT`、`BUCKET_NAME`、`REGION`；本地还需 MySQL、Redis 配置。
- 不要提交真实密钥、数据库密码或 OSS 凭据；数据库结构变更应同步检查 SQL、实体和 Mapper XML。

## 4. 索引使用说明

- 先按需求选择模块，再读取对应 `modules/*.md`；需要接口细节时直接查看 Controller、Service 接口和 Mapper XML。
- 新增业务模块时补充模块文件并登记到第 2 节；变更历史可记录在 `docs/CONTEXT-CHANGELOG.md`（当前未启用）。
