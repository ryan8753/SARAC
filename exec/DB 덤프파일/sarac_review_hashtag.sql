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
-- Table structure for table `review_hashtag`
--

DROP TABLE IF EXISTS `review_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_hashtag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(50) DEFAULT NULL,
  `review_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp0eyqmdmma9sxguoe8sim6ldn` (`review_id`),
  CONSTRAINT `FKp0eyqmdmma9sxguoe8sim6ldn` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_hashtag`
--

LOCK TABLES `review_hashtag` WRITE;
/*!40000 ALTER TABLE `review_hashtag` DISABLE KEYS */;
INSERT INTO `review_hashtag` VALUES (1,'게임만화',1),(2,'완결',1),(3,'메이플스토리',1),(4,'만화',1),(5,'게임만화',3),(6,'만화',3),(7,'돈벌자',5),(8,'부자',5),(9,'부동산',5),(10,'맛있다',9),(11,'베이킹',9),(12,'쿠키',9),(16,'내꿈은재벌2세',13),(17,'퇴사를꿈꾸는취준생',13),(18,'회사',13),(19,'백수가짱이다',13),(20,'돈많은백수',13),(25,'취직',15),(26,'퇴사',15),(27,'돈많은백수',15),(38,'유명인',23),(39,'셀럽',23),(40,'셀러브리티',23),(41,'힘들당',24),(42,'회사',24),(43,'사회생활',24),(44,'모든사람에게',25),(45,'좋은사람',25),(54,'제빵',31),(55,'베이킹',31),(71,'ㅎㅎ',53),(72,'최고',53),(75,'고양이',59),(76,'123',60),(77,'고양이',61),(78,'감성',63),(79,'시집',63),(80,'고양이',64),(81,'감성',64),(82,'감성',66),(83,'독서인증',66),(84,'시집',66),(85,'화이팅',11),(86,'A505',16),(87,'화이팅',16),(88,'좋은책',16),(89,'하하',16);
/*!40000 ALTER TABLE `review_hashtag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07 11:25:32
