-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.6.41-log

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
-- Table structure for table `bank_account_transaction`
--

DROP TABLE IF EXISTS `bank_account_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account_transaction` (
  `tx_id` int(11) NOT NULL,
  `acc_number` int(11) DEFAULT NULL,
  `transaction_type` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  PRIMARY KEY (`tx_id`),
  KEY `acc_number_idx` (`acc_number`),
  CONSTRAINT `acc_number` FOREIGN KEY (`acc_number`) REFERENCES `bank_account` (`acc_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account_transaction`
--

LOCK TABLES `bank_account_transaction` WRITE;
/*!40000 ALTER TABLE `bank_account_transaction` DISABLE KEYS */;
INSERT INTO `bank_account_transaction` VALUES (1,1101,'credit',50000,'2016-02-03 23:23:12'),(2,1101,'debit',5000,'2017-01-05 03:15:00'),(3,1102,'credit',700000,'1999-08-15 11:00:00'),(4,1102,'debit',7800,'2017-07-17 05:23:09'),(5,1103,'credit',45000,'2013-05-22 14:25:00'),(6,1103,'debit',3000,'2018-01-31 19:15:12'),(7,1104,'credit',85000,'2012-07-06 23:23:12'),(8,1104,'debit',9000,'2015-02-03 18:00:12'),(9,1105,'credit',125000,'2010-09-29 16:45:12'),(10,1105,'debit',3500,'2012-12-30 21:21:12'),(11,1105,'debit',2000,'2012-12-30 21:21:12'),(12,1103,'credit',4000,'2012-12-30 21:21:12'),(13,1101,'credit',4000,'2012-12-30 21:21:12'),(14,1104,'debit',3400,'2012-12-30 21:21:12'),(15,1105,'debit',250,'2018-08-03 00:00:00'),(16,1105,'debit',250,'2018-08-08 00:00:00'),(17,1105,'debit',250,'2018-08-08 00:00:00'),(18,1104,'debit',400,'2018-08-08 00:00:00'),(19,1103,'credit',550,'2018-08-08 00:00:00'),(20,1101,'credit',2500,'2018-08-08 00:00:00'),(21,1101,'credit',2500,'2018-08-08 00:00:00'),(22,1103,'credit',400,'2018-08-09 00:00:00');
/*!40000 ALTER TABLE `bank_account_transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-09  6:19:29
