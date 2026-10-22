# 模块：http-api

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/utils/request.js`、`front/vite.config.js` 和 `front/src/api/`。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `utils/request.js` | Axios 实例，baseURL `/api`，请求时注入 `localStorage.jwt` 到 `token` 头，401 时清理登录态。 |
| `vite.config.js` | `@` 别名与 `/api` 到 `http://localhost:8080` 的开发代理。 |
| `api/login.js` | 登录、注册、验证码。 |
| `api/{admin,customer,merchant}.js` | 三种用户资料的 CRUD/搜索请求。 |
| `api/{part,goods,collects}.js` | 零件、商品、收藏请求。 |
| `api/{order,orderDetail,contract}.js` | 订单、订单流程、合同请求。 |
| `api/{logs,report}.js` | 操作日志和报表请求。 |

## 对外接口

- 所有 API 文件默认使用 `@/utils/request`，并导出页面调用的函数。
- 请求头字段名固定为 `token`；后端 Filter 与前端拦截器必须保持一致。
- 开发环境 `/api` 前缀由 Vite 代理移除，生产部署需由网关或反向代理提供等价规则。

## 依赖

- 依赖: Axios、浏览器 `localStorage`、Vue Router 状态 · 被依赖: 全部业务页面。

## 典型修改场景

- 「新增后端接口」→ 在对应 `src/api/*.js` 增加导出函数，再从页面导入，不在页面直接创建 Axios 实例。
- 「统一请求头/超时/错误处理」→ 修改 `utils/request.js`，同时检查后端 `TokenFilter` 和 `Result`。
- 「修改后端地址或代理」→ 修改 `vite.config.js`；生产环境需同步部署配置。

## 延伸阅读

- [后端基础模块](../../../end/docs/modules/app-foundation.md)
- [后端接口总览](../../../end/docs/INDEX.md)

## 测试

- 位置: 无测试目录 · 运行: `npm run build`；接口联调需同时启动后端 `8080`。

## 注意事项

- `utils/request.js` 的 401 分支调用了 `useUserStore()`，但当前文件未显式导入该函数；修改认证错误处理时需先核对实际运行状态。
- 页面中存在 `action="/api/upload"` 的 Element Plus 上传调用，变更代理规则时必须一并检查。
