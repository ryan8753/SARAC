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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_score` int DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `is_secret` bit(1) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `isbn` varchar(13) NOT NULL,
  `writer` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd536wsovvqufhxix285i50wtq` (`isbn`),
  KEY `FK19ilkthnmufi16tcs4l7vwa2r` (`writer`),
  CONSTRAINT `FK19ilkthnmufi16tcs4l7vwa2r` FOREIGN KEY (`writer`) REFERENCES `user` (`id`),
  CONSTRAINT `FKd536wsovvqufhxix285i50wtq` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,5,'코믹메이플이 100권을 기점으로 완결된다는데\n너무 슬픕니다 ㅠㅠ\n99권에 있는 데미안과 도도의 싸움..\n가슴이 웅장해지는군요',_binary '\0','코믹 메이플스토리가 완결 기념 독후감','9788926380192',4),(3,3,'곧 완결이라니\n아자아자 화이팅',_binary '\0','메이플스토리 98권~','9788926379899',4),(5,5,'너무 유익해요',_binary '\0',NULL,'9788993925340',8),(6,3,'1234567890',_binary '\0',NULL,'9788926379899',2),(7,3,'ㅁㄴㅇㄹ호',_binary '\0',NULL,'9788926380192',2),(9,5,'책이름을 베이킹의 정석이라 바꾸고 재 출간해야 할 정도로 꼭 필요하다고 생각해요!!!',_binary '\0','베이킹의 정석입니다.','9791197030185',5),(11,3,'화이팅',_binary '','ㅎㅎ','9788926379899',3),(13,5,'회사를 붙고 싶어서 간절히 기도하는데\n막상 붙으면 뛰쳐나오고 싶은 심리를 잘 표현한 책이다\n취직도 안했지만 벌써 퇴사하고 싶다~',_binary '\0','회사란... 무엇일까 고민하게 되는 책','9791196782634',4),(15,4,'역시 인생은..\n대리만족이던가?\n취직도 안했는데 퇴사를 하는 z세대의 김아무개씨가 부러웠다..\n어랏..? 눈에서 왜 땀이\n어쩌면 조금 슬퍼졌을지도?',_binary '\0','취직도 안했는데 퇴사부터 하고 싶은 녀석','9791197455513',4),(16,4,'좋은책입니다',_binary '','야호','9798987007907',3),(23,4,'이 책을 읽으면서 저도 셀럽의 꿈을 이룰 수 있을 것 같습니다.\n인스타그램 10k?\n제 목표는 쥐쥬래곤의 팔로워를 뛰어 넘는 겁니다.',_binary '\0','나도 이제 셀럽?','9791196932176',4),(24,4,'사회 생활을 하는 것에 있어서 어떻게 하는 것이 현명한지 알려주는\n조직 생활계의 바이블인 것 같다.\n하지만, 책에서 알려주는 대로 하는 것이 마냥 쉽지는 않은 현실이다.\n오늘도 인생이 참 쓰다',_binary '\0','조직 문화에 대한 깊은 고찰.','9791197193385',4),(25,5,'모든 사람에게 좋은 사람일 필요 없다는 말이 감명깊어 읽게 되었습니다..! 정말 좋은 책이니 읽어보세요 강력 추천합니다!!',_binary '\0','소정이님의 리뷰입니다.','9791196764616',3),(31,5,'뿌듯했습니다~!',_binary '\0','쌀베이킹의 교과서','9791197122217',4),(53,3,'ㅎㅎ',_binary '\0','잘읽었습니다','9791158608569',3),(59,3,'고전 명작!\n이만한게 없다',_binary '\0','이경준님의 리뷰입니다.','9788943312275',5),(60,3,'초등학교 2학년 부터 무려 20년간 재밌게 보고 있습니다',_binary '\0','역시 코믹 메이플이 짱이야','9788926379899',2),(61,3,'좋아좋아',_binary '\0','고양이가 좋아좋아','9788943312275',2),(63,5,'추천추천',_binary '\0','시집','9791158608569',1),(64,3,'귀여워',_binary '\0','고양이조아','9788943312275',1),(66,5,'인증합니다!',_binary '\0','독서 인증','9791158608569',1);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
