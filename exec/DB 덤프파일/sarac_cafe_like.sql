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
-- Table structure for table `cafe_like`
--

DROP TABLE IF EXISTS `cafe_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cafe_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `good_bad` bit(1) NOT NULL,
  `cafe_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9r25dbbmqxau8uyamonk4qr7m` (`cafe_id`),
  KEY `FKqefsdefqxe811mk0qymv2da93` (`user_id`),
  CONSTRAINT `FK9r25dbbmqxau8uyamonk4qr7m` FOREIGN KEY (`cafe_id`) REFERENCES `cafe` (`id`),
  CONSTRAINT `FKqefsdefqxe811mk0qymv2da93` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cafe_like`
--

LOCK TABLES `cafe_like` WRITE;
/*!40000 ALTER TABLE `cafe_like` DISABLE KEYS */;
INSERT INTO `cafe_like` VALUES (3,_binary '\0',28743,4),(4,_binary '',29950,4),(5,_binary '',36725,4),(6,_binary '',36448,4),(7,_binary '\0',30320,4),(22,_binary '\0',139822,6);
/*!40000 ALTER TABLE `cafe_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07 11:25:31
