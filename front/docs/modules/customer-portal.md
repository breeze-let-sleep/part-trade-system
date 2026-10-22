# 模块：customer-portal

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/views/customer/` 和共享首页 `front/src/views/home.vue`。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `home.vue` | 三种角色共享的首页展示。 |
| `info.vue` | 顾客资料修改、删除和头像上传。 |
| `shop.vue` | 在售零件搜索、下单和收藏商家。 |
| `favoriteMerchant.vue` | 收藏商家列表、搜索和取消收藏。 |
| `message.vue` | 待支付订单消息和支付操作。 |
| `orderDetail.vue` | 顾客进行中/已完成订单、收货评价。 |
| `contractList.vue` | 顾客合同列表、详情和签署。 |
| `robot.vue` | 调用 `/chat` 的流式智能客服页面，使用 `marked` 渲染文本。 |

## 对外接口

- 使用 `api/goods.js`、`api/order.js`、`api/orderDetail.js`、`api/collects.js`、`api/contract.js`、`api/customer.js`。
- 智能客服直接请求后端 `/chat?prompt=&chatId=&curId=`，不走常规 API 文件。

## 依赖

- 依赖: `auth-routing`、`http-api`、Element Plus、marked · 被依赖: 顾客角色路由。

## 典型修改场景

- 「商品搜索/下单/收藏」→ 修改 `shop.vue` 及 `api/goods.js`、`api/order.js`、`api/collects.js`。
- 「订单消息/收货/评价」→ 修改 `message.vue`、`orderDetail.vue` 与后端订单模块。
- 「合同签署」→ 修改 `contractList.vue`、`api/contract.js` 与合同后端接口。
- 「智能客服」→ 修改 `robot.vue`，同步检查后端 `AIChatController` 和 AI 工具。

## 延伸阅读

- [后端 AI 上传模块](../../../end/docs/modules/ai-upload.md)
- [后端订单合同模块](../../../end/docs/modules/orders-contracts.md)

## 测试

- 位置: 无单测 · 运行: `npm run build`，按顾客角色手工验证购买、收藏、支付、评价和客服流式响应。

## 注意事项

- 智能客服依赖后端环境变量和外部模型服务，不能仅凭前端构建通过判断功能可用。
- 订单、收藏和合同请求都包含当前用户 ID，调整登录态字段时要同时修改页面参数。
