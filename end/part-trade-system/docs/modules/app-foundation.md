# 模块：app-foundation

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 下的启动、配置、过滤器、异常、常量和工具基础层。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `PartTradeSystemApplication.java` | Spring Boot 启动类。 |
| `config/{CommonConfiguration,CorsConfig,RedisConfiguration,Knife4jConfig,Knife4jOpenApiCustomizer}.java` | ChatClient、CORS、Redis、Knife4j/OpenAPI 等基础设施配置。 |
| `filter/TokenFilter.java` | 从请求头读取 JWT，恢复当前用户上下文并拦截受保护请求。 |
| `handler/GlobalExceptionHandler.java` | 将业务异常/参数异常转换为统一响应。 |
| `exception/*.java` | 登录、账号、增删改、删除限制等异常类型。 |
| `constant/{MessageConstant,SystemConstants}.java` | 响应消息与系统常量。 |
| `utils/{JWTUtils,MD5Utils,CurrentHolder,TimeUtils}.java` | Token、密码摘要、请求上下文、时间工具。 |

## 对外接口

- `TokenFilter` 保护后端接口并依赖请求头 `token`。
- `CurrentHolder` 为下游 Service 提供当前登录用户信息。
- `GlobalExceptionHandler` 统一包装异常响应。

## 依赖

- 依赖: Spring Boot、JJWT、Redis、Spring AI、Knife4j · 被依赖: 所有 Controller/Service。

## 典型修改场景

- 「调整登录拦截/放行规则」→ 修改 `filter/TokenFilter.java`、`utils/JWTUtils.java`，同步检查前端 `utils/request.js`。
- 「新增全局配置或统一错误响应」→ 修改 `config/`、`handler/`、`pojo/Result.java`。
- 「修改中间件连接配置」→ 修改 `src/main/resources/application.yml`，不要把密钥写入代码。

## 延伸阅读

- [仓库 README](../../../README.md)
- [application.yml](../../part-trade-system/src/main/resources/application.yml)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`。

## 注意事项

- `application.yml` 中的数据库密码仅适合本地示例，生产环境应改为环境变量或安全配置。
- 修改鉴权行为时同时验证前端动态路由和后端 Filter，两端都依赖 JWT 中的角色信息。
