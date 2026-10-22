# 模块：auth-routing

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/router/`、`front/src/store/user.js`、`front/src/utils/jwt.js` 及认证页面。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `router/index.js` | 创建 Web History 路由，注册 `/login`、`/register` 和兜底 404。 |
| `router/roleRouter.js` | 定义管理员、商家、顾客的懒加载路由树和菜单元数据。 |
| `router/guard.js` | 首次进入恢复 JWT/角色路由；未登录跳转登录页，已登录避免落入 404。 |
| `store/user.js` | Pinia 用户状态、JWT 解析、角色路由动态安装、菜单生成和退出。 |
| `utils/jwt.js` | 仅解析 JWT payload，不验证签名。 |
| `views/login.vue`、`views/register.vue` | 登录与注册表单，调用 `/api/login`、`/api/register`、`/api/code`。 |

## 对外接口

- 角色路由 ID: `0` 管理员、`1` 商家、`2` 顾客。
- `useUserStore().setUserInfo(jwt)`：登录成功后写入用户并安装路由。
- `useUserStore().restore()`：刷新时恢复用户和角色路由。
- `useUserStore().logout()`：清理 JWT、重置状态并刷新页面。

## 依赖

- 依赖: `http-api`、Element Plus、Vue Router、Pinia · 被依赖: `app-shell` 和所有角色页面。

## 典型修改场景

- 「新增角色页面」→ 修改 `router/roleRouter.js`、`store/user.js`，必要时更新后端 JWT role 约定。
- 「调整登录/注册流程」→ 修改 `views/login.vue`、`views/register.vue`、`api/login.js` 和后端 `LoginController`。
- 「修改登录拦截」→ 修改 `router/guard.js`，同时检查 `utils/request.js` 的 401 处理。

## 延伸阅读

- [HTTP/API 模块](http-api.md)
- [后端身份模块](../../../end/docs/modules/identity-users.md)

## 测试

- 位置: 无前端单测 · 运行: `npm run build`；登录、刷新、退出和三种角色菜单需手工回归。

## 注意事项

- 角色路由会在 `store/user.js` 中动态添加，重复登录/恢复时需防止路由重复或 404 顺序错误。
- `utils/jwt.js` 不验证签名，前端角色信息不能作为安全边界。
