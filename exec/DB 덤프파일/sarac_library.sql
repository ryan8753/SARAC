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
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `library_type` varchar(255) DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48t4jttimtl0rtbohpmn6lhjl` (`isbn`),
  KEY `FKgx6ohut2yexvjuyeuvwss4dbi` (`user_id`),
  CONSTRAINT `FK48t4jttimtl0rtbohpmn6lhjl` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `FKgx6ohut2yexvjuyeuvwss4dbi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (8,'READ','9788926380192',4),(9,'READ','9788926379899',4),(10,'READ','9788926379899',3),(12,'WISH','1110000010158',8),(13,'WISH','1110001114350',8),(14,'WISH','9788926379899',8),(16,'READING','9788926379899',2),(17,'READING','9788926380192',2),(18,'READING','1110001114350',2),(19,'READING','9798960107298',2),(20,'READING','9791198021313',2),(28,'WISH','9788926380192',3),(34,'READ','9791190450430',3),(35,'READING','9791197022043',3),(36,'READ','9791196437053',3),(37,'WISH','9798938411074',3),(38,'WISH','9791197550904',3),(39,'WISH','9791197067211',3),(43,'READ','9791197511400',3),(45,'READ','9791196782634',4),(46,'READ','9791197455513',4),(47,'READING','9791196932176',4),(57,'READING','9791197193385',4),(58,'WISH','9791196764616',4),(59,'WISH','9798987007907',4),(60,'WISH','9791195271504',3),(67,'WISH','9788993925340',3),(73,'READ','9798985345773',3),(74,'READ','9798990989061',3),(75,'WISH','9791197008412',5),(76,'WISH','9791196764616',5),(77,'READING','9788943312275',5),(78,'WISH','9788926380192',5),(81,'READING','9799984986725',5),(82,'WISH','9791197455513',5),(89,'READ','9791158608569',1),(90,'WISH','9791197030185',1);
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
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
