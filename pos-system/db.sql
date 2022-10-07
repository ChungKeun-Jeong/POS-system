-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: POS
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `code` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PRICE` int DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `ID` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `POSITION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('id1','pw1','홍길동','매니저'),('id11','pw11','아이디','직원'),('id121','pw','dk','직원'),('id123','pw123','ao','매니저'),('id2','pw2','김길동','직원'),('id3','pw3','이길동','직원'),('id4','pw4','김이박','매니저'),('test','pw1','이름','직원');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay`
--

DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay` (
  `DATE` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CODE` varchar(100) DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  `PRICE` int DEFAULT NULL,
  `TOTALPRICE` int DEFAULT NULL,
  `ORDERNUMBER` varchar(100) NOT NULL,
  PRIMARY KEY (`ORDERNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay`
--

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES ('0601 - 16:16','과자','c001',1,2000,2000,'0601 - 16:16c0010'),('0601 - 16:16','컵라면','c002',3,1500,4500,'0601 - 16:16c0021'),('0601 - 16:16','컵라면','c002',1,1500,1500,'0601 - 16:16c0024'),('0601 - 16:16','사이다','c003',5,1000,5000,'0601 - 16:16c0032'),('0601 - 16:16','사이다','c003',1,1000,1000,'0601 - 16:16c0035'),('0602 - 13:48','삼각김밥','c004',1,1200,1200,'0602 - 13:48c0043'),('0602 - 13:51','커피','c005',3,2500,7500,'0602 - 13:51c0050'),('0602 - 17:00','사이다','c003',3,1000,3000,'0602 - 17:00c0030'),('0602 - 17:00','삼각김밥','c004',1,1200,1200,'0602 - 17:00c0041'),('0603 - 11:20','과자','c001',3,2000,6000,'0603 - 11:20c0010'),('0603 - 11:20','컵라면','c002',1,1500,1500,'0603 - 11:20c0021'),('0603 - 11:20','사이다','c003',1,1000,1000,'0603 - 11:20c0032'),('0603 - 11:20','삼각김밥','c004',1,1200,1200,'0603 - 11:20c0043'),('0603 - 11:27','과자','c001',7,2000,14000,'0603 - 11:27c0014'),('0603 - 11:27','사이다','c003',4,1000,4000,'0603 - 11:27c0035'),('0603 - 11:27','삼각김밥','c004',3,1200,3600,'0603 - 11:27c0046'),('0603 - 11:27','커피','c005',3,2500,7500,'0603 - 11:27c00510'),('0603 - 11:27','커피','c005',10,2500,25000,'0603 - 11:27c0057'),('0603 - 11:27','도시락','c009',8,5000,40000,'0603 - 11:27c0098'),('0603 - 11:27','빵','c011',3,1200,3600,'0603 - 11:27c0119'),('0603 - 15:53','컵라면','c002',3,1500,4500,'0603 - 15:53c00211'),('0603 - 15:53','맥주','c006',3,3000,9000,'0603 - 15:53c00612'),('0603 - 15:53','우유','c008',3,1400,4200,'0603 - 15:53c00813'),('0603 - 15:53','도시락','c009',2,5000,10000,'0603 - 15:53c00914'),('0603 - 16:02','사이다','c003',4,1000,4000,'0603 - 16:02c00315'),('0603 - 16:02','커피','c005',4,2500,10000,'0603 - 16:02c00516'),('0603 - 16:02','맥주','c006',5,3000,15000,'0603 - 16:02c00617'),('0603 - 16:02','아이스크림','c007',5,1700,8500,'0603 - 16:02c00718'),('0603 - 16:14','커피','c005',3,2500,7500,'0603 - 16:14c00519'),('0603 - 16:14','맥주','c006',3,3000,9000,'0603 - 16:14c00620'),('0603 - 16:14','아이스크림','c007',3,1700,5100,'0603 - 16:14c00721'),('0603 - 16:14','도시락','c009',8,5000,40000,'0603 - 16:14c00922'),('0603 - 16:30','컵라면','c002',4,1500,6000,'0603 - 16:30c00223'),('0603 - 16:30','사이다','c003',5,1000,5000,'0603 - 16:30c00324'),('0603 - 16:30','삼각김밥','c004',3,1200,3600,'0603 - 16:30c00425'),('0603 - 16:30','커피','c005',3,2500,7500,'0603 - 16:30c00526'),('0603 - 16:30','맥주','c006',7,3000,21000,'0603 - 16:30c00627'),('0603 - 16:30','도시락','c009',8,5000,40000,'0603 - 16:30c00928');
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `CODE` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PRICE` int DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('c001','과자',2000,148),('c002','컵라면',1500,73),('c003','사이다',1000,11),('c004','삼각김밥',1200,8),('c005','커피',2500,37),('c006','맥주',3000,24),('c007','아이스크림',1700,55),('c008','우유',1400,5),('c009','도시락',5000,57),('cccc','과',5000,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiveproduct`
--

DROP TABLE IF EXISTS `receiveproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receiveproduct` (
  `DATE` varchar(100) DEFAULT NULL,
  `CODE` varchar(100) NOT NULL,
  `PLUSQUANTITY` int DEFAULT NULL,
  `RECEIVENUMBER` varchar(100) NOT NULL,
  PRIMARY KEY (`RECEIVENUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiveproduct`
--

LOCK TABLES `receiveproduct` WRITE;
/*!40000 ALTER TABLE `receiveproduct` DISABLE KEYS */;
INSERT INTO `receiveproduct` VALUES ('0531','c001',10,'c0010531'),('0531','c002',1,'c0020531'),('0531','c003',3,'c0030531'),('0531','c004',15,'c0040531'),('0601','c009',50,'c0090601'),('0603','cccc',50,'cccc0603');
/*!40000 ALTER TABLE `receiveproduct` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-03 17:12:18
