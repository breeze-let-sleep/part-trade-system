-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: part_trade_system
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id，以0开头，共6位',
  `name` varchar(15) NOT NULL COMMENT '管理员名字',
  `password` varchar(200) NOT NULL COMMENT '密码（MD5加密后密文）',
  `phone` char(11) DEFAULT NULL COMMENT '电话号码',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像的地址',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  `update_time` datetime NOT NULL COMMENT '修改时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'root','81dc9bdb52d04dc20036dbd8313ed055','13800138001','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/12/f639882a-098a-4a46-845f-5eedd2df34c6.jpg','2025-11-01 09:00:00','2025-12-14 21:27:42'),(2,'小明','81dc9bdb52d04dc20036dbd8313ed055','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(3,'小黑','e10adc3949ba59abbe56e057f20f883e','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(4,'小红','e10adc3949ba59abbe56e057f20f883e','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(5,'李白','e10adc3949ba59abbe56e057f20f883e','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(6,'赵云','e10adc3949ba59abbe56e057f20f883e','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(7,'关羽','e10adc3949ba59abbe56e057f20f883e','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(8,'奥特曼','81dc9bdb52d04dc20036dbd8313ed055','13900139000','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-02 10:30:00','2025-11-02 10:30:00'),(9,'张飞','81dc9bdb52d04dc20036dbd8313ed055','11223344553','','2025-12-05 16:51:20','2025-12-14 19:22:49'),(14,'李四','81dc9bdb52d04dc20036dbd8313ed055','22334455667','','2025-12-05 17:01:08','2025-12-05 17:01:08'),(15,'小a','81dc9bdb52d04dc20036dbd8313ed055','11223344556','','2025-12-05 17:03:23','2025-12-05 17:03:23'),(16,'小b','81dc9bdb52d04dc20036dbd8313ed055','11223344556','','2025-12-05 17:03:37','2025-12-05 17:03:37'),(17,'小c','81dc9bdb52d04dc20036dbd8313ed055','11223344556','','2025-12-05 17:04:43','2025-12-05 17:04:43');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect_merchant`
--

DROP TABLE IF EXISTS `collect_merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect_merchant` (
  `merchant_id` int NOT NULL COMMENT '供应商id，以1开头，共6位',
  `customer_id` int NOT NULL COMMENT '顾客id，以2开头，共6位',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`merchant_id`,`customer_id`),
  KEY `fk_collect_customer` (`customer_id`),
  CONSTRAINT `fk_collect_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_collect_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏供应商表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect_merchant`
--

LOCK TABLES `collect_merchant` WRITE;
/*!40000 ALTER TABLE `collect_merchant` DISABLE KEYS */;
INSERT INTO `collect_merchant` VALUES (100000,200000,'2025-11-07 09:45:00'),(100000,200002,'2025-12-14 17:01:06'),(100001,200001,'2025-11-08 16:20:00'),(100002,200002,'2025-12-14 19:52:12');
/*!40000 ALTER TABLE `collect_merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '合同id',
  `order_id` int NOT NULL COMMENT '订单id',
  `merchant_signature` varchar(15) DEFAULT NULL COMMENT '供应商签名',
  `customer_signature` varchar(15) DEFAULT NULL COMMENT '顾客签名',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  KEY `fk_contract_order` (`order_id`),
  CONSTRAINT `fk_contract_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单合同表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,1,'诚信供应商','张三','2025-11-10 10:30:00'),(2,2,NULL,NULL,'2025-11-11 14:30:00'),(3,6,'商家签名','小明','2025-11-29 10:31:34'),(4,7,NULL,NULL,'2025-12-14 20:11:51'),(6,9,NULL,NULL,'2025-12-14 21:32:40'),(7,16,NULL,NULL,'2025-12-18 17:01:37');
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id，以2开头，共6位',
  `name` varchar(15) NOT NULL COMMENT '顾客名字',
  `password` varchar(200) NOT NULL COMMENT '密码（MD5加密后密文）',
  `phone` char(11) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  `update_time` datetime NOT NULL COMMENT '修改时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=200008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='顾客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (200000,'张三','e10adc3949ba59abbe56e057f20f883e','13500135003','北京市朝阳区XX小区','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-05 11:00:00','2025-11-05 11:00:00'),(200001,'李四','e10adc3949ba59abbe56e057f20f883e','13400134004','上海市浦东新区XX路','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-06 15:30:00','2025-11-06 15:30:00'),(200002,'顾客小明','81dc9bdb52d04dc20036dbd8313ed055','13400134005','上海市浦东新区凤凰路','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','2025-11-06 15:30:00','2025-12-18 09:55:35'),(200004,'小发','81dc9bdb52d04dc20036dbd8313ed055','12345678901','','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/12/f6789f8c-baa0-46ff-bf32-76c7b53ce62d.png','2025-12-06 10:28:15','2025-12-06 10:32:22'),(200006,'注册new','81dc9bdb52d04dc20036dbd8313ed055','12345678901',NULL,NULL,'2025-12-14 19:26:12','2025-12-14 21:29:37');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id，以1开头，共6位',
  `name` varchar(15) NOT NULL COMMENT '供应商名字',
  `password` varchar(200) NOT NULL COMMENT '密码（MD5加密后密文）',
  `phone` char(11) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `description` varchar(200) DEFAULT NULL COMMENT '公司简介',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  `update_time` datetime NOT NULL COMMENT '修改时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='供应商表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (100000,'诚信零件供应商','e10adc3949ba59abbe56e057f20f883e','13700137001','广东省深圳市宝安区XX工业园','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','专业生产精密机械零件，10年行业经验','2025-10-20 08:15:00','2025-10-20 08:15:00'),(100001,'恒通五金配件','e10adc3949ba59abbe56e057f20f883e','13600136002','浙江省宁波市鄞州区XX大道','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/06/d2c0b790-cdaf-4d3e-9a10-27c2d2eca9b6.jpg','主营不锈钢零件、冲压件，支持定制','2025-10-25 14:20:00','2025-10-25 14:20:00'),(100002,'供应商小明','81dc9bdb52d04dc20036dbd8313ed055','13600136003','金湾区','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/12/ecb092be-96e3-42c1-9c26-95f570ab1afe.png','匠心传承的公司','2025-11-23 20:20:38','2025-12-16 19:27:27'),(100004,'哇哈哈哈有限公司','81dc9bdb52d04dc20036dbd8313ed055','12345678901','竹9-206','https://java-web-hyl.oss-cn-beijing.aliyuncs.com/2025/12/4cee2f84-6024-4b69-b6be-ebdcafebe2a8.jpg','源于1999年的老品牌','2025-12-14 19:23:50','2025-12-14 19:24:46');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant_part`
--

DROP TABLE IF EXISTS `merchant_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant_part` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `merchant_id` int NOT NULL COMMENT '供应商id，以1开头，共6位',
  `part_id` int NOT NULL COMMENT '零件id',
  `is_publish` tinyint(1) DEFAULT '0' COMMENT '是否发布上线（0:否;1:是）',
  PRIMARY KEY (`id`),
  KEY `fk_merchant_part_merchant` (`merchant_id`),
  KEY `fk_merchant_part_part` (`part_id`),
  CONSTRAINT `fk_merchant_part_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_merchant_part_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='供应商零件管理（商品表）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_part`
--

LOCK TABLES `merchant_part` WRITE;
/*!40000 ALTER TABLE `merchant_part` DISABLE KEYS */;
INSERT INTO `merchant_part` VALUES (1,100000,1,1),(2,100000,2,1),(3,100001,3,1),(4,100000,4,0),(6,100002,6,0),(9,100002,9,0),(11,100002,11,0),(12,100002,12,0),(13,100002,13,1),(14,100002,14,1),(15,100002,15,0),(16,100002,16,0);
/*!40000 ALTER TABLE `merchant_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '操作id',
  `admin_id` int NOT NULL COMMENT 'id，以0开头，共6位',
  `method` varchar(100) NOT NULL COMMENT '操作方法描述',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  KEY `fk_operate_log_admin` (`admin_id`),
  CONSTRAINT `fk_operate_log_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` VALUES (4,2,'审核供应商【诚信零件供应商】注册','2025-10-20 08:30:00'),(5,1,'处理零件【精密齿轮】库存调整申请','2025-10-10 10:00:00'),(6,2,'查看订单统计数据（2025年11月）','2025-11-15 16:45:00'),(7,1,'root操作了AdminController.deleteAdmin方法','2025-12-05 20:55:24'),(8,1,'root操作了AdminController.deleteAdmin方法','2025-12-05 21:03:22'),(9,1,'root操作了MerchantController.addMerchant方法','2025-12-05 21:32:59'),(10,1,'root操作了AdminController.updateAdmin方法','2025-12-14 19:19:58'),(11,1,'root操作了AdminController.updateAdmin方法','2025-12-14 19:20:21'),(12,1,'root操作了CustomerController.addCustomer方法','2025-12-14 19:21:11'),(13,1,'root操作了CustomerController.updateCustomer方法','2025-12-14 19:21:33'),(14,1,'root操作了CustomerController.deleteCustomer方法','2025-12-14 19:21:52'),(15,1,'root操作了AdminController.updateAdmin方法','2025-12-14 19:22:49'),(16,1,'root操作了AdminController.addAdmin方法','2025-12-14 19:23:10'),(17,1,'root操作了AdminController.deleteAdmin方法','2025-12-14 19:23:18'),(18,1,'root操作了MerchantController.addMerchant方法','2025-12-14 19:23:50'),(19,1,'root操作了MerchantController.updateMerchant方法','2025-12-14 19:24:46'),(20,1,'root操作了MerchantController.deleteMerchant方法','2025-12-14 19:24:57'),(23,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:15:00'),(24,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:16:21'),(25,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:17:07'),(26,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:17:43'),(27,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:19:15'),(28,1,'root操作了AdminController.updateAdmin方法','2025-12-14 21:27:42'),(29,1,'root操作了CustomerController.updateCustomer方法','2025-12-14 21:29:37'),(30,1,'root操作了CustomerController.addCustomer方法','2025-12-15 21:06:32'),(31,1,'root操作了CustomerController.updateCustomer方法','2025-12-15 21:06:48'),(32,1,'root操作了CustomerController.updateCustomer方法','2025-12-15 21:08:49'),(33,1,'root操作了CustomerController.deleteCustomer方法','2025-12-15 21:09:03'),(34,1,'root操作了AdminController.updateAdmin方法','2025-12-15 21:09:27'),(35,1,'root操作了AdminController.updateAdmin方法','2025-12-15 21:10:44'),(36,1,'root操作了AdminController.deleteAdmin方法','2025-12-15 21:10:56'),(37,1,'root操作了MerchantController.addMerchant方法','2025-12-15 21:11:27'),(38,1,'root操作了MerchantController.updateMerchant方法','2025-12-15 21:18:03'),(39,1,'root操作了MerchantController.deleteMerchant方法','2025-12-15 21:18:11'),(40,1,'root操作了MerchantController.updateMerchant方法','2025-12-16 19:27:27');
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `merchant_id` int NOT NULL COMMENT '供应商id，以1开头，共6位',
  `customer_id` int NOT NULL COMMENT '顾客id，以2开头，共6位',
  `part_id` int NOT NULL COMMENT '零件id',
  `amount` int NOT NULL COMMENT '交易数量，单位：个',
  `total_price` int NOT NULL COMMENT '交易金额，单位：元',
  `is_success` int DEFAULT '0' COMMENT '是否成功（0：不成功；1：已签名，成功）',
  `is_paid` int DEFAULT '0' COMMENT '顾客是否付款（0：未付；1：已付）',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  KEY `fk_order_merchant` (`merchant_id`),
  KEY `fk_order_customer` (`customer_id`),
  KEY `fk_order_part` (`part_id`),
  CONSTRAINT `fk_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,100000,200000,1,10,1200,1,1,'2025-08-10 10:00:00'),(2,100001,200001,3,5,425,0,0,'2025-09-11 14:30:00'),(3,100000,200000,1,5,600,1,0,'2025-10-11 14:30:00'),(4,100000,200000,4,100,500,1,0,'2025-11-11 14:30:00'),(5,100002,200002,2,10,350,1,1,'2025-11-11 14:30:00'),(6,100002,200002,2,10,350,1,1,'2025-12-12 14:30:00'),(7,100002,200002,14,5,115,0,0,'2025-12-14 20:11:51'),(9,100002,200002,14,6,138,0,0,'2025-12-14 21:32:40'),(16,100000,200002,2,10,350,0,0,'2025-12-18 17:01:36');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_order_insert` AFTER INSERT ON `order` FOR EACH ROW BEGIN
    -- 向合同表插入对应记录，初始签名为空
    INSERT INTO contract (order_id, merchant_signature, customer_signature, create_time)
    VALUES (NEW.id, NULL, NULL, NOW());
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_after_order_paid` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
    -- 当is_paid从0变为1时，在order_detail中插入记录
    IF NEW.is_paid = 1 AND OLD.is_paid = 0 THEN
        -- 检查是否已存在该order_id的记录，避免重复插入
        IF NOT EXISTS (SELECT 1 FROM order_detail WHERE order_id = NEW.id) THEN
            INSERT INTO order_detail (order_id, update_time)
            VALUES (NEW.id, NOW());
        END IF;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单流程id',
  `order_id` int NOT NULL COMMENT '订单id',
  `is_deliver` int DEFAULT '0' COMMENT '是否发货（0：未发货；1：已发货-运输中）',
  `is_take` int DEFAULT '0' COMMENT '是否收到货物（0：未收到；1：已收到）',
  `star` int DEFAULT NULL COMMENT '星级评价（1~5星）',
  `evaluate` varchar(200) DEFAULT NULL COMMENT '评价内容',
  `update_time` datetime NOT NULL COMMENT '修改时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  KEY `fk_order_detail_order` (`order_id`),
  CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,1,1,5,'零件质量很好，交货及时，非常满意！','2025-11-12 15:00:00'),(2,2,0,0,NULL,NULL,'2025-11-11 14:30:00'),(3,5,1,1,5,'推荐','2025-12-14 20:59:37'),(5,6,1,1,5,'好耶，推荐','2025-12-15 21:03:27');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `merchant_id` int DEFAULT NULL COMMENT '供应商id',
  `name` varchar(15) NOT NULL COMMENT '名称',
  `color` int DEFAULT (-(1)) COMMENT '颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）',
  `weight` double(6,2) DEFAULT (-(1)) COMMENT '重量，单位kg',
  `description` varchar(100) DEFAULT '' COMMENT '描述',
  `inventory` int NOT NULL COMMENT '库存，单位个',
  `single_price` int NOT NULL COMMENT '单价，单位元',
  `create_time` datetime NOT NULL COMMENT '创建时间(YYYY-MM-DD HH:MM:SS)',
  `update_time` datetime NOT NULL COMMENT '修改时间(YYYY-MM-DD HH:MM:SS)',
  PRIMARY KEY (`id`),
  KEY `fk_part_merchant_id` (`merchant_id`),
  CONSTRAINT `fk_part_merchant_id` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='零件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (1,100000,'精密齿轮',3,0.50,'模数2，齿数20，不锈钢材质',500,120,'2025-10-10 09:30:00','2025-10-10 09:30:00'),(2,100001,'轴承套',6,0.30,'内径20mm，外径30mm，发黑处理',1000,35,'2025-10-12 10:15:00','2025-10-12 10:15:00'),(3,100000,'连接轴',1,0.80,'长度50mm，直径10mm，45号钢',800,85,'2025-10-15 11:20:00','2025-10-15 11:20:00'),(4,100000,'螺母',2,0.10,'长度20mm，直径20mm，内径10mm，45号钢',5000,5,'2025-10-15 11:20:00','2025-10-15 11:20:00'),(6,100002,'bbb',2,0.20,'描述',100,1,'2025-12-06 11:09:04','2025-12-06 11:09:06'),(9,100002,'ccc',1,0.40,'描述',100,1,'2025-12-06 11:09:04','2025-12-06 11:09:06'),(11,100002,'烦烦烦',3,0.90,'24k钛合金',500,23,'2025-12-06 15:11:05','2025-12-06 15:11:05'),(12,100002,'哇哈哈',3,0.90,'24k钛合金',500,23,'2025-12-06 15:11:05','2025-12-06 15:11:05'),(13,100002,'80大锤',6,0.90,'24k钛合金',500,23,'2025-12-06 15:11:05','2025-12-06 15:30:58'),(14,100002,'40小锤',6,0.90,'24k钛合金',500,23,'2025-12-06 15:11:05','2025-12-06 15:11:05'),(15,100002,'钢珠',6,0.90,'24k钛合金',500,23,'2025-12-06 15:11:05','2025-12-06 15:11:05'),(16,100002,'测试',3,1.10,'简简单单',800,6,'2025-12-14 19:47:40','2025-12-14 19:47:40');
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_after_part_insert` AFTER INSERT ON `part` FOR EACH ROW BEGIN
    INSERT INTO merchant_part (merchant_id, part_id)
    VALUES (NEW.merchant_id, NEW.id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_auto_unpublish_on_inventory_zero` AFTER UPDATE ON `part` FOR EACH ROW BEGIN
    -- 判断库存是否小于等于0
    IF NEW.inventory <= 0 THEN
        -- 更新对应商品的发布状态为0（未发布）
        UPDATE merchant_part
        SET is_publish = 0
        WHERE part_id = NEW.id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_after_part_delete` BEFORE DELETE ON `part` FOR EACH ROW BEGIN
    DELETE FROM merchant_part
    WHERE part_id = OLD.id AND merchant_id = OLD.merchant_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `v_merchant_collect_info`
--

DROP TABLE IF EXISTS `v_merchant_collect_info`;
/*!50001 DROP VIEW IF EXISTS `v_merchant_collect_info`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_merchant_collect_info` AS SELECT 
 1 AS `merchant_id`,
 1 AS `name`,
 1 AS `phone`,
 1 AS `address`,
 1 AS `avatar`,
 1 AS `description`,
 1 AS `customer_id`,
 1 AS `create_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_merchant_part_detail`
--

DROP TABLE IF EXISTS `v_merchant_part_detail`;
/*!50001 DROP VIEW IF EXISTS `v_merchant_part_detail`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_merchant_part_detail` AS SELECT 
 1 AS `merchant_id`,
 1 AS `merchant_name`,
 1 AS `part_id`,
 1 AS `part_name`,
 1 AS `color`,
 1 AS `weight`,
 1 AS `description`,
 1 AS `inventory`,
 1 AS `single_price`,
 1 AS `create_time`,
 1 AS `update_time`,
 1 AS `id`,
 1 AS `is_publish`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_operate_log_with_admin`
--

DROP TABLE IF EXISTS `v_operate_log_with_admin`;
/*!50001 DROP VIEW IF EXISTS `v_operate_log_with_admin`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_operate_log_with_admin` AS SELECT 
 1 AS `id`,
 1 AS `admin_id`,
 1 AS `method`,
 1 AS `create_time`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_order_complete_info`
--

DROP TABLE IF EXISTS `v_order_complete_info`;
/*!50001 DROP VIEW IF EXISTS `v_order_complete_info`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_order_complete_info` AS SELECT 
 1 AS `order_id`,
 1 AS `merchant_id`,
 1 AS `customer_id`,
 1 AS `part_id`,
 1 AS `amount`,
 1 AS `total_price`,
 1 AS `is_success`,
 1 AS `is_paid`,
 1 AS `create_time`,
 1 AS `merchant_name`,
 1 AS `part_name`,
 1 AS `color`,
 1 AS `weight`,
 1 AS `single_price`,
 1 AS `customer_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_order_detail_complete`
--

DROP TABLE IF EXISTS `v_order_detail_complete`;
/*!50001 DROP VIEW IF EXISTS `v_order_detail_complete`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_order_detail_complete` AS SELECT 
 1 AS `order_id`,
 1 AS `is_deliver`,
 1 AS `is_take`,
 1 AS `star`,
 1 AS `evaluate`,
 1 AS `updateTime`,
 1 AS `merchant_id`,
 1 AS `merchant_name`,
 1 AS `merchant_address`,
 1 AS `customer_id`,
 1 AS `customer_name`,
 1 AS `customer_address`,
 1 AS `part_name`,
 1 AS `amount`,
 1 AS `single_price`,
 1 AS `total_price`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_merchant_collect_info`
--

/*!50001 DROP VIEW IF EXISTS `v_merchant_collect_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_merchant_collect_info` AS select `merchant`.`id` AS `merchant_id`,`merchant`.`name` AS `name`,`merchant`.`phone` AS `phone`,`merchant`.`address` AS `address`,`merchant`.`avatar` AS `avatar`,`merchant`.`description` AS `description`,`collect_merchant`.`customer_id` AS `customer_id`,`collect_merchant`.`create_time` AS `create_time` from (`merchant` join `collect_merchant` on((`merchant`.`id` = `collect_merchant`.`merchant_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_merchant_part_detail`
--

/*!50001 DROP VIEW IF EXISTS `v_merchant_part_detail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_merchant_part_detail` AS select `merchant`.`id` AS `merchant_id`,`merchant`.`name` AS `merchant_name`,`part`.`id` AS `part_id`,`part`.`name` AS `part_name`,`part`.`color` AS `color`,`part`.`weight` AS `weight`,`part`.`description` AS `description`,`part`.`inventory` AS `inventory`,`part`.`single_price` AS `single_price`,`part`.`create_time` AS `create_time`,`part`.`update_time` AS `update_time`,`merchant_part`.`id` AS `id`,`merchant_part`.`is_publish` AS `is_publish` from ((`merchant` join `merchant_part` on((`merchant`.`id` = `merchant_part`.`merchant_id`))) join `part` on((`merchant_part`.`part_id` = `part`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_operate_log_with_admin`
--

/*!50001 DROP VIEW IF EXISTS `v_operate_log_with_admin`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_operate_log_with_admin` AS select `operate_log`.`id` AS `id`,`operate_log`.`admin_id` AS `admin_id`,`operate_log`.`method` AS `method`,`operate_log`.`create_time` AS `create_time`,`admin`.`name` AS `name` from (`operate_log` join `admin` on((`operate_log`.`admin_id` = `admin`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_order_complete_info`
--

/*!50001 DROP VIEW IF EXISTS `v_order_complete_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_order_complete_info` AS select `order`.`id` AS `order_id`,`order`.`merchant_id` AS `merchant_id`,`order`.`customer_id` AS `customer_id`,`order`.`part_id` AS `part_id`,`order`.`amount` AS `amount`,`order`.`total_price` AS `total_price`,`order`.`is_success` AS `is_success`,`order`.`is_paid` AS `is_paid`,`order`.`create_time` AS `create_time`,`merchant`.`name` AS `merchant_name`,`part`.`name` AS `part_name`,`part`.`color` AS `color`,`part`.`weight` AS `weight`,`part`.`single_price` AS `single_price`,`customer`.`name` AS `customer_name` from (((`order` join `merchant` on((`order`.`merchant_id` = `merchant`.`id`))) join `part` on((`order`.`part_id` = `part`.`id`))) join `customer` on((`order`.`customer_id` = `customer`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_order_detail_complete`
--

/*!50001 DROP VIEW IF EXISTS `v_order_detail_complete`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_order_detail_complete` AS select `order_detail`.`order_id` AS `order_id`,`order_detail`.`is_deliver` AS `is_deliver`,`order_detail`.`is_take` AS `is_take`,`order_detail`.`star` AS `star`,`order_detail`.`evaluate` AS `evaluate`,`order_detail`.`update_time` AS `updateTime`,`merchant`.`id` AS `merchant_id`,`merchant`.`name` AS `merchant_name`,`merchant`.`address` AS `merchant_address`,`customer`.`id` AS `customer_id`,`customer`.`name` AS `customer_name`,`customer`.`address` AS `customer_address`,`part`.`name` AS `part_name`,`order`.`amount` AS `amount`,`part`.`single_price` AS `single_price`,`order`.`total_price` AS `total_price` from ((((`order_detail` join `order` on((`order_detail`.`order_id` = `order`.`id`))) join `merchant` on((`order`.`merchant_id` = `merchant`.`id`))) join `customer` on((`order`.`customer_id` = `customer`.`id`))) join `part` on((`order`.`part_id` = `part`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-18 19:51:48
