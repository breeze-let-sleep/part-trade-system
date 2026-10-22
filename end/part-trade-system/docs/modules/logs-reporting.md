# 模块：logs-reporting

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 的操作日志与统计报表业务。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `controller/LogController.java` | `/logs/getLogs` 分页查询操作日志。 |
| `controller/ReportController.java` | `/report` 获取统计报表数据。 |
| `anno/AdminLog.java` | 标记需要记录管理员操作的方法。 |
| `aop/OperateLogAspect.java` | 对标注方法记录操作日志。 |
| `service/{IOperateLogService,IVOperateLogWithAdminService,ReportService}.java` | 日志和报表业务接口。 |
| `service/impl/{OperateLogServiceImpl,VOperateLogWithAdminServiceImpl,ReportServiceImpl}.java` | 对应业务实现。 |
| `mapper/{OperateLogMapper,VOperateLogWithAdminMapper}.java` | 日志数据访问。 |
| `resources/com/hyltest/mapper/VOperateLogWithAdminMapper.xml` | 日志与管理员关联视图查询。 |

## 对外接口

- `GET /logs/getLogs`：管理员操作日志分页查询。
- `GET /report`：返回报表统计和图表数据。

## 依赖

- 依赖: `app-foundation`、`identity-users`、`data-model` · 被依赖: 管理员操作日志页和报表页。

## 典型修改场景

- 「新增审计操作」→ 在目标 Controller/Service 方法上使用 `@AdminLog`，检查 `OperateLogAspect` 和日志表字段。
- 「调整报表指标」→ 修改 `ReportServiceImpl`、`ReportVO` 和相关查询；同步前端 `views/admin/report.vue`。
- 「日志筛选/分页」→ 修改 `LogController`、`OperateLogServiceImpl`、对应 Mapper XML。

## 延伸阅读

- [数据模型模块](data-model.md)
- [前端管理员控制台](../../../front/docs/modules/admin-console.md)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`。

## 注意事项

- AOP 记录日志时不要记录密码、JWT、API Key 等敏感信息。
- 报表数据跨越多个业务表，表结构调整后需回归验证统计 SQL 与视图。
