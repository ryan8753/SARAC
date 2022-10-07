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
-- Table structure for table `review_photo`
--

DROP TABLE IF EXISTS `review_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_photo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `photo_url` varchar(200) DEFAULT NULL,
  `review_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK80ti8nek4uv8vn4vjhpre6mwg` (`review_id`),
  CONSTRAINT `FK80ti8nek4uv8vn4vjhpre6mwg` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_photo`
--

LOCK TABLES `review_photo` WRITE;
/*!40000 ALTER TABLE `review_photo` DISABLE KEYS */;
INSERT INTO `review_photo` VALUES (1,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/be5a3680-874f-49f9-9619-4c1352a633a8-1.jfif',1),(2,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/bbca9224-b012-4446-bf9b-0976c0173ae1-6.jpg',6),(5,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/682d66b7-0a1e-4045-847b-8d19ff75518e-9.png',9),(9,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/b4d94e87-bf19-4305-91a4-6672eaf7d3e4-13.png',13),(10,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/090bd5da-d1d2-4751-8528-5f1b81ae50c6-13.png',13),(11,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/54244a48-d2fc-41fa-b635-a8e5cae57745-13.png',13),(14,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/1e6547bb-f128-40fb-ab6f-616f4a6031a7-15.gif',15),(15,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/32f71719-99a7-43e9-b005-87b54fc34097-16.gif',16),(22,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/2e56e63a-7c89-4319-a49e-4952222e5f1f-23.gif',23),(23,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/81b1f529-f693-4e0d-9d20-0739c380caf9-24.gif',24),(24,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/d344c4f2-a2cd-45cc-a9a5-05fdd2984ff0-24.gif',24),(25,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/9d1ba637-b020-44da-92d8-618a5fc753c0-25.jpg',25),(26,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/241940bd-3ea8-4f9b-9ea5-915a9fbc8e52-25.jpg',25),(32,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/1eec8f4f-cf92-448a-bedb-5e2f7a7158ab-31.jpg',31),(65,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/f5e8a018-126a-471f-83ce-1061b6d6cc6b-53.jpg',53),(72,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/16c09bfd-a0d6-420b-b21e-ae5cec47fe2c-59.jpg',59),(73,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/d5259f29-0358-43f3-a957-5fb517854bc1-60.jpg',60),(74,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/77e637d3-f8a1-4022-8ae6-7f6a3e66a5f0-61.jfif',61),(75,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/f4a54fb9-110e-4f6a-954c-6fc54d3fd627-61.jfif',61),(76,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/95913614-b30b-43cb-93dd-6f88a4a9eca4-61.jfif',61),(77,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/b0a0546a-eb28-4b89-b469-25a28ca64b02-61.jfif',61),(78,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/0348daa9-1990-4edc-9888-ff6d6ec34a89-63.png',63),(79,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/dad31371-b8aa-410d-b927-a193dfd59019-7.jpeg',7),(81,'https://sarac-a505.s3.ap-northeast-2.amazonaws.com/review/d1f9908c-4b35-48d6-b89c-79982f7cbc04-66.jpg',66);
/*!40000 ALTER TABLE `review_photo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07 11:25:38
