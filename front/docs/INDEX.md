# 前端项目上下文索引

> 面向 AI agent 的前端导航文件。开始任务前先读本文件，按需阅读 `modules/` 中的模块文件与被引用文档；修改代码后更新对应模块文件。
> updated: 93545f1 · maintained by: project-context-index

## 1. 项目概览

- 定位: 零件交易系统 Vue 3 单页应用，按管理员、商家、顾客三种角色展示不同工作台。
- 技术栈: Vue 3 Composition API · Vue Router 4 · Pinia · Element Plus · Axios · Chart.js · Vite 6 · npm。
- 常用命令: build=`npm run build` · test=未配置 · lint=未配置 · run=`npm run dev`（默认 `http://localhost:5173/`）。
- 顶层结构: `src/api/` 请求封装；`src/router/` 静态路由、角色路由和守卫；`src/store/` 登录状态；`src/views/` 页面；`src/layouts/` 主框架；`public/` 静态资源。
- 相关文档: [README.md](../README.md) 说明前端功能和技术栈；仓库根 [README.md](../../README.md) 提供前后端整体运行步骤。

## 2. 模块地图

| 模块 | 路径 | 职责 | 详情 | updated |
| --- | --- | --- | --- | --- |
| app-shell | `src/{main.js,App.vue}`、`src/layouts/`、`src/components/` | 应用挂载、Element Plus 初始化、主布局、菜单和通用分页 | [modules/app-shell.md](modules/app-shell.md) | 93545f1 |
| auth-routing | `src/router/`、`src/store/user.js`、`src/utils/jwt.js`、登录/注册页 | JWT 登录态、角色路由安装、导航守卫和认证视图 | [modules/auth-routing.md](modules/auth-routing.md) | 93545f1 |
| http-api | `src/utils/request.js`、`vite.config.js`、`src/api/` | Axios 实例、JWT 注入、代理和后端 API 封装 | [modules/http-api.md](modules/http-api.md) | 93545f1 |
| admin-console | `src/views/admin/` | 管理员用户/订单/日志/报表管理页面 | [modules/admin-console.md](modules/admin-console.md) | 93545f1 |
| merchant-workspace | `src/views/merchant/` | 商家资料、零件供应、合同和订单发货页面 | [modules/merchant-workspace.md](modules/merchant-workspace.md) | 93545f1 |
| customer-portal | `src/views/customer/`、`src/views/home.vue` | 顾客商品购买、收藏、合同、消息、订单详情和 AI 客服 | [modules/customer-portal.md](modules/customer-portal.md) | 93545f1 |

## 3. 全局约束与规范

- 所有 HTTP 请求经 `src/utils/request.js`，浏览器端 base URL 为 `/api`，Vite 开发服务器将其代理到 `http://localhost:8080` 并移除 `/api` 前缀。
- 角色值为 `0` 管理员、`1` 商家、`2` 顾客；动态路由和菜单由 `src/store/user.js` 按角色安装。
- JWT 仅用于前端状态恢复和路由控制，前端不校验签名；后端仍负责真正的权限校验。
- 页面统一使用 Element Plus；业务 API 优先新增/修改 `src/api/*.js`，避免在页面中散落 Axios 调用。
- 修改接口路径、请求参数或响应结构时，必须同步检查后端对应 Controller 与页面字段。

## 4. 索引使用说明

- 先读本文件，再按功能选择模块；页面级细节直接查看对应 `src/views/**/*.vue`。
- 新增角色页面时同时修改 `router/roleRouter.js`、`store/user.js`、页面目录和 API 封装。
- 新增模块时补充模块文件并登记到第 2 节；变更历史可记录在 `docs/CONTEXT-CHANGELOG.md`（当前未启用）。
