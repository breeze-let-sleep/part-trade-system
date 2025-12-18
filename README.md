# 项目本地运行说明文档

## 一、前端代码运行说明

### 1. 依赖环境

- Node.js 版本：v18.16.0

- npm（包管理工具）版本：9.6.6

### 2. 本地运行步骤

1. 将代码文件拷贝到本地文件夹下

2. 使用 VS Code 打开并进入项目根目录

3. 打开命令行窗口，执行以下命令安装所需依赖：

    ```Bash
    npm install
    ```

4. 依赖安装完成后，执行以下命令启动开发服务器：

    ```Bash
    npm run dev
    ```

5. 启动成功后，可在浏览器访问以下地址：

    ```Plain Text
     http://localhost:5173/
    ```

## 二、后端代码运行说明

### 1. 前置准备

#### （1）阿里平台相关配置

1. 登录阿里平台：[https://www.aliyun.com/](https://www.aliyun.com/)

2. OSS 对象存储配置（环境变量中设置以下参数）：

    ```Plain Text
    ENDPOINT=https://oss-cn-beijing.aliyuncs.com;
	BUCKET_NAME=你的bucketName;
	REGION=你的bucket所在地区（上面的endpoint也需要对应）
    ```

    注：OSS 配置具体详细过程此处省略

3. 大模型服务平台百炼配置：

    - 创建个人密钥

    - 在环境变量中配置以下参数：

        ```Plain Text
        QWEN_API_KEY=你的api-key
        ```

#### （2）数据库配置

1. 创建数据库 `part_trade_system`

2. 通过提供的 SQL 文件创建对应数据库表（含以下测试数据）：

    - 管理员账号：root，密码：1234

    - 顾客账号：顾客小明，密码：1234

    - 供应商账号：供应商小明，密码：1234

### 2. 本地运行步骤

1. 将代码文件拷贝到本地文件夹下

2. 使用 IntelliJ IDEA 打开并进入项目

3. 修改 `application.yml` 文件中的数据库相关配置

4. 打开 `PartTradeSystemApplication.java` 文件，启动项目即可
