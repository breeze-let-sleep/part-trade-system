# 模块：app-shell

> updated: 93545f1 · maintained by: project-context-index

- 路径: `front/src/` 的应用入口、布局和通用组件。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `main.js` | 创建 Vue 应用，注册 Element Plus、中文语言包、图标、Router 和 Pinia。 |
| `App.vue` | 根路由出口。 |
| `layouts/index.vue` | 登录后主框架，展示角色、退出按钮、侧栏和内容路由。 |
| `layouts/sideMenu.vue` | 根据 Pinia `menus` 递归渲染侧边栏。 |
| `components/pagination.vue` | 通用 Element Plus 分页组件，支持页码/页大小双向绑定。 |
| `components/HelloWorld.vue` | 模板遗留示例组件，当前业务页面未依赖。 |
| `index.html`、`public/`、`src/assets/` | 应用壳、静态图片和资源。 |

## 对外接口

- `layouts/index.vue` 作为三种角色动态路由的父组件。
- `pagination.vue` 暴露 `total`、`pageSize`、`currentPage` 与 `change`/更新事件。

## 依赖

- 依赖: Vue、Element Plus、Vue Router、Pinia · 被依赖: 所有登录后角色页面。

## 典型修改场景

- 「修改全局头部/侧栏/退出逻辑」→ 修改 `layouts/index.vue`、`layouts/sideMenu.vue`。
- 「新增全局 UI 或分页行为」→ 修改 `src/components/`，再同步引用页面。
- 「调整 Element Plus 全局注册」→ 修改 `src/main.js`，确认图标和语言包仍可用。

## 延伸阅读

- [认证与路由模块](auth-routing.md)
- [前端 README](../../README.md)

## 测试

- 位置: 无测试目录 · 运行: `npm run build` 做构建校验，`npm run dev` 做手工验证。

## 注意事项

- 侧栏使用路由 `meta.title` 和 `meta.icon`，新增菜单必须提供这两个字段。
- `components/pagination.vue` 的注释示例使用大写文件名，实际引用时注意当前文件名为小写。
