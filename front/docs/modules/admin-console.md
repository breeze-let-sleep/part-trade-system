# 模块：admin-console

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/views/admin/`。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `adminList.vue` | 管理员列表、新增修改、模糊搜索和头像上传。 |
| `customerList.vue` | 顾客列表、新增修改、搜索和头像上传。 |
| `merchantList.vue` | 商家列表、新增修改、搜索和头像上传。 |
| `info.vue` | 当前管理员资料修改、删除和头像上传。 |
| `orderList.vue` | 已完成订单模糊查询。 |
| `operateLog.vue` | 操作日志分页展示。 |
| `report.vue` | Chart.js 报表统计可视化。 |

## 对外接口

- 页面通过 `api/admin.js`、`api/customer.js`、`api/merchant.js`、`api/order.js`、`api/logs.js`、`api/report.js` 访问后端。
- `report.vue` 直接依赖 `@vueuse/core` 的 `useTransition`，当前只在锁文件依赖树中间接出现。

## 依赖

- 依赖: `auth-routing`、`http-api`、Element Plus、Chart.js · 被依赖: 管理员角色路由。

## 典型修改场景

- 「管理员管理用户」→ 修改对应用户列表页和 `api/{admin,customer,merchant}.js`，检查后端身份模块。
- 「订单/日志筛选」→ 修改 `orderList.vue`、`operateLog.vue`，同步后端分页参数和响应字段。
- 「报表指标或图表」→ 修改 `report.vue` 与 `api/report.js`，同步 `ReportVO` 和报表 SQL。

## 延伸阅读

- [后端日志报表模块](../../../end/docs/modules/logs-reporting.md)
- [后端身份模块](../../../end/docs/modules/identity-users.md)

## 测试

- 位置: 无单测 · 运行: `npm run build`，并按管理员角色手工验证列表、上传、日志和报表。

## 注意事项

- 非 root 管理员的路由会动态移除 `AdminList`，权限逻辑位于 `store/user.js`。
- 用户列表表单字段很多，接口字段变更时要同时检查新增/编辑对话框和表格列。
