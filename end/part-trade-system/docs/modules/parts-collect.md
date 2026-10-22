# 模块：parts-collect

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 的零件、商品浏览和商家收藏业务。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `controller/PartController.java` | `/parts` 零件分页、模糊查询、增改发布与删除。 |
| `controller/GoodsController.java` | `/goods` 商品浏览与在售商品搜索。 |
| `controller/CollectController.java` | `/collects` 顾客收藏商家的增删与查询。 |
| `service/{IPartService,IMerchantPartService,IVMerchantPartDetailService,ICollectMerchantService,IVMerchantCollectInfoService}.java` | 零件、商家零件关系和收藏业务接口。 |
| `service/impl/{PartServiceImpl,MerchantPartServiceImpl,VMerchantPartDetailServiceImpl,CollectMerchantServiceImpl,VMerchantCollectInfoServiceImpl}.java` | 对应业务实现。 |
| `mapper/{PartMapper,MerchantPartMapper,VMerchantPartDetailMapper,CollectMerchantMapper,VMerchantCollectInfoMapper}.java` | 零件/收藏数据访问。 |
| `resources/com/hyltest/mapper/{PartMapper,VMerchantPartDetailMapper,VMerchantCollectInfoMapper}.xml` | 零件与视图查询 SQL。 |

## 对外接口

- `/parts`: `getParts`、`likeParts`、`addPart`、`updatePart`、`publishPart/{id}/{isPublish}`、`deletePart/{id}`。
- `/goods`: `getGoods`、`likeGoods`。
- `/collects`: `addCollect/{customerId}/{merchantId}`、`removeCollect/{customerId}/{merchantId}`、`likeCollects`、`getCollects`。

## 依赖

- 依赖: `identity-users`、`data-model`、`orders-contracts`（下单扣库存） · 被依赖: 商家商品管理和顾客商店/收藏页面。

## 典型修改场景

- 「修改零件库存/上下架」→ 修改 `PartServiceImpl`、`PartMapper(.xml)`，同时检查订单取消/支付库存恢复逻辑。
- 「调整商品搜索条件」→ 修改 `GoodsController`、`VMerchantPartDetailServiceImpl` 与对应 XML。
- 「收藏商家功能」→ 修改 `CollectController`、`CollectMerchantServiceImpl` 和视图查询。

## 延伸阅读

- [数据模型模块](data-model.md)
- [前端商家工作台](../../../front/docs/modules/merchant-workspace.md)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`。

## 注意事项

- 库存变更必须考虑事务和并发，避免超卖；订单相关库存联动见 `orders-contracts`。
- `VMerchantPartDetail`、`VMerchantCollectInfo` 对应数据库视图，字段变更需同时检查 SQL 视图。
