# 模块：identity-users

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 的登录、管理员、顾客、商家业务。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `controller/LoginController.java` | `/login`、`/register`、`/code`，登录 JWT 与注册验证码。 |
| `controller/AdminController.java` | `/admins` 下管理员查询、分页、模糊查询和 CRUD。 |
| `controller/CustomerController.java` | `/customers` 下顾客查询、分页、模糊查询和 CRUD。 |
| `controller/MerchantController.java` | `/merchants` 下商家查询、分页、模糊查询和 CRUD。 |
| `service/{LoginService,IAdminService,ICustomerService,IMerchantService}.java` | 身份与用户资料业务接口。 |
| `service/impl/{LoginServiceImpl,AdminServiceImpl,CustomerServiceImpl,MerchantServiceImpl}.java` | 对应业务实现。 |
| `mapper/{LoginMapper,AdminMapper,CustomerMapper,MerchantMapper}.java` | 用户数据访问入口。 |
| `resources/com/hyltest/mapper/{AdminMapper,CustomerMapper,MerchantMapper}.xml` | 用户查询/CRUD SQL。 |

## 对外接口

- `POST /login`、`POST /register`、`GET /code`。
- `/admins`、`/customers`、`/merchants` 下分别提供列表、详情、模糊查询、新增、修改、删除。
- JWT payload 约定包含 `id`、`name`、`role`，角色值为 `0` 管理员、`1` 商家、`2` 顾客。

## 依赖

- 依赖: `app-foundation`、`data-model` · 被依赖: 前端登录页、用户列表页和所有角色路由。

## 典型修改场景

- 「新增角色或权限」→ 修改 `LoginServiceImpl`、`UserVO`、`TokenFilter`，同步修改前端 `store/user.js` 和 `router/roleRouter.js`。
- 「增加用户字段」→ 修改实体、Mapper/XML、Service、Controller 与前端 API/表单。
- 「调整登录/注册校验」→ 修改 `LoginController`、`LoginServiceImpl`、`LoginMapper`，检查 Redis 验证码逻辑。

## 延伸阅读

- [数据模型模块](data-model.md)
- [前端认证与路由模块](../../../front/docs/modules/auth-routing.md)

## 测试

- 位置: `src/test/java/com/hyltest/`（JWT、MD5、Redis 等基础测试） · 运行: `.\mvnw.cmd test`。

## 注意事项

- 前端只解析 JWT 作为路由展示依据，签名校验必须以后端为准。
- 管理员、商家、顾客的表结构和字段变化会影响多个列表页，修改前检查 SQL 外键。
