-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: sarac.tk    Database: sarac
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) DEFAULT NULL,
  `is_library_open` bit(1) DEFAULT NULL,
  `is_review_open` bit(1) DEFAULT NULL,
  `kakao_id` bigint DEFAULT NULL,
  `nickname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/profile/2430103492.gif',_binary '\0',_binary '',2430103492,'정다은'),(2,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',_binary '\0',_binary '\0',2430262127,'김치'),(3,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/profile/2429045552.gif',_binary '',_binary '',2429045552,'소정이'),(4,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/profile/2439621996.gif',_binary '',_binary '',2439621996,'신민아'),(5,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/profile/2436663314.gif',_binary '',_binary '',2436663314,'이경준'),(6,'http://k.kakaocdn.net/dn/EsCTW/btqIKrIuuEC/tCwz2SEMcqYbXijohYfiKK/img_640x640.jpg',_binary '\0',_binary '',2462094207,'준'),(7,'http://k.kakaocdn.net/dn/r01a9/btrEh7f7BSt/UTLd9DWmVCcfltlmAeKc11/img_640x640.jpg',_binary '',_binary '',2472348607,'원재호'),(8,'http://k.kakaocdn.net/dn/dHgi4S/btrL9jtMvpe/5dRUr1YElsz6JhAVmL9Ln0/img_640x640.jpg',_binary '',_binary '',2472384500,'김성재'),(9,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',_binary '',_binary '',2472406895,'yrn'),(10,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',_binary '',_binary '',2474042531,'원민석'),(11,'http://k.kakaocdn.net/dn/uIy5r/btrLR8EtiEb/HTMarKUGWY9fS3PAr1iUMk/img_640x640.jpg',_binary '',_binary '',2474043555,'이창엽');
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

-- Dump completed on 2022-10-07 11:25:39
