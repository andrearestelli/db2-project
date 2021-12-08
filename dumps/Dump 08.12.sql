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
INSERT INTO `customer` VALUES ('andreareste','zxc1','andrea.restelli@gmail.com',0),('ciccio','franco','cicciopaterna@gmail.com',0),('cugolizer','deadlock','gianpaolo.cugola@gmail.com',1),('domenicoputi','zxc1','domenico1.putignano@gmail.com',0),('fedesneakers','airforce1','federico.piermattei@gmail.com',0),('gianluboss','30L','gianluca.radi@gmail.com',0),('martinenghi','ok','davide.martinenghi@gmail.com',1),('pierorendina','qwerty','pierorendina@gmail.com',0),('sanchiopazzo','ps5','andreailsanchio@gmail.com',0);
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
  CONSTRAINT `id_user1` FOREIGN KEY (`ID_user`, `ID_schedule`) REFERENCES `service_activation_schedule` (`user_id`, `ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opt_product_schedule_link`
--

LOCK TABLES `opt_product_schedule_link` WRITE;
/*!40000 ALTER TABLE `opt_product_schedule_link` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
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
  KEY `user_fkey_idx` (`ID_user`,`ID_schedule`),
  KEY `service_fkey_idx` (`ID_service`),
  CONSTRAINT `scheule_fkey` FOREIGN KEY (`ID_user`, `ID_schedule`) REFERENCES `service_activation_schedule` (`user_id`, `ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_fkey` FOREIGN KEY (`ID_service`) REFERENCES `service` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_service_link`
--

LOCK TABLES `schedule_service_link` WRITE;
/*!40000 ALTER TABLE `schedule_service_link` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_activation_schedule`
--

DROP TABLE IF EXISTS `service_activation_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_activation_schedule` (
  `user_id` varchar(45) NOT NULL,
  `ID` int NOT NULL,
  `activation_date` date DEFAULT NULL,
  `deactivation_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`,`ID`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `customer` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_activation_schedule`
--

LOCK TABLES `service_activation_schedule` WRITE;
/*!40000 ALTER TABLE `service_activation_schedule` DISABLE KEYS */;
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

-- Dump completed on 2021-12-08 15:43:52
