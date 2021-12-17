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
  `ID` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `datetime` datetime DEFAULT NULL,
  `amount` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `customer` VALUES ('andreareste','zxc1','andrea.restelli@gmail.com',0),('ciccio','franco','cicciopaterna@gmail.com',0),('cugolizer','deadlock','gianpaolo.cugola@gmail.com',0),('damianandrew','softeng2','damian.andrew@mail.polimi.it',0),('domenicoputi','zxc1','domenico1.putignano@gmail.com',0),('fedesneakers','airforce1','federico.piermattei@gmail.com',0),('gianluboss','30L','gianluca.radi@gmail.com',0),('martinenghi','ok','davide.martinenghi@gmail.com',0),('pierorendina','qwerty','pierorendina@gmail.com',0),('sanchiopazzo','ps5','andreailsanchio@gmail.com',0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `CreateAfter` AFTER UPDATE ON `customer` FOR EACH ROW BEGIN
	IF(old.insolvent = 2 AND new.insolvent = 3) THEN
		INSERT INTO telcodb.alert
        VALUES(null,old.username,NOW(),
        (SELECT o.total_value FROM telcodb.order o
        WHERE old.username = o.user_orderer AND
        O.date_hour = (SELECT MAX(o1.date_hour) FROM telcodb.order o1
						WHERE o1.user_orderer = old.username AND o1.state = 'REJECTED'))
        ,old.mail);
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
  `total_value` decimal(5,2) DEFAULT NULL,
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
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `UpdateInsolvent` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF (old.state ='PENDING' AND new.state = 'REJECTED') THEN
			UPDATE telcodb.customer
			SET insolvent = insolvent + 1
			WHERE username = old.user_orderer;
	ELSEIF (old.state ='REJECTED' AND new.state = 'VALID') THEN
			UPDATE telcodb.customer
			SET insolvent = insolvent - 1
			WHERE username = old.user_orderer;
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `UpdateTotalPurchasesPerPackage` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.total_purchases_per_package
			SET total_purchases = total_purchases + 1
			WHERE packageName = (SELECT sp.name FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `UpdateTotalPurchasesPerPackageAndValidityPeriod` AFTER UPDATE ON `order` FOR EACH ROW BEGIN
	IF((old.state = 'PENDING' OR old.state = 'REJECTED')
		AND new.state = 'VALID') THEN
		UPDATE telcodb.total_purchases_validity_period_per_package
			SET total_purchases = total_purchases + 1
			WHERE (packageName,validity_period) = (SELECT sp.name,sp.validity_period FROM
								telcodb.service_package sp JOIN telcodb.order o
                                ON o.id_package = sp.ID
                                WHERE o.ID = new.ID);
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `package_opt_product_link` VALUES (2,1),(10,1),(2,2),(6,2),(12,2),(1,3),(7,3),(2,4),(4,4),(6,4),(4,5),(10,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
INSERT INTO `service_package` VALUES (1,'service1',2,24),(2,'service2',3,20),(3,'service3',6,25),(4,'service4',3,18),(5,'service5',5,45),(6,'service6',3,41),(7,'service7',2,46),(10,'service8',3,47),(11,'service9',4,40),(12,'service8',3,90);
/*!40000 ALTER TABLE `service_package` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `CreateService_package_Total_purchases` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN
	IF(new.name NOT IN (SELECT tp.packageName FROM telcodb.total_purchases_per_package tp))THEN
		INSERT INTO telcodb.total_purchases_per_package 
		VALUES(new.name,0);
    END IF;
    IF((new.name,new.validity_period) NOT IN (SELECT tp.packageName,tp.validity_period FROM telcodb.total_purchases_validity_period_per_package tp))THEN
		INSERT INTO telcodb.total_purchases_validity_period_per_package
		VALUES(new.name,new.validity_period,0);
	END IF;	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
INSERT INTO `service_to_package_link` VALUES (1,1),(2,1),(3,1),(6,2),(8,2),(10,2),(6,3),(7,3),(8,4),(1,5),(4,5),(6,5),(8,6),(9,6),(10,6),(2,7),(7,7),(3,10),(7,10),(6,11),(7,11),(1,12);
/*!40000 ALTER TABLE `service_to_package_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `total_purchases_per_package`
--

DROP TABLE IF EXISTS `total_purchases_per_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `total_purchases_per_package` (
  `packageName` varchar(45) NOT NULL,
  `total_purchases` int DEFAULT NULL,
  PRIMARY KEY (`packageName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_purchases_per_package`
--

LOCK TABLES `total_purchases_per_package` WRITE;
/*!40000 ALTER TABLE `total_purchases_per_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `total_purchases_per_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `total_purchases_validity_period_per_package`
--

DROP TABLE IF EXISTS `total_purchases_validity_period_per_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `total_purchases_validity_period_per_package` (
  `packageName` varchar(45) NOT NULL,
  `validity_period` int NOT NULL,
  `total_purchases` int DEFAULT NULL,
  PRIMARY KEY (`packageName`,`validity_period`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_purchases_validity_period_per_package`
--

LOCK TABLES `total_purchases_validity_period_per_package` WRITE;
/*!40000 ALTER TABLE `total_purchases_validity_period_per_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `total_purchases_validity_period_per_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'telcodb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-17 14:56:21
