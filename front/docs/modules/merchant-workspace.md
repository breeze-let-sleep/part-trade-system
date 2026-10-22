# 模块：merchant-workspace

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/views/merchant/`。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `info.vue` | 商家资料查看修改、删除和头像上传。 |
| `productList.vue` | 零件列表、搜索、新增修改、发布/下架和删除。 |
| `contractList.vue` | 商家订单合同列表、详情查看与签署。 |
| `orderDetail.vue` | 商家进行中/已完成订单与发货操作。 |

## 对外接口

- 使用 `api/merchant.js`、`api/part.js`、`api/contract.js`、`api/order.js`、`api/orderDetail.js`。
- 库存上下架调用 `/parts/publishPart/{id}/{isPublish}`，发货调用 `/details/deliver/{id}`。

## 依赖

- 依赖: `auth-routing`、`http-api`、Element Plus · 被依赖: 商家角色路由。

## 典型修改场景

- 「修改零件表单/库存」→ 修改 `productList.vue`、`api/part.js`，并检查后端零件模块和库存事务。
- 「发货流程」→ 修改 `orderDetail.vue`、`api/orderDetail.js` 与后端订单详情实现。
- 「合同签署」→ 修改 `contractList.vue`、`api/contract.js` 与合同后端接口。

## 延伸阅读

- [后端零件收藏模块](../../../end/docs/modules/parts-collect.md)
- [后端订单合同模块](../../../end/docs/modules/orders-contracts.md)

## 测试

- 位置: 无单测 · 运行: `npm run build`，按商家角色手工验证零件、合同和发货流程。

## 注意事项

- 零件删除新增、库存变更和订单状态在多个页面共享，修改数据结构时需回归顾客商店和管理员订单列表。
