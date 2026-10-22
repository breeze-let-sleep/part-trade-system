# 模块：data-model

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/{pojo,mapper}` 与 `src/main/resources/com/hyltest/mapper/`。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `pojo/Result.java`、`pojo/PageResult.java` | 统一响应和分页响应结构。 |
| `pojo/entity/*.java` | 表实体：Admin、Customer、Merchant、Part、Order、OrderDetail、Contract、OperateLog 等。 |
| `pojo/entity/V*.java` | 数据库视图实体，用于跨表展示。 |
| `pojo/VO/*.java` | 登录、合同、报表等接口传输对象。 |
| `pojo/query/PartQuery.java` | 零件条件查询参数。 |
| `mapper/*.java` | 16 个 Mapper 接口（含视图 Mapper），业务层的数据访问边界。 |
| `resources/com/hyltest/mapper/*.xml` | 复杂 SQL、关联查询和视图查询实现。 |
| `../../../part_trade_system.sql` | 10 张核心表及 5 个视图的建库脚本。 |

## 对外接口

- 实体字段与数据库列通过 `map-underscore-to-camel-case: true` 映射。
- Mapper 接口是 Service 访问数据库的主要入口；视图 Mapper 直接承载跨表结果。
- `Result`/`PageResult` 是 Controller 与前端之间的主要数据结构。

## 依赖

- 依赖: MySQL、MyBatis、MyBatis-Plus、Lombok · 被依赖: 所有业务模块。

## 典型修改场景

- 「新增表字段」→ 同步修改 `part_trade_system.sql`、实体、VO、Mapper 接口/XML、Service 和前端表单。
- 「调整跨表查询」→ 优先修改对应 `resources/com/hyltest/mapper/*.xml`，再检查视图实体字段。
- 「改响应结构」→ 修改 `Result`/`PageResult` 或具体 VO，并回归所有角色页面。

## 延伸阅读

- [数据库脚本](../../../part_trade_system.sql)
- [仓库 README](../../../README.md)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`；数据库集成测试需要本机 MySQL 与初始化 SQL。

## 注意事项

- 先确认外键和删除级联/限制策略，再修改实体删除逻辑；订单、合同、库存存在明显联动。
- 视图实体名称以 `V` 开头，不要把它误当成可直接写入的基表实体。
