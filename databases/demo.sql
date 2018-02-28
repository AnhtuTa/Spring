-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `amount` decimal(8,0) DEFAULT NULL COMMENT 'Số tiền trong tài khoản, tối đa < 100 000 000 (8 chữ số, trong đó 0 số sau dấu thập phân và (8-0) số trước dấu thập phân)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,'anhtu',90000000),(2,'huy',90000000),(3,'anhtu2',40000000),(4,'huy2',90000000),(5,'anhtu3',90000000),(6,'huy3',90000000);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `countryName` varchar(45) DEFAULT NULL,
  `capital` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Vietnam','Hanoi'),(2,'Japan','Tokyo'),(3,'USA','Washington'),(4,'Singapore','Singapore'),(5,'England','London');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Anhtu2','Hanoi'),(2,'Huy ga','Hanoi'),(3,'Anh Tu Ta','Hanoi'),(4,'Nguyen bka','HCMC'),(5,'Toan','Bac giang'),(6,'Trung','Hai phong'),(7,'Anhtu_updated','Hanoi'),(8,'Anhtuta','Hanoi'),(11,'Ronaldo','BDN'),(13,'Anhtuta','Hanoi'),(14,'Anhtuta','Hanoi 2'),(15,'fresher','fpt'),(16,'Anhtuta','Da Nang'),(17,'Anhtuta','Beijing');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demo_trigger`
--

DROP TABLE IF EXISTS `demo_trigger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demo_trigger` (
  `id` int(11) NOT NULL,
  `std_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demo_trigger`
--

LOCK TABLES `demo_trigger` WRITE;
/*!40000 ALTER TABLE `demo_trigger` DISABLE KEYS */;
INSERT INTO `demo_trigger` VALUES (1,'son go ku_hehehe'),(2,'alo_hehehe cuoi cai cc :v_updated'),(3,'fewaf_hehehe'),(12,'anhtu_hehehe wtf?_updated');
/*!40000 ALTER TABLE `demo_trigger` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `demo`.`demo_BEFORE_INSERT` BEFORE INSERT ON `demo_trigger` FOR EACH ROW
BEGIN
	set NEW.std_name = CONCAT(NEW.std_name, '_hehehe');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `demo`.`demo_BEFORE_UPDATE` BEFORE UPDATE ON `demo_trigger` FOR EACH ROW
BEGIN
	set NEW.std_name = CONCAT(NEW.std_name, '_updated');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `fresher`
--

DROP TABLE IF EXISTS `fresher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fresher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fresher`
--

LOCK TABLES `fresher` WRITE;
/*!40000 ALTER TABLE `fresher` DISABLE KEYS */;
INSERT INTO `fresher` VALUES (1,'att','1111','Hanoi',2017),(2,'huy','123','Hanoi',2019),(3,'nguyen','123','HCM City',2011),(9,'toan','121','Bac Giang',2013);
/*!40000 ALTER TABLE `fresher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pupil`
--

DROP TABLE IF EXISTS `pupil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pupil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `addr` varchar(45) DEFAULT NULL,
  `country` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `MaQG_idx` (`country`),
  CONSTRAINT `MaQG` FOREIGN KEY (`country`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pupil`
--

LOCK TABLES `pupil` WRITE;
/*!40000 ALTER TABLE `pupil` DISABLE KEYS */;
INSERT INTO `pupil` VALUES (1,'anhtu','Hanoi',1),(2,'nguyen','HN',2),(3,'Nam','HCM',1),(4,'Huyen','Da nang',3),(5,'Tạ Anh Tú','Hà Nội',4),(6,'Toàn gà','Bắc Giang',5),(7,'Huy noob','Bách Khoa',5),(8,'Giang','Lào Cai',2),(9,'Trung Anh','Hanam',1),(10,'Goku','Saiyan',3),(11,'Songoku','Tokyo',2),(12,'Lionel Messi','Argentina',5),(13,'Lionel Messi','Argentina',5),(14,'Lionel Messi','Argentina',5),(15,'Lionel Messi','Argentina',5),(17,'anhtu2','Hanoi2',3),(18,'anhtu3','Hanoi3',3),(19,'anhtu4','Hanoi4',3),(20,'anhtu5','Hanoi5',3),(21,'Lionel Messi','Argentina',5),(23,'anhtu2','Hanoi2',3),(24,'anhtu3','Hanoi3',3),(25,'anhtu4','Hanoi4',3),(26,'anhtu5','Hanoi5',3);
/*!40000 ALTER TABLE `pupil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anhtu1','Hanoi1'),(2,'anhtu2','Hanoi2'),(3,'anhtu3','Hanoi3'),(4,'anhtu4','Hanoi4'),(5,'anhtu5','Hanoi5'),(6,'anhtu6','Hanoi6'),(7,'anhtu7','Hanoi7'),(8,'anhtu8','Hanoi8'),(9,'anhtu9','Hanoi9'),(10,'anhtu10','Hanoi10'),(11,'anhtu11','Hanoi11'),(12,'anhtu12','Hanoi12'),(13,'anhtu13','Hanoi13'),(14,'anhtu14','Hanoi14'),(15,'anhtu15','Hanoi15'),(16,'anhtu16','Hanoi16'),(17,'anhtu17','Hanoi17'),(18,'anhtu18','Hanoi18'),(19,'anhtu19','Hanoi19'),(20,'anhtu20','Hanoi20'),(21,'anhtu21','Hanoi21'),(22,'anhtu22','Hanoi22'),(23,'anhtu23','Hanoi23'),(24,'anhtu24','Hanoi24'),(25,'anhtu25','Hanoi25'),(26,'anhtu26','Hanoi26'),(27,'anhtu27','Hanoi27'),(28,'anhtu28','Hanoi28'),(29,'anhtu29','Hanoi29'),(30,'anhtu30','Hanoi30'),(31,'anhtu31','Hanoi31'),(32,'anhtu32','Hanoi32'),(33,'anhtu33','Hanoi33'),(34,'anhtu34','Hanoi34'),(35,'anhtu35','Hanoi35'),(36,'anhtu36','Hanoi36'),(37,'anhtu37','Hanoi37'),(38,'anhtu38','Hanoi38'),(39,'anhtu39','Hanoi39'),(40,'anhtu40','Hanoi40'),(41,'anhtu41','Hanoi41'),(42,'anhtu42','Hanoi42'),(43,'anhtu43','Hanoi43'),(44,'anhtu44','Hanoi44'),(45,'anhtu45','Hanoi45'),(46,'anhtu46','Hanoi46'),(47,'anhtu47','Hanoi47'),(48,'anhtu48','Hanoi48'),(49,'anhtu49','Hanoi49'),(50,'anhtu50','Hanoi50'),(51,'anhtu51','Hanoi51'),(52,'anhtu52','Hanoi52'),(53,'anhtu53','Hanoi53'),(54,'anhtu54','Hanoi54'),(55,'anhtu55','Hanoi55'),(56,'anhtu56','Hanoi56'),(57,'anhtu57','Hanoi57'),(58,'anhtu58','Hanoi58'),(59,'anhtu59','Hanoi59'),(60,'anhtu60','Hanoi60'),(61,'anhtu61','Hanoi61'),(62,'anhtu62','Hanoi62'),(63,'anhtu63','Hanoi63'),(64,'anhtu64','Hanoi64'),(65,'anhtu65','Hanoi65'),(66,'anhtu66','Hanoi66'),(67,'anhtu67','Hanoi67'),(68,'anhtu68','Hanoi68'),(69,'anhtu69','Hanoi69'),(70,'anhtu70','Hanoi70'),(71,'anhtu71','Hanoi71'),(72,'anhtu72','Hanoi72'),(73,'anhtu73','Hanoi73'),(74,'anhtu74','Hanoi74'),(75,'anhtu75','Hanoi75'),(76,'anhtu76','Hanoi76'),(77,'anhtu77','Hanoi77'),(78,'anhtu78','Hanoi78'),(79,'anhtu79','Hanoi79'),(80,'anhtu80','Hanoi80'),(81,'anhtu81','Hanoi81'),(82,'anhtu82','Hanoi82'),(83,'anhtu83','Hanoi83'),(84,'anhtu84','Hanoi84'),(85,'anhtu85','Hanoi85'),(86,'anhtu86','Hanoi86'),(87,'anhtu87','Hanoi87'),(88,'anhtu88','Hanoi88'),(89,'anhtu89','Hanoi89'),(90,'anhtu90','Hanoi90'),(91,'anhtu91','Hanoi91'),(92,'anhtu92','Hanoi92'),(93,'anhtu93','Hanoi93'),(94,'anhtu94','Hanoi94'),(95,'anhtu95','Hanoi95'),(96,'anhtu96','Hanoi96'),(97,'anhtu97','Hanoi97'),(98,'anhtu98','Hanoi98'),(99,'anhtu99','Hanoi99'),(100,'anhtu100','Hanoi100');
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

-- Dump completed on 2018-02-28 18:42:18
