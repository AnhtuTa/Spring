-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: o7plan
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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_no` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `dept_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'101','IT'),(2,'102','SET'),(3,'103','Human resources'),(4,'104','Accounting and Finance'),(5,'105','Marketing'),(6,'106','Research and Development'),(7,'107','Production'),(8,'108','Purchasing');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spitter`
--

DROP TABLE IF EXISTS `spitter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spitter` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spitter`
--

LOCK TABLES `spitter` WRITE;
/*!40000 ALTER TABLE `spitter` DISABLE KEYS */;
INSERT INTO `spitter` VALUES ('gohan','$2a$10$zJW/IcHZMss1EJaAZW5KWueWPVopi4G0P261fuDVIXXs5.wpRFQPW',1),('goku','b59c67bf196a4758191e42f76670ceba',1),('vegeta','b59c67bf196a4758191e42f76670ceba',1);
/*!40000 ALTER TABLE `spitter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spitter_roles`
--

DROP TABLE IF EXISTS `spitter_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spitter_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `each_role_uk_spitter_roles` (`username`,`role`),
  CONSTRAINT `userid_fk_spitter_roles` FOREIGN KEY (`username`) REFERENCES `spitter` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spitter_roles`
--

LOCK TABLES `spitter_roles` WRITE;
/*!40000 ALTER TABLE `spitter_roles` DISABLE KEYS */;
INSERT INTO `spitter_roles` VALUES (8,'gohan','ROLE_ADMIN'),(7,'gohan','ROLE_USER'),(3,'goku','ROLE_ADMIN'),(1,'goku','ROLE_USER'),(6,'goku','ROLE_WTF'),(4,'vegeta','ROLE_USER');
/*!40000 ALTER TABLE `spitter_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_accounts`
--

DROP TABLE IF EXISTS `user_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_accounts` (
  `ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `USER_NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `FIRST_NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_NAME` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ENABLED` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `User_Account_UK1` (`EMAIL`),
  UNIQUE KEY `User_Account_UK2` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_accounts`
--

LOCK TABLES `user_accounts` WRITE;
/*!40000 ALTER TABLE `user_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `ROLE_ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `USERNAME` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `USER_ROLE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `USER_ROLE_UK` (`USERNAME`,`USER_ROLE`),
  CONSTRAINT `username_fk_user_roles` FOREIGN KEY (`USERNAME`) REFERENCES `users` (`USERNAME`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('2','admin','ADMIN'),('3','admin','USER'),('1','anhtu','USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userconnection` (
  `USERID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PROVIDERID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PROVIDERUSERID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `RANK` int(11) NOT NULL,
  `DISPLAYNAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PROFILEURL` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMAGEURL` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCESSTOKEN` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SECRET` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFRESHTOKEN` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXPIRETIME` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USERID`,`PROVIDERID`,`PROVIDERUSERID`),
  UNIQUE KEY `USERCONNECTIONRANK` (`USERID`,`PROVIDERID`,`RANK`),
  CONSTRAINT `userID_fk_userconnection` FOREIGN KEY (`USERID`) REFERENCES `user_accounts` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USERNAME` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `ENABLED` smallint(6) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','1111',1),('anhtu','1111',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-28 18:42:52
