-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: simplehr
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
  `DEPT_ID` int(11) NOT NULL,
  `DEPT_NAME` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DEPT_NO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LOCATION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`),
  UNIQUE KEY `DEPT_NO` (`DEPT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (10,'ACCOUNTING','D10','NEW YORK'),(20,'RESEARCH','D20','DALLAS'),(30,'SALES','D30','CHICAGO'),(40,'OPERATIONS','D40','BOSTON');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMP_ID` bigint(20) NOT NULL,
  `EMP_NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `EMP_NO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `IMAGE` longblob,
  `JOB` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `SALARY` float NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `MNG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE KEY `EMP_NO` (`EMP_NO`),
  KEY `FK75C8D6AE269A3C9` (`DEPT_ID`),
  KEY `FK75C8D6AE6106A42` (`EMP_ID`),
  KEY `FK75C8D6AE13C12F64` (`MNG_ID`),
  CONSTRAINT `FK75C8D6AE13C12F64` FOREIGN KEY (`MNG_ID`) REFERENCES `employee` (`EMP_ID`),
  CONSTRAINT `FK75C8D6AE269A3C9` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`DEPT_ID`),
  CONSTRAINT `FK75C8D6AE6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (7369,'SMITH','E7369','1980-12-17',NULL,'CLERK',800,20,7902),(7499,'ALLEN','E7499','1981-02-20',NULL,'SALESMAN',1600,30,7698),(7521,'WARD','E7521','1981-02-22',NULL,'SALESMAN',1250,30,7698),(7566,'JONES','E7566','1981-04-02',NULL,'MANAGER',2975,20,7839),(7654,'MARTIN','E7654','1981-09-28',NULL,'SALESMAN',1250,30,7698),(7698,'BLAKE','E7698','1981-05-01',NULL,'MANAGER',2850,30,7839),(7782,'CLARK','E7782','1981-06-09',NULL,'MANAGER',2450,30,7839),(7788,'SCOTT','E7788','1987-04-19',NULL,'ANALYST',3000,20,7566),(7839,'KING','E7839','1981-11-17',NULL,'PRESIDENT',5000,10,NULL),(7844,'TURNER','E7844','1981-09-08',NULL,'SALESMAN',1500,30,7698),(7876,'ADAMS','E7876','1987-05-23',NULL,'CLERK',1100,20,7698),(7900,'ADAMS','E7900','1981-12-03',NULL,'CLERK',950,30,7698),(7902,'FORD','E7902','1981-12-03',NULL,'ANALYST',3000,20,7566),(7934,'MILLER','E7934','1982-01-23',NULL,'CLERK',1300,10,7698);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_grade`
--

DROP TABLE IF EXISTS `salary_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary_grade` (
  `GRADE` int(11) NOT NULL,
  `HIGH_SALARY` float NOT NULL,
  `LOW_SALARY` float NOT NULL,
  PRIMARY KEY (`GRADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_grade`
--

LOCK TABLES `salary_grade` WRITE;
/*!40000 ALTER TABLE `salary_grade` DISABLE KEYS */;
INSERT INTO `salary_grade` VALUES (1,9999,3001);
/*!40000 ALTER TABLE `salary_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timekeeper`
--

DROP TABLE IF EXISTS `timekeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timekeeper` (
  `Timekeeper_Id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `Date_Time` datetime NOT NULL,
  `In_Out` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `EMP_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Timekeeper_Id`),
  KEY `FK744D9BFF6106A42` (`EMP_ID`),
  CONSTRAINT `FK744D9BFF6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timekeeper`
--

LOCK TABLES `timekeeper` WRITE;
/*!40000 ALTER TABLE `timekeeper` DISABLE KEYS */;
/*!40000 ALTER TABLE `timekeeper` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-11 18:45:19
