# 模块：orders-contracts

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 的订单、订单详情和合同业务。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `controller/OrderController.java` | `/orders` 下单、待支付消息、支付、删除和已完成订单查询。 |
| `controller/OrderDetailController.java` | `/details` 进行中/已完成订单、评价和发货。 |
| `controller/ContractController.java` | `/contracts` 合同列表、合同详情和签署。 |
| `service/{IOrderService,IOrderDetailService,IContractService}.java` | 订单、详情、合同业务接口。 |
| `service/impl/{OrderServiceImpl,OrderDetailServiceImpl,ContractServiceImpl}.java` | 对应业务实现，包含状态流转和事务逻辑。 |
| `mapper/{OrderMapper,OrderDetailMapper,ContractMapper,VOrderCompleteInfoMapper,VOrderDetailCompleteMapper}.java` | 订单与合同数据访问。 |
| `resources/com/hyltest/mapper/{OrderMapper,OrderDetailMapper,VOrderCompleteInfoMapper,VOrderDetailCompleteMapper}.xml` | 订单关联查询和完成态视图查询。 |

## 对外接口

- `/orders`: `addOrder`、`getPaidMessage`、`payOrder/{orderId}`、`removeOrder/{orderId}`、`getEndLikeOrders`。
- `/details`: `getProcessingOrders`、`getEndOrders`、`evaluateOrder`、`deliver/{id}`。
- `/contracts`: `getOrders`、`getOrderDetail/{orderId}`、`signature`。
- 状态语义以 `Order`、`OrderDetail` 实体和 Service 实现为准：待付款、已付款/待发货、已发货、已收货/评价等阶段。

## 依赖

- 依赖: `identity-users`、`parts-collect`、`data-model` · 被依赖: 商家订单页、顾客消息/合同页、管理员订单列表。

## 典型修改场景

- 「调整订单状态流转」→ 修改 `OrderController`、`OrderServiceImpl`、`OrderMapper(.xml)`，检查前端 `api/order.js`、订单页面。
- 「发货/收货/评价」→ 修改 `OrderDetailController`、`OrderDetailServiceImpl` 与订单详情视图。
- 「合同签署」→ 修改 `ContractController`、`ContractServiceImpl`、合同实体和前端合同页。

## 延伸阅读

- [数据模型模块](data-model.md)
- [前端 API 层](../../../front/docs/modules/http-api.md)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`。

## 注意事项

- 订单支付、取消、库存扣减/恢复必须保持数据库事务一致；修改时优先检查 `@Transactional` 和库存 SQL。
- 订单状态字段同时被多角色页面读取，接口响应结构变化会波及管理员、商家和顾客页面。
