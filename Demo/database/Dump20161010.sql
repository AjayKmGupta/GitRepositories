-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: sdet
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `competency`
--

DROP TABLE IF EXISTS `competency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `competency_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competency`
--

LOCK TABLES `competency` WRITE;
/*!40000 ALTER TABLE `competency` DISABLE KEYS */;
INSERT INTO `competency` VALUES (1,'T4','Competency-T4'),(2,'C1','Competency-C1'),(3,'C2','Competency-C2'),(4,'C3','Competency-C3'),(5,'C4','Competency-C4'),(6,'C5','Competency-C5'),(7,'C6','Competency-C6'),(8,'C7','Competency-C7'),(9,'C8','Competency-C8'),(10,'C9','Competency-C9'),(11,'C10','Competency-C10'),(12,'C11','Competency-C11'),(13,'C12','Competency-C12');
/*!40000 ALTER TABLE `competency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `mid` varchar(10) NOT NULL,
  `ename` varchar(25) NOT NULL,
  `competency` varchar(10) NOT NULL,
  `practice` varchar(25) NOT NULL,
  `vertical` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('M1027320','Santosh Bhoje','C1','Testing','RCM'),('M1030589','dsadewr','C8','ewrwe','TTH'),('M1234556','Raj','C12','Test','RCM'),('M1234568','Tester','C12','Testing','RCM'),('M1243525','Vibha','C3','rtyrty','BFSI'),('M1243526','Kamal','C2','ddtr','TTH'),('M1243527','Rajni','C3','tetet','BFSI'),('M1243528','Vibha','C3','jhsdgjah','BFSI'),('M1243529','Kamal','C4','rweryw eyjr','TTH'),('M1243530','Rajni','C4','werw ber','TTH'),('M1243531','Vibha','C4','rjywe r','TTH'),('M1243532','Kamal','C4','wrwev','TTH'),('M1243539','Kamal','C2','rweryw eyjr','BFSI'),('M1243540','Rajni','C2','werw ber','RCM'),('M1243541','Vibha','C2','rjywe r','TESTING'),('M1243542','Kamal','C2','wrwev','RCM'),('M1928734','Mahesh','C2','Testing','BFSI'),('M1928735','Mahesh','C2','Testing','BFSI'),('M4563563','dasf','T4','asdfag','csgafdg');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verticals`
--

DROP TABLE IF EXISTS `verticals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verticals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) NOT NULL,
  `vert_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verticals`
--

LOCK TABLES `verticals` WRITE;
/*!40000 ALTER TABLE `verticals` DISABLE KEYS */;
INSERT INTO `verticals` VALUES (1,'RCM','RCM'),(2,'BFSI','BFSI'),(3,'TTH','TTH'),(4,'HITech','HI-TECH'),(5,'TESTING','TESTING');
/*!40000 ALTER TABLE `verticals` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-10 12:08:38
