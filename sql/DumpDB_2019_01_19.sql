-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: insurance
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(100) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `nameOrganization` varchar(45) DEFAULT NULL,
  `CLIENT_TYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (32,'Shevchenko str. 17,Kharkiv','Serga','Vechniy',NULL,'Individual'),(43,'Lubzik','Alexandr','TheBestPerson',NULL,'Individual'),(44,'Lubzik',NULL,NULL,'Pobeda','LegalEntity'),(45,'new','Alex','Strymeneshenko',NULL,'Individual'),(46,'newLegal',NULL,NULL,'new','LegalEntity'),(47,'Prechepilovka','Alex','Hohoh',NULL,'Individual'),(48,'bestPlace',NULL,NULL,'Klass','LegalEntity'),(49,'Kharkiv','Vova','Pypkin',NULL,'Individual'),(50,'Dalekoedaleko','Vovan','Paxan',NULL,'Individual'),(51,'Schstie',NULL,NULL,'Kryteckaya','LegalEntity');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `number` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateConclusion` date NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `Client_id` bigint(20) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_Contract_Client_idx` (`Client_id`),
  CONSTRAINT `FKmnn06x6dnex5w8cffevf40dlg` FOREIGN KEY (`Client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `fk_Contract_Client` FOREIGN KEY (`Client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (12,'1992-09-05','1977-05-25','1941-04-04',32),(13,'1111-11-11','1111-11-11','1111-11-11',46),(18,'1212-12-12','1212-12-12','1212-12-12',45),(19,'1111-11-11','1111-11-11','1111-11-11',44);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_insuredperson`
--

DROP TABLE IF EXISTS `contract_insuredperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract_insuredperson` (
  `Contract_number` bigint(20) NOT NULL,
  `InsuredPerson_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Contract_number`,`InsuredPerson_id`),
  KEY `fk_Contract_has_InsuredPerson_InsuredPerson1_idx` (`InsuredPerson_id`),
  KEY `fk_Contract_has_InsuredPerson_Contract1_idx` (`Contract_number`),
  CONSTRAINT `FK1scv5198ehntx7gp5nkdxegy7` FOREIGN KEY (`InsuredPerson_id`) REFERENCES `insuredperson` (`id`),
  CONSTRAINT `FKdd1al6iy88rgu8jk5e618fx9w` FOREIGN KEY (`Contract_number`) REFERENCES `contract` (`number`),
  CONSTRAINT `fk_Contract_has_InsuredPerson_Contract1` FOREIGN KEY (`Contract_number`) REFERENCES `contract` (`number`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contract_has_InsuredPerson_InsuredPerson1` FOREIGN KEY (`InsuredPerson_id`) REFERENCES `insuredperson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_insuredperson`
--

LOCK TABLES `contract_insuredperson` WRITE;
/*!40000 ALTER TABLE `contract_insuredperson` DISABLE KEYS */;
INSERT INTO `contract_insuredperson` VALUES (12,23),(12,24),(18,24),(18,26),(13,27),(19,27);
/*!40000 ALTER TABLE `contract_insuredperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insuredperson`
--

DROP TABLE IF EXISTS `insuredperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insuredperson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `cost` int(11) NOT NULL,
  `identificationNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`identificationNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insuredperson`
--

LOCK TABLES `insuredperson` WRITE;
/*!40000 ALTER TABLE `insuredperson` DISABLE KEYS */;
INSERT INTO `insuredperson` VALUES (23,'Alexa','Pypok','1969-05-20',2222,12342341),(24,'Serga','Smeshnoy','1954-02-04',4321,76481298),(26,'Alexandr','Strymeneshenko','1998-12-12',123,12345214),(27,'Boba','Felix','1999-11-11',1212,11111111),(28,'qwqweeq','qweqwer','1111-11-11',12,12121212);
/*!40000 ALTER TABLE `insuredperson` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-19 19:51:36
