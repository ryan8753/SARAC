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
-- Table structure for table `review_comment`
--

DROP TABLE IF EXISTS `review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` varchar(200) DEFAULT NULL,
  `depth` int DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `review_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpcp7njr5bif01glg7tcm07qhv` (`parent_id`),
  KEY `FK9j7pkcpuestrwjre1a1902biu` (`review_id`),
  KEY `FK336skpbimvangdiji5g0sxlf1` (`user_id`),
  CONSTRAINT `FK336skpbimvangdiji5g0sxlf1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK9j7pkcpuestrwjre1a1902biu` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`),
  CONSTRAINT `FKpcp7njr5bif01glg7tcm07qhv` FOREIGN KEY (`parent_id`) REFERENCES `review_comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_comment`
--

LOCK TABLES `review_comment` WRITE;
/*!40000 ALTER TABLE `review_comment` DISABLE KEYS */;
INSERT INTO `review_comment` VALUES (1,'하이~!',0,NULL,1,3),(2,'배고파요',1,1,1,3),(3,'배불러요',1,1,1,4),(4,'무플방지위원회에서 나왔습니다',0,NULL,1,4),(5,'무플방지위원회에서 출장나왔습니다.',1,1,1,4),(6,'진짜 추억인데 이게 완결났네요',1,4,1,5),(7,'숙제하고 갑니다',0,NULL,1,1),(8,'123',0,NULL,1,2),(9,'ㅁㄴㅇㄹ',0,NULL,7,2),(11,'진짜 세상사람들 다 봐야 되는 책이에요!!',0,NULL,9,5),(16,'부동산 정보 감사합니다~ 제 블로그에도 와서 공매 포스팅 구경하고 가세요^^',0,NULL,5,4),(17,'저도 이 책 감명 깊게 읽었어요! 힐링되는 책이네요 :)',0,NULL,25,4),(18,'비슷한 책 추천도 가능하실까요?',1,17,25,4),(19,'박범신 작가님의 힐링 - 살아서 꽃피지 않는 영혼은 없다 책 추천드려요~',1,17,25,3),(26,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',0,NULL,23,1);
/*!40000 ALTER TABLE `review_comment` ENABLE KEYS */;
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
