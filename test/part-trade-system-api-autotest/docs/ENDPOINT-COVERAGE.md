# 接口覆盖清单
<!-- maintained by: project-context-index -->

本清单由 `api-docs/default_OpenAPI.json` 与 `apis/*_api.py` 中的方法标记核对生成。

- 文档接口总数：49
- 已封装接口总数：49
- 覆盖结果：100%

| HTTP | 路径 | 说明 | 接口封装 | 测试文件 |
| --- | --- | --- | --- | --- |
| DELETE | `/admins/deleteAdmin/{id}` | 删除管理员信息 | `apis/user_api.py` | `testcases/test_user.py` |
| DELETE | `/collects/removeCollect/{customerId}/{merchantId}` | 取消收藏 | `apis/collect_api.py` | `testcases/test_collect.py` |
| DELETE | `/customers/deleteCustomer/{id}` | 删除顾客信息 | `apis/user_api.py` | `testcases/test_user.py` |
| DELETE | `/merchants/deleteMerchant/{id}` | 删除供应商信息 | `apis/user_api.py` | `testcases/test_user.py` |
| DELETE | `/orders/removeOrder/{orderId}` | 删除订单 | `apis/order_api.py` | `testcases/test_order.py` |
| DELETE | `/parts/deletePart/{id}` | 删除零件信息 | `apis/part_api.py` | `testcases/test_part.py` |
| GET | `/admins/getAdmins` | 分页获取管理员列表 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/admins/getInfo/{id}` | 获取单个管理员信息 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/admins/likeAdmins` | 模糊查询管理员 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/chat` | AI对话 | `apis/misc_api.py` | `testcases/test_misc.py` |
| GET | `/code` | 获取验证码 | `apis/login_api.py` | `testcases/test_login.py` |
| GET | `/collects/getCollects` | 分页请求收藏的供应商列表 | `apis/collect_api.py` | `testcases/test_collect.py` |
| GET | `/collects/likeCollects` | 模糊查询已收藏的供应商 | `apis/collect_api.py` | `testcases/test_collect.py` |
| GET | `/contracts/getOrderDetail/{orderId}` | 查询订单详情 | `apis/contract_api.py` | `testcases/test_contract.py` |
| GET | `/contracts/getOrders` | 分页获取当前用户未签名订单 | `apis/contract_api.py` | `testcases/test_contract.py` |
| GET | `/customers/getCustomers` | 分页请求顾客列表 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/customers/getInfo/{id}` | 获取单个顾客信息 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/customers/likeCustomers` | 模糊查询顾客列表 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/details/getEndOrders` | 分页获取当前用户已经结束的订单 | `apis/order_api.py` | `testcases/test_order.py` |
| GET | `/details/getProcessingOrders` | 分页获取当前用户处理中的订单 | `apis/order_api.py` | `testcases/test_order.py` |
| GET | `/goods/getGoods` | 分页获取商品 | `apis/goods_api.py` | `testcases/test_goods.py` |
| GET | `/goods/likeGoods` | 模糊查询商品 | `apis/goods_api.py` | `testcases/test_goods.py` |
| GET | `/logs/getLogs` | 获取操作日志列表 | `apis/misc_api.py` | `testcases/test_misc.py` |
| GET | `/merchants/getInfo/{id}` | 获取单个供应商信息 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/merchants/getMerchants` | 分页获取供应商列表 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/merchants/likeMerchants` | 模糊查询供应商列表 | `apis/user_api.py` | `testcases/test_user.py` |
| GET | `/orders/getEndLikeOrders` | 模糊查看已完成的所有订单 | `apis/order_api.py` | `testcases/test_order.py` |
| GET | `/orders/getPaidMessage` | 获取待支付订单信息 | `apis/order_api.py` | `testcases/test_order.py` |
| GET | `/parts/getParts` | 分页获取零件列表 | `apis/part_api.py` | `testcases/test_part.py` |
| GET | `/parts/likeParts` | 模糊查询零件 | `apis/part_api.py` | `testcases/test_part.py` |
| GET | `/report` | 获取报表数据 | `apis/misc_api.py` | `testcases/test_misc.py` |
| POST | `/admins/addAdmin` | 新增管理员信息 | `apis/user_api.py` | `testcases/test_user.py` |
| POST | `/collects/addCollect/{customerId}/{merchantId}` | 添加供应商为收藏 | `apis/collect_api.py` | `testcases/test_collect.py` |
| POST | `/customers/addCustomer` | 新增顾客信息 | `apis/user_api.py` | `testcases/test_user.py` |
| POST | `/login` | 用户登录 | `apis/login_api.py` | `testcases/test_login.py` |
| POST | `/merchants/addMerchant` | 新增供应商信息 | `apis/user_api.py` | `testcases/test_user.py` |
| POST | `/orders/addOrder` | 购买零件 | `apis/order_api.py` | `testcases/test_order.py` |
| POST | `/parts/addPart` | 新增零件信息 | `apis/part_api.py` | `testcases/test_part.py` |
| POST | `/register` | 用户注册 | `apis/login_api.py` | `testcases/test_login.py` |
| POST | `/upload` | 上传图片到阿里云 | `apis/misc_api.py` | `testcases/test_misc.py` |
| PUT | `/admins/updateAdmin` | 修改管理员信息 | `apis/user_api.py` | `testcases/test_user.py` |
| PUT | `/contracts/signature` | 签名订单 | `apis/contract_api.py` | `testcases/test_contract.py` |
| PUT | `/customers/updateCustomer` | 修改顾客信息 | `apis/user_api.py` | `testcases/test_user.py` |
| PUT | `/details/deliver/{id}` | 发货 | `apis/order_api.py` | `testcases/test_order.py` |
| PUT | `/details/evaluateOrder` | 收货并评价订单 | `apis/order_api.py` | `testcases/test_order.py` |
| PUT | `/merchants/updateMerchant` | 修改供应商信息 | `apis/user_api.py` | `testcases/test_user.py` |
| PUT | `/orders/payOrder/{orderId}` | 支付订单 | `apis/order_api.py` | `testcases/test_order.py` |
| PUT | `/parts/publishPart/{id}/{isPublish}` | 修改零件上线信息 | `apis/part_api.py` | `testcases/test_part.py` |
| PUT | `/parts/updatePart` | 修改零件信息 | `apis/part_api.py` | `testcases/test_part.py` |
