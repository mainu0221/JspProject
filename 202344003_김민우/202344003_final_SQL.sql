-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: fortunedb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `fortunerecords`
--

DROP TABLE IF EXISTS `fortunerecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fortunerecords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `fortune` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fortunerecords_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userlogin` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fortunerecords`
--

LOCK TABLES `fortunerecords` WRITE;
/*!40000 ALTER TABLE `fortunerecords` DISABLE KEYS */;
INSERT INTO `fortunerecords` VALUES (22,8,'안녕하세요, 테스트2님. 오늘의 운세를 알려드리겠습니다. 오늘은 새로운 시작을 위한 좋은 기회가 찾아올 것입니다. 자신의 가능성을 믿고 도전하는 것이 중요한 날이니, 자신을 믿고 앞으로 나아가 보세요. 특히 자신의 의견을 확신하고 행동하는 것이 긍정적인 결과를 가져다줄 것입니다. \n\n오늘의 행운의 숫자는 7이며, 행운의 색깔은 파란색입니다. 이 두 가지를 주의 깊게 살펴보고 오늘 하루를 시작해 보세요. 행운이 함께할 것입니다. 좋은 하루 보내시길 바라며, 늘 행운이 함께하기를 기원합니다. 함께해주셔서 감사합니다.','2024-11-26 01:56:00'),(24,5,'안녕하세요, 김민우님. 오늘의 운세를 알려드리겠습니다. 오늘은 당신에게 행운이 함께할 것입니다. 긍정적인 에너지가 넘치는 날이니 자신을 믿고 도전해보세요. 오늘의 행운의 숫자는 7이며, 행운의 색깔은 파란색입니다. 이 색을 계속해서 마음가짐으로 삼아주세요. 오늘은 뜻밖의 좋은 일들이 일어날 수 있으니 긍정적으로 생각하고 행동해보세요. 좋은 하루 되시길 바라며, 행운과 행복이 늘 함께하길 기원합니다.','2024-11-30 03:40:37'),(29,10,'오늘 홍길동님의 운세를 살펴보겠습니다. 오늘은 새로운 시작이 당신에게 찾아올 것입니다. 어제의 어려움은 지나갔고, 오늘은 새로운 기회가 당신을 기다리고 있을 것입니다. 자신을 믿고 새로운 도전에 나서보세요. 특히 온종일 긍정적인 에너지를 발산하면서 주변 사람들과 소통하는 것이 중요할 것입니다. \n\n오늘의 행운의 숫자는 7이며, 행운의 색깔은 파랑입니다. 파란색의 조용하고 안정적인 에너지를 받아들이면서 오늘을 시작해보세요. 행운이 함께할 것입니다. 오늘 하루 행운을 빕니다.','2024-12-01 23:18:19'),(30,10,'약간의 어려움이 있을 수 있지만, 극복할 수 있을 것입니다. 오늘은 주변 사람들과의 소통이 중요해 보입니다. 활발한 대화를 통해 해결책을 찾을 수 있을 것입니다. 당신에게 가장 중요한 것은 자신의 목표를 분명히 하고 그것을 향해 노력하는 것입니다. 운세의 숫자는 7이며, 행운의 색깔은 파란색입니다. 오늘 하루도 화이팅하세요!','2024-12-01 23:18:26'),(31,5,'오늘은 김민우님의 운세는 매우 밝고 긍정적입니다. 긍정적인 에너지에 가득 차 있어서 어떤 일이든 성공할 것입니다. 오늘은 건강과 행운이 함께하며, 주변 사람들과의 소통이 활발해질 것입니다. 특히 새로운 인연을 만나게 될 수도 있으니 기회를 잘 살려보세요. 행운의 숫자는 7이며, 행운의 색깔은 파란색입니다. 오늘 하루를 즐기시고 행운이 가득한 하루를 보내시길 바랍니다.','2024-12-01 23:45:49');
/*!40000 ALTER TABLE `fortunerecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin`
--

DROP TABLE IF EXISTS `userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlogin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin`
--

LOCK TABLES `userlogin` WRITE;
/*!40000 ALTER TABLE `userlogin` DISABLE KEYS */;
INSERT INTO `userlogin` VALUES (1,'admin','admin','2024-11-21 14:45:29'),(5,'test1','test1','2024-11-21 15:17:20'),(8,'test2','test2','2024-11-22 14:55:54'),(10,'test3','test','2024-12-02 08:05:53');
/*!40000 ALTER TABLE `userlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofile`
--

DROP TABLE IF EXISTS `userprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userprofile` (
  `user_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `birth_date` varchar(10) NOT NULL,
  `calendar_type` enum('solar','lunar') NOT NULL,
  `birth_time` varchar(8) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `userprofile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userlogin` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofile`
--

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
INSERT INTO `userprofile` VALUES (5,'김민우','male','1999-02-21','solar','06:30','2024-11-21 15:27:44','2024-11-30 12:40:31'),(8,'김민지','female','1993-12-25','solar','15:45','2024-11-22 14:56:31','2024-11-30 12:42:12'),(10,'홍길동','male','2004-12-25','solar','07:12','2024-12-02 08:17:43','2024-12-02 08:18:03');
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-02 18:32:22
