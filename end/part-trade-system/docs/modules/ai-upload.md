# 模块：ai-upload

> updated: 93545f1 · maintained by: project-context-index

- 路径: `end/part-trade-system/src/main/java/com/hyltest/` 的 AI 客服和 OSS 上传能力。

## 核心文件

| 文件 | 职责 |
| --- | --- |
| `controller/AIChatController.java` | `GET /chat` 接收 prompt/chatId/curId，流式返回 Qwen 内容。 |
| `tools/PurchasePartsTools.java` | AI 工具调用：查询在售零件、最新零件、购买生成订单。 |
| `pojo/query/PartQuery.java` | AI 零件查询参数及工具描述。 |
| `controller/UploadController.java` | `POST /upload` 上传图片到阿里云 OSS。 |
| `utils/{AliyunOSSOperator,AliyunOSSProperties}.java` | OSS 客户端与 `aliyun.oss` 配置绑定。 |
| `config/CommonConfiguration.java` | ChatClient 等 AI 相关 Bean 配置。 |

## 对外接口

- `GET /chat?prompt=&chatId=&curId=`：流式文本响应，依赖会话内存和当前用户 ID。
- `POST /upload`：接收 Multipart 图片并返回 OSS URL。
- `PurchasePartsTools` 通过 `@Tool` 暴露查询/下单能力给 Spring AI。

## 依赖

- 依赖: Spring AI、百炼兼容 OpenAI 接口、阿里云 OSS、`parts-collect`、`orders-contracts` · 被依赖: 顾客智能客服页和各头像上传表单。

## 典型修改场景

- 「修改客服提示词/会话策略」→ 修改 `AIChatController.java`、`config/CommonConfiguration.java`，检查 `chatId` 与用户 ID 传递。
- 「新增 AI 工具能力」→ 修改 `tools/PurchasePartsTools.java` 和相关 Service，保持工具参数可序列化。
- 「替换对象存储或上传路径」→ 修改 `AliyunOSSOperator`、`AliyunOSSProperties` 和 `application.yml`。

## 延伸阅读

- [前端顾客门户](../../../front/docs/modules/customer-portal.md)
- [application.yml](../../part-trade-system/src/main/resources/application.yml)

## 测试

- 位置: `src/test/java/com/hyltest/` · 运行: `.\mvnw.cmd test`；AI/OSS 联调需配置环境变量和外部服务。

## 注意事项

- `QWEN_API_KEY`、OSS 端点、Bucket 和区域均为外部依赖，不要把真实值写入索引或代码。
- AI 工具可以触达下单逻辑，修改工具权限或参数时必须复核用户身份和库存校验。
