CREATE DATABASE  IF NOT EXISTS `telcodb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `telcodb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: telcodb
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert` (
  `ID` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `datetime` datetime DEFAULT NULL,
  `amount` decimal(2,0) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`,`user_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `customer` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `username` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `insolvent` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('andreareste','zxc1','andrea.restelli@gmail.com',1),('ciccio','franco','cicciopaterna@gmail.com',0),('cugolizer','deadlock','gianpaolo.cugola@gmail.com',0),('damianandrew','softeng2','damian.andrew@mail.polimi.it',0),('domenicoputi','zxc1','domenico1.putignano@gmail.com',0),('fedesneakers','airforce1','federico.piermattei@gmail.com',0),('gianluboss','30L','gianluca.radi@gmail.com',0),('martinenghi','ok','davide.martinenghi@gmail.com',0),('pierorendina','qwerty','pierorendina@gmail.com',1),('sanchiopazzo','ps5','andreailsanchio@gmail.com',0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `username` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('annamariaantola','mips'),('cinziona','crm'),('giampaologiovanniagosta','calcolatrice'),('levantinoski','zucchi'),('stefanoceri','dbms'),('valentinona','bilancio');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opt_product_schedule_link`
--

DROP TABLE IF EXISTS `opt_product_schedule_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opt_product_schedule_link` (
  `ID_user` varchar(45) NOT NULL,
  `ID_schedule` int NOT NULL,
  `ID_opt_product` int NOT NULL,
  PRIMARY KEY (`ID_user`,`ID_schedule`,`ID_opt_product`),
  KEY `id_opt_prod1_idx` (`ID_opt_product`),
  CONSTRAINT `id_opt_prod1` FOREIGN KEY (`ID_opt_product`) REFERENCES `optional_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `serv_act_schedfk` FOREIGN KEY (`ID_user`, `ID_schedule`) REFERENCES `service_activation_schedule` (`user_id`, `ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opt_product_schedule_link`
--

LOCK TABLES `opt_product_schedule_link` WRITE;
/*!40000 ALTER TABLE `opt_product_schedule_link` DISABLE KEYS */;
INSERT INTO `opt_product_schedule_link` VALUES ('andreareste',9,1),('andreareste',11,1),('pierorendina',12,1),('andreareste',9,2),('andreareste',11,2),('pierorendina',12,2),('pierorendina',15,2),('domenicoputi',17,3),('pierorendina',16,3),('andreareste',11,4),('pierorendina',14,4),('pierorendina',15,4);
/*!40000 ALTER TABLE `opt_product_schedule_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optional_product`
--

DROP TABLE IF EXISTS `optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_product` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `validity_period` int NOT NULL,
  `monthly_fee` decimal(2,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_product`
--

LOCK TABLES `optional_product` WRITE;
/*!40000 ALTER TABLE `optional_product` DISABLE KEYS */;
INSERT INTO `optional_product` VALUES (1,'Decoder',3,13),(2,'Google news feed',3,8),(3,'Internet Tv channel',2,15),(4,'SMS news feed',3,10),(5,'Youtube Premium',3,5);
/*!40000 ALTER TABLE `optional_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `date_hour` datetime NOT NULL,
  `state` varchar(45) NOT NULL,
  `total_value` int DEFAULT NULL,
  `sub_date` date DEFAULT NULL,
  `user_orderer` varchar(45) DEFAULT NULL,
  `id_package` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `userOrderer_idx` (`user_orderer`),
  KEY `id_package_idx` (`id_package`),
  CONSTRAINT `id_package` FOREIGN KEY (`id_package`) REFERENCES `service_package` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `userOrderer` FOREIGN KEY (`user_orderer`) REFERENCES `customer` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (11,'2021-12-10 15:45:39','VALID',123,'2021-12-16','andreareste',2),(12,'2021-12-10 15:48:34','VALID',150,'2021-12-10','andreareste',3),(13,'2021-12-10 15:53:24','VALID',153,'2021-12-30','andreareste',2),(14,'2021-12-10 16:41:14','VALID',123,'2021-12-30','pierorendina',2),(15,'2021-12-10 16:41:26','VALID',150,'2021-12-29','pierorendina',3),(16,'2021-12-10 16:41:36','VALID',84,'2021-12-30','pierorendina',4),(17,'2021-12-10 16:41:48','VALID',114,'2021-12-29','pierorendina',2),(18,'2021-12-10 16:42:04','VALID',78,'2021-12-22','pierorendina',1),(19,'2021-12-10 16:58:41','VALID',78,'2021-12-24','domenicoputi',1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_opt_product_link`
--

DROP TABLE IF EXISTS `order_opt_product_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_opt_product_link` (
  `IDorder` int NOT NULL,
  `ID_optional_product` int NOT NULL,
  PRIMARY KEY (`IDorder`,`ID_optional_product`),
  KEY `Id_opt_prod_idx` (`ID_optional_product`),
  CONSTRAINT `Id_opt_prod2` FOREIGN KEY (`ID_optional_product`) REFERENCES `optional_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Id_order2` FOREIGN KEY (`IDorder`) REFERENCES `order` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_opt_product_link`
--

LOCK TABLES `order_opt_product_link` WRITE;
/*!40000 ALTER TABLE `order_opt_product_link` DISABLE KEYS */;
INSERT INTO `order_opt_product_link` VALUES (11,1),(13,1),(14,1),(11,2),(13,2),(14,2),(17,2),(18,3),(19,3),(13,4),(16,4),(17,4);
/*!40000 ALTER TABLE `order_opt_product_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_opt_product_link`
--

DROP TABLE IF EXISTS `package_opt_product_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_opt_product_link` (
  `id_package` int NOT NULL,
  `id_opt_product` int NOT NULL,
  PRIMARY KEY (`id_package`,`id_opt_product`),
  KEY `id_opt_prod_idx` (`id_opt_product`),
  CONSTRAINT `id_opt_prod` FOREIGN KEY (`id_opt_product`) REFERENCES `optional_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_package_2` FOREIGN KEY (`id_package`) REFERENCES `service_package` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_opt_product_link`
--

LOCK TABLES `package_opt_product_link` WRITE;
/*!40000 ALTER TABLE `package_opt_product_link` DISABLE KEYS */;
INSERT INTO `package_opt_product_link` VALUES (2,1),(2,2),(1,3),(2,4),(4,4),(4,5);
/*!40000 ALTER TABLE `package_opt_product_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_service_link`
--

DROP TABLE IF EXISTS `schedule_service_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_service_link` (
  `ID_user` varchar(45) NOT NULL,
  `ID_schedule` int NOT NULL,
  `ID_service` int NOT NULL,
  PRIMARY KEY (`ID_schedule`,`ID_service`,`ID_user`),
  KEY `service_fkey_idx` (`ID_service`),
  KEY `serv_act_schedulefk_idx` (`ID_user`,`ID_schedule`),
  CONSTRAINT `serv_act_schedulefk` FOREIGN KEY (`ID_user`, `ID_schedule`) REFERENCES `service_activation_schedule` (`user_id`, `ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_fkey` FOREIGN KEY (`ID_service`) REFERENCES `service` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_service_link`
--

LOCK TABLES `schedule_service_link` WRITE;
/*!40000 ALTER TABLE `schedule_service_link` DISABLE KEYS */;
INSERT INTO `schedule_service_link` VALUES ('pierorendina',16,1),('domenicoputi',17,1),('pierorendina',16,2),('domenicoputi',17,2),('pierorendina',16,3),('domenicoputi',17,3),('andreareste',9,6),('andreareste',10,6),('andreareste',11,6),('pierorendina',12,6),('pierorendina',13,6),('pierorendina',15,6),('andreareste',10,7),('pierorendina',13,7),('andreareste',9,8),('andreareste',11,8),('pierorendina',12,8),('pierorendina',14,8),('pierorendina',15,8),('andreareste',9,10),('andreareste',11,10),('pierorendina',12,10),('pierorendina',15,10);
/*!40000 ALTER TABLE `schedule_service_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `minutes` int DEFAULT NULL,
  `sms` int DEFAULT NULL,
  `fee_minutes` decimal(2,0) DEFAULT NULL,
  `fee_sms` decimal(2,0) DEFAULT NULL,
  `gigabytes` int DEFAULT NULL,
  `fee_gigabytes` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Fixed Phone',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Mobile Phone',800,1000,6,2,NULL,NULL),(3,'Fixed Internet',NULL,NULL,NULL,NULL,150,20),(4,'Mobile Internet',NULL,NULL,NULL,NULL,50,8),(5,'Mobile Phone',600,800,5,2,NULL,NULL),(6,'Fixed Internet',NULL,NULL,NULL,NULL,120,18),(7,'Mobile Internet',NULL,NULL,NULL,NULL,70,12),(8,'Mobile Phone',1000,1000,10,3,NULL,NULL),(9,'Fixed Internet',NULL,NULL,NULL,NULL,130,20),(10,'Mobile Internet',NULL,NULL,NULL,NULL,80,14);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_activation_schedule`
--

DROP TABLE IF EXISTS `service_activation_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_activation_schedule` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `activation_date` date DEFAULT NULL,
  `deactivation_date` date DEFAULT NULL,
  PRIMARY KEY (`ID`,`user_id`),
  KEY `user_id1_idx` (`user_id`),
  CONSTRAINT `user_id1` FOREIGN KEY (`user_id`) REFERENCES `customer` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_activation_schedule`
--

LOCK TABLES `service_activation_schedule` WRITE;
/*!40000 ALTER TABLE `service_activation_schedule` DISABLE KEYS */;
INSERT INTO `service_activation_schedule` VALUES (9,'andreareste','2021-12-16','2022-03-16'),(10,'andreareste','2021-12-10','2022-06-10'),(11,'andreareste','2021-12-30','2022-03-30'),(12,'pierorendina','2021-12-30','2022-03-30'),(13,'pierorendina','2021-12-29','2022-06-29'),(14,'pierorendina','2021-12-30','2022-03-30'),(15,'pierorendina','2021-12-29','2022-03-29'),(16,'pierorendina','2021-12-22','2022-02-22'),(17,'domenicoputi','2021-12-24','2022-02-24');
/*!40000 ALTER TABLE `service_activation_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package`
--

DROP TABLE IF EXISTS `service_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `validity_period` int NOT NULL,
  `monthly_fee` decimal(2,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
INSERT INTO `service_package` VALUES (1,'service1',2,24),(2,'service2',3,20),(3,'service3',6,25),(4,'service4',3,18);
/*!40000 ALTER TABLE `service_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_to_package_link`
--

DROP TABLE IF EXISTS `service_to_package_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_to_package_link` (
  `ID_service` int NOT NULL,
  `ID_package` int NOT NULL,
  PRIMARY KEY (`ID_service`,`ID_package`),
  KEY `id_package_idx` (`ID_package`),
  CONSTRAINT `id_package_link` FOREIGN KEY (`ID_package`) REFERENCES `service_package` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_service_` FOREIGN KEY (`ID_service`) REFERENCES `service` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_to_package_link`
--

LOCK TABLES `service_to_package_link` WRITE;
/*!40000 ALTER TABLE `service_to_package_link` DISABLE KEYS */;
INSERT INTO `service_to_package_link` VALUES (1,1),(2,1),(3,1),(6,2),(8,2),(10,2),(6,3),(7,3),(8,4);
/*!40000 ALTER TABLE `service_to_package_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-13 10:07:32
