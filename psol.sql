-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: psol
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `consignee` varchar(16) DEFAULT NULL,
  `detail_address` varchar(64) DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `admin` (
  `id` varchar(64) NOT NULL,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_uuid_uindex` (`id`),
  CONSTRAINT `admin_user_uuid_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='管理员对象';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('df9316f60bdd420ea2b8316ec21f3022','文工团键盘手');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `cart_item` (
  `choice_id` int(11) NOT NULL,
  `store_id` varchar(64) DEFAULT NULL,
  `product_id` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `invalid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`choice_id`),
  UNIQUE KEY `cart_item_choice_id_uindex` (`choice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `order` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `store_id` varchar(64) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `status` varchar(64) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `dicount` float DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `order_item` (
  `order_id` varchar(64) DEFAULT NULL,
  `product_id` varchar(64) DEFAULT NULL,
  `choice_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  UNIQUE KEY `order_item_pk` (`order_id`,`choice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `product` (
  `id` varchar(64) NOT NULL,
  `store_id` varchar(64) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `cover_img` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `display_price` float DEFAULT NULL,
  `freight` float DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `product_args` varchar(256) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id_uindex` (`id`),
  UNIQUE KEY `product_store_id_uindex` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_choice`
--

DROP TABLE IF EXISTS `product_choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `product_choice` (
  `choice_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(64) NOT NULL,
  `price` float DEFAULT NULL,
  `storage` int(11) DEFAULT NULL,
  `choice_img` varchar(64) DEFAULT NULL,
  `choice` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`choice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_choice`
--

LOCK TABLES `product_choice` WRITE;
/*!40000 ALTER TABLE `product_choice` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_imgs`
--

DROP TABLE IF EXISTS `product_imgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `product_imgs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `product_id` varchar(64) NOT NULL,
  `is_swipper` tinyint(1) DEFAULT '0',
  `swipper_order` int(11) DEFAULT NULL,
  `arg_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_imgs`
--

LOCK TABLES `product_imgs` WRITE;
/*!40000 ALTER TABLE `product_imgs` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_imgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_service`
--

DROP TABLE IF EXISTS `product_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `product_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(64) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_service`
--

LOCK TABLES `product_service` WRITE;
/*!40000 ALTER TABLE `product_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puser`
--

DROP TABLE IF EXISTS `puser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `puser` (
  `id` varchar(64) NOT NULL,
  `nickname` varchar(16) DEFAULT NULL,
  `name` varchar(8) DEFAULT NULL,
  `id_number` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `puser_id_uindex` (`id`),
  CONSTRAINT `puser_user_id_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puser`
--

LOCK TABLES `puser` WRITE;
/*!40000 ALTER TABLE `puser` DISABLE KEYS */;
/*!40000 ALTER TABLE `puser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_path`
--

DROP TABLE IF EXISTS `role_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `role_path` (
  `role` varchar(16) DEFAULT NULL,
  `path` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_path`
--

LOCK TABLES `role_path` WRITE;
/*!40000 ALTER TABLE `role_path` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_path` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `staff` (
  `id` varchar(64) NOT NULL,
  `work_id` varchar(64) DEFAULT NULL,
  `name` varchar(8) DEFAULT NULL,
  `id_number` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_id_uindex` (`id`),
  CONSTRAINT `staff_user_id_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `store` (
  `id` varchar(64) NOT NULL,
  `owner_id` varchar(64) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `logo` varchar(64) DEFAULT NULL,
  `location` varchar(32) DEFAULT NULL,
  `open_time` date DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `activated` tinyint(1) DEFAULT '1',
  `is_special` tinyint(1) DEFAULT '0',
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `store_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_img`
--

DROP TABLE IF EXISTS `store_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `store_img` (
  `store_id` varchar(64) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL,
  `link` varchar(64) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `is_swipper` tinyint(1) DEFAULT '0',
  `swipper_order` int(11) DEFAULT NULL,
  UNIQUE KEY `store_img_pk` (`store_id`,`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_img`
--

LOCK TABLES `store_img` WRITE;
/*!40000 ALTER TABLE `store_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(16) DEFAULT NULL,
  `activated` tinyint(1) DEFAULT '1' COMMENT '账号是否被激活',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`),
  UNIQUE KEY `user_uuid_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('016950a3c749447cb4859a2e4bc55bd8','user','$2a$10$mU/Q4Wp2XaHkIYgljRrpzuJ5.ZRcgfhijGOsVB8sLzX/eFkigA4lK','ROLE_user',1),('111','user1','$2a$10$AJKkLcNCgaGBgPkesREar.eOoJtIaU0Gk9iRghrpeIoOBpw2bQlfO','ROLE_x',1),('df9316f60bdd420ea2b8316ec21f3022','admin','$2a$10$mU/Q4Wp2XaHkIYgljRrpzuJ5.ZRcgfhijGOsVB8sLzX/eFkigA4lK','ROLE_admin',1),('f8be87307a304a609b0a956cb88b8363','kunkun','$2a$10$mU/Q4Wp2XaHkIYgljRrpzuJ5.ZRcgfhijGOsVB8sLzX/eFkigA4lK','ROLE_cxk',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-17 21:49:43
