-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db01
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `director_info`
--

DROP TABLE IF EXISTS `director_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director_info` (
  `id` varchar(255) NOT NULL,
  `director_avatar` varchar(255) DEFAULT NULL,
  `director_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director_info`
--

LOCK TABLES `director_info` WRITE;
/*!40000 ALTER TABLE `director_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `director_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_info`
--

DROP TABLE IF EXISTS `production_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_info` (
  `id` varchar(255) NOT NULL,
  `production_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_info`
--

LOCK TABLES `production_info` WRITE;
/*!40000 ALTER TABLE `production_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `production_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star_info`
--

DROP TABLE IF EXISTS `star_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `star_info` (
  `id` varchar(255) NOT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `star_avatar` varchar(255) DEFAULT NULL,
  `star_description` varchar(255) DEFAULT NULL,
  `star_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star_info`
--

LOCK TABLES `star_info` WRITE;
/*!40000 ALTER TABLE `star_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `star_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `email_valid_flag` tinyint NOT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_admin` int DEFAULT '0',
  `l_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('c0a802ad-8acb-10fa-818a-cb8120a50000',NULL,'borolaydo941',1,NULL,NULL,NULL,NULL,'$2a$10$PVx1bQYKywaJ0MK3G5hN6OV3hjj2iARd9aT9ZJFIx5r76Lh4wD3fi','2023-09-25 17:42:32','2023-09-25 17:42:32',NULL,'borolaydo'),('c0a802ad-8ad4-1d07-818a-d46ddfdf0000',NULL,'admin11',1,NULL,NULL,1,NULL,'$2a$10$vX1tRyU0Nb0CnaSKNY4rNOJfqktgDs1RqRe2TiyOhjAzqcVqT84Za','2023-09-27 11:18:05','2023-09-27 11:18:05',NULL,'admin11');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_token` (
  `refesh_token` varchar(255) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `expiration_time` bigint NOT NULL,
  PRIMARY KEY (`refesh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
INSERT INTO `user_token` VALUES ('2a4a0600-708e-48a4-9f27-51e718e8b5c5','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTU3ODExMTMsImV4cCI6MTY5NjY0NTExM30.Gmu_oI1lwJSXtHZfUga07OfC2MkU5opUFCEQ1KNXpumC8GtPCwjIZ6jD4owtZAKOrRlyQsIOBb31Lh5xjouOgA',1753032704),('2e71f6e6-1b20-4d67-8242-e79db6e168cc','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTU2MzE1MzUsImV4cCI6MTY5NjQ5NTUzNX0.GxETSaRZqQTjG3PbxIG5Brrfxn7uH1VujHJmC2VkVxE-2sZ1wCgM4ZZbEBqS8Z0h8Lqi9Pz3AXLbIRy0B2XcHA',1753032704),('be83fbc4-d321-4c33-b741-d5439cf32bbf','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTU3ODkyMjMsImV4cCI6MTY5NjY1MzIyM30.fNZ__eZJe09l9wYbn1qlmAh1xMIb9vqz5sfJpyBOo7F2Kc-hLlJ6W109PG3BkhRxWNgxqvnUm4O-cBxd4KA1Mw',1753032704),('c29292fc-3ce0-4f7d-a582-ebabd8774047','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTU3ODEyODIsImV4cCI6MTY5NjY0NTI4Mn0.eHW-aVEjQuAU8Dx1ceYQz7uTCgVP-zuPFxIAea6zLSH9LSp8w7XGSuxkezKSKnJe8msuwfgxNq9cabNXLI1IAw',1753032704);
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-19 15:41:24
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db02
-- ------------------------------------------------------
-- Server version	8.0.32

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-19 15:41:25
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db03
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `genre_type`
--

DROP TABLE IF EXISTS `genre_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre_type` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `display_name` varchar(255) NOT NULL,
  `genre_kbn` int DEFAULT '1',
  `movie_grade_id` varchar(255) DEFAULT NULL,
  `order_score` int DEFAULT '100',
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_type`
--

LOCK TABLES `genre_type` WRITE;
/*!40000 ALTER TABLE `genre_type` DISABLE KEYS */;
INSERT INTO `genre_type` VALUES ('c0a802ad-8aca-14f4-818a-ca85990c0000',0,'Hành động',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599180001',0,'Phiêu lưu',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190002',0,'Hài',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190003',0,'Drama',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190004',0,'Giả tưởng',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190005',0,'Ma quỷ',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190006',0,'Âm nhạc',1,NULL,160,'2023-09-25 04:07:47','2023-09-25 15:40:44','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190007',0,'Huyền bí',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190008',0,'Lãng mạn',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca8599190009',0,'Khoa học viễn tưởng',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca859919000a',0,'Thể thao',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca85991a000b',0,'Kinh dị',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update'),('c0a802ad-8aca-14f4-818a-ca85991a000c',0,'Viễn tây',1,NULL,100,'2023-09-25 04:07:47','2023-09-25 04:07:47','postman_update');
/*!40000 ALTER TABLE `genre_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_banner`
--

DROP TABLE IF EXISTS `movie_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_banner` (
  `id` varchar(255) NOT NULL,
  `banner_src` varchar(255) NOT NULL,
  `del_flg` int NOT NULL DEFAULT '0',
  `movie_id` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_banner`
--

LOCK TABLES `movie_banner` WRITE;
/*!40000 ALTER TABLE `movie_banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_director`
--

DROP TABLE IF EXISTS `movie_director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_director` (
  `id` varchar(255) NOT NULL,
  `director_id` varchar(255) NOT NULL,
  `director_kbn` tinyint NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_director`
--

LOCK TABLES `movie_director` WRITE;
/*!40000 ALTER TABLE `movie_director` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `genre_id` varchar(255) NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_gradle`
--

DROP TABLE IF EXISTS `movie_gradle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_gradle` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `member_visible_flg` int NOT NULL DEFAULT '1',
  `movie_grade_name` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sort_no` int NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_gradle`
--

LOCK TABLES `movie_gradle` WRITE;
/*!40000 ALTER TABLE `movie_gradle` DISABLE KEYS */;
INSERT INTO `movie_gradle` VALUES ('16969108947186-4f33-a52a-ac422b682082',0,1,'Basic Movie','2023-10-10 04:08:14',10,'2023-10-10 04:08:14',NULL),('1696920641941d-479d-b458-e478686196cd',0,1,'Middle Movie','2023-10-10 06:50:41',20,'2023-10-10 06:50:41',NULL),('16969206504911-4569-b9cb-682ba4745c32',0,1,'Middle Plus Movie','2023-10-10 06:50:50',30,'2023-10-10 06:50:50',NULL),('1696920664397a-4be0-94c3-6dbce8a89913',0,1,'Premium Plus Movie','2023-10-10 06:51:04',50,'2023-10-10 06:51:04',NULL),('16969206701494-45f6-8c06-1612c6b9ca25',0,1,'Premium Movie','2023-10-10 06:51:10',40,'2023-10-10 06:51:10',NULL),('16969299537711-4e2a-b815-9d2958f55fca',0,1,'Basic plus Movie','2023-10-10 09:25:53',20,'2023-10-10 09:25:53',NULL),('16969300169943-4588-873c-625688d386ce',0,1,'ABC','2023-10-10 09:26:56',990,'2023-10-10 09:26:56',NULL),('16969300242149-4325-9d4d-0cd0c763ba24',0,1,'XYZ','2023-10-10 09:27:04',990,'2023-10-10 09:27:04',NULL),('1696930042282e-47ee-9fe3-d48951479cce',0,1,'QWE','2023-10-10 09:27:22',990,'2023-10-10 09:27:22',NULL),('1697442571289a-4b14-8ad5-7b4d902c1f93',0,1,'QWE','2023-10-16 07:49:31',990,'2023-10-16 07:49:31',NULL);
/*!40000 ALTER TABLE `movie_gradle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_info`
--

DROP TABLE IF EXISTS `movie_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_info` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `duration_min` bigint NOT NULL,
  `movie_name` varchar(255) NOT NULL,
  `movie_sub_name` varchar(255) DEFAULT NULL,
  `production_id` varchar(255) NOT NULL,
  `regist_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `thumnail` varchar(255) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) NOT NULL DEFAULT 'postman_update',
  `year_release_at` bigint NOT NULL,
  `banners` text,
  `movie_grade_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_info`
--

LOCK TABLES `movie_info` WRITE;
/*!40000 ALTER TABLE `movie_info` DISABLE KEYS */;
INSERT INTO `movie_info` VALUES ('c0a802ad-8ad4-1e12-818a-d490219f0003',0,'Dom and the crew must take on an international terrorist who turns out to be Dom and Mia\'s estranged brother.',143,'F9: The Fast Saga','Fast & Furious 9: Huyền Thoại Tốc Độ','c0a802ad-8a16-1720-818a-16ca3af0000e','2023-09-27 11:55:30','fdae3c9f-0392-441c-8b5d-fea8995405f7.jpeg','2023-09-27 11:55:30','postman_update',2021,NULL,NULL);
/*!40000 ALTER TABLE `movie_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_star`
--

DROP TABLE IF EXISTS `movie_star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_star` (
  `id` varchar(255) NOT NULL,
  `caster` varchar(255) NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  `sort_no` int DEFAULT NULL,
  `star_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_star`
--

LOCK TABLES `movie_star` WRITE;
/*!40000 ALTER TABLE `movie_star` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_trailer`
--

DROP TABLE IF EXISTS `movie_trailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_trailer` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `movie_id` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `trailer_title` varchar(255) DEFAULT NULL,
  `trailer_url` varchar(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_trailer`
--

LOCK TABLES `movie_trailer` WRITE;
/*!40000 ALTER TABLE `movie_trailer` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_trailer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-19 15:41:25
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db05
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `charge_info_plan`
--

DROP TABLE IF EXISTS `charge_info_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge_info_plan` (
  `id` varchar(255) NOT NULL,
  `day_kbn` int DEFAULT '0',
  `del_flg` int DEFAULT '0',
  `plan_kbn` int DEFAULT '0',
  `plan_name` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `time_end` datetime(6) DEFAULT NULL,
  `time_start` datetime(6) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge_info_plan`
--

LOCK TABLES `charge_info_plan` WRITE;
/*!40000 ALTER TABLE `charge_info_plan` DISABLE KEYS */;
INSERT INTO `charge_info_plan` VALUES ('1696995583894b-4e44-a4e9-fe6bfa45661b',0,0,0,'Basic plan','2023-10-11 03:39:44',NULL,'2023-10-11 12:39:14.301000','2023-10-11 03:39:44','postman_update');
/*!40000 ALTER TABLE `charge_info_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge_info_set`
--

DROP TABLE IF EXISTS `charge_info_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge_info_set` (
  `id` varchar(255) NOT NULL,
  `charge_info_plan_id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `movie_grade_id` varchar(255) NOT NULL,
  `price` int DEFAULT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `seat_grade_id` varchar(255) NOT NULL,
  `site_gradle_id` varchar(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge_info_set`
--

LOCK TABLES `charge_info_set` WRITE;
/*!40000 ALTER TABLE `charge_info_set` DISABLE KEYS */;
INSERT INTO `charge_info_set` VALUES ('1696995584072b-499d-8f81-aee20f4f5332','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955841001-4041-a16d-ea7a5b1bcd6c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955841263-40e7-a477-2828ca54bef9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955841501-4c1c-992b-6bd9c097832c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584179d-432a-8639-fe161dcf6aa0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584208f-4b56-b2d8-a302e6f2e7d8','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584234b-4f15-85b4-4d0f7e00f2cf','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955842598-4a02-a67f-a8feeed4adda','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955842873-41bc-837b-7d3269d1d595','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:44','16969136174891-4d2c-9b04-95021609d2bc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955843143-44d8-9cf2-3dfc4cfd5824','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584345e-4d26-964d-655cadd2be19','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955843702-4d51-b3dc-6a67905c476a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955843984-4a2c-8dcb-61d346b52e01','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584425e-4b5f-8a5a-1bc0ef40ba73','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955844534-4c37-ab2b-92b8f909b11f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955844809-479a-9442-4669cf18896f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955845033-4e27-9fbc-4e0061318b8d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955845290-49d0-ac58-2de77273a231','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:44','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955845530-4c42-a5f3-328be3179827','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955845791-4b4a-9e9a-10d6c0211e02','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955846030-4291-b85a-ee959181df99','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584624e-4ade-939c-9e6fb0e07023','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584655a-4518-a607-042710842e9f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955846796-4a31-aec6-8d1246396eca','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584702f-4119-9005-668c7bad5b04','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584726d-4efe-a2f5-9e767d81239b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955847480-4328-b9f6-0a1c5b59e094','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:44','1696920417933b-46ad-90ca-67c614c75608','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584796d-47aa-b5ed-d800e9db38ed','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955848207-4f43-b9b8-afdd43c4927f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955848510-49c8-9ac6-e1ee3347cc6c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584877b-4076-8807-15d36aefb657','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584901c-48ba-aaea-859b42dc4672','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955849264-4d1b-a9ef-d2d3eeeecf7a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955849523-48c0-8f59-90ab182bd10b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('1696995584979b-4d19-acbe-be2cc2c9c19a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:44','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:44','postman_update'),('16969955850102-4447-b339-dbd5a2a93794','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:45','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955850405-47f7-b1e7-f82acd37e428','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955850636-481c-a488-1585958641ca','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585088c-41e7-a333-d6b32cbb93bc','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955851157-46b7-b0e9-bfea2e7047a3','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955851405-4056-b4a2-f12886be19b2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955851655-4aaa-8eb1-ed8aa4d0d5aa','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955851966-45d5-942c-36bbf75b2ef6','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955852790-4fe9-8fc8-c0af7d26610a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955853158-4816-9eee-ce7d6d864254','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:45','16969204267977-47c0-8850-e9cb40000847','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955853478-4cf3-bd19-bdff753d29b2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585371c-46cf-b5f2-5a5d6c816fac','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585395a-4ea0-b9a1-e4eb31f2de46','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955854222-4433-8bdd-d45cc2b55257','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955854551-4e28-82cc-ceb8a7582be2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585491d-4d39-9ee1-fc2b284119d8','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585529c-43d4-9943-261e321f364b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585560f-4497-8b5b-9a1f5c5958ec','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('16969955855917-422d-b452-6cb219bb808c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:45','16969204805543-471d-80ac-33b3fca0b6fc','1696913612645a-403f-bf60-4ebb2265611a','2023-10-11 03:39:45','postman_update'),('1696995585640e-41c4-b1fb-ee5195ece48c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955856798-42ad-a2c5-6e0dab96c2fd','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955857032-4095-8b35-48ceb5e109b5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955857289-49e7-b9b3-49e7bc57aa96','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('1696995585752d-49a8-af3c-1db46965e18f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955857880-41f5-ae1f-59473145fc77','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955858658-48ec-98bf-84558da00181','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('1696995585895b-418e-b011-947d72de1313','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('1696995585922d-4f0f-87b8-b44b0d2ac9a5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:45','16969136174891-4d2c-9b04-95021609d2bc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955859548-4a3c-846e-ca9386a8458f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:45','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:45','postman_update'),('16969955859975-4c89-8e46-e3c3c9a191d9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586027d-44b5-8a80-c4e18371900e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586054d-4bcd-9d13-e35959d9122c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586079a-4f03-9c87-8323442954e0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586100c-4120-8ba1-12eca4fdaa77','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586124a-4edd-b1f7-3654901179a9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955861452-4edf-83dc-f82a3b547c9e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586168e-404f-9567-77c8338d3e1d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:46','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955862046-4365-8b71-307f258b534b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955862305-4977-a3a3-5b1522390d0c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955862530-4047-a9fa-99e94555f313','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955862752-4d9e-83e4-a4ecb5ba23ab','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586295e-41ec-9a66-27a20d038ba4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955863180-4d4b-b8f2-a691722777c2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586341e-4fe8-9096-5086acbf2ff5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586365d-4122-bdef-015060475594','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955863916-4dc3-b774-c91a13fcce4c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:46','1696920417933b-46ad-90ca-67c614c75608','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586414e-4b52-b7e6-f955ddcff34a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955864383-4f56-bbf9-d0da682e1799','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586464c-4bc9-a26d-6985f3b41b84','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955864904-48b5-a91b-2b771836ee47','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586518a-41b6-8761-e1149c714b95','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586555f-44da-a382-76a7cc392fd9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955865880-4552-b42f-5bd46650ebad','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955866165-4558-85f9-7388fd939379','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955866449-4f87-b604-db4503b82c66','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:46','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586666e-4d34-ade3-43c04116ed2e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586707f-4d35-a4d1-2df592d4d061','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586732c-4750-b33d-eb7e81e04136','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586762b-4553-be8b-6aa390e3c9af','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955867841-41c9-be59-c821a2b3060a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955868190-4d1f-9775-b4db9c353765','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586843c-45ae-99eb-72c0ae0a1ae5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955868646-4f7b-9f4b-55a80aa6d3e0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955868842-45cd-8b0f-f16b102c9af0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:46','16969204267977-47c0-8850-e9cb40000847','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955869077-4c4e-88e5-76bf602bb526','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:46','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955869383-4bda-999b-e8d8ad89d0b0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:46','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('1696995586966d-4187-8fcc-0d33728f24ce','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:46','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955869892-4716-8369-f1c492060d1b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:46','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:46','postman_update'),('16969955870144-4d91-922a-63ef7854921f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:47','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:47','postman_update'),('1696995587033c-4553-9053-e9448dbdf9d5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:47','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:47','postman_update'),('16969955870545-4eb4-a92f-ec8c46355810','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:47','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:47','postman_update'),('1696995587081e-4d63-9437-4f481a34d806','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:47','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:47','postman_update'),('16969955871041-49b9-b287-32e15cd15eef','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:47','16969204805543-471d-80ac-33b3fca0b6fc','16969207150595-419e-9b2d-e5536bb4513e','2023-10-11 03:39:47','postman_update'),('16969955871308-4833-805d-db8dd5ae0221','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587155b-4688-82a1-c30634988f5d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955871827-43c3-af73-c7bb506ae01e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587207e-4ffa-97ab-15c117a80ba2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587232f-4c36-ab20-6aeaba4880d0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955872593-4d6a-9af4-225839e5bc3a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955872863-4001-8f9a-e98f36c87457','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955873100-43fd-8bc6-f9fb925edd9a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955873380-4d84-85eb-cdf3716211f3','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:47','16969136174891-4d2c-9b04-95021609d2bc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955873699-46a2-86b0-32a29b314b94','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587400d-4ab1-a138-1e0b3f58c414','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955874275-458e-99d0-4fc64ef8fa05','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955874540-4144-8142-968132d25ba2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955874814-4086-80cc-a77e009c47b7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955875043-435e-b209-b483444cdac4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955875338-4238-9c1c-c3ccdf175ee7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587559e-469c-b9c2-c2007d42dc8e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955875870-44cc-976f-6eac9dc2c7ea','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:47','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955876163-4bdd-baac-86b8c50a8e03','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955876480-4569-9487-cb754f69e49c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955876738-44f2-94ad-fb395619e9ff','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587705b-44f6-a71e-5965885034a8','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955877347-4a51-8d74-5d6757790a80','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955877656-423b-9466-85045a191bdb','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587797d-4b74-b313-fec0412d95f6','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587819e-4c79-a042-9fe601b8da81','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955878496-4bae-87ba-a5203dcade63','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:47','1696920417933b-46ad-90ca-67c614c75608','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587878a-48dc-b3c5-12f80ed4db03','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:47','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955879043-4b2d-9334-4765eb68fb10','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:47','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587931b-4b61-afd3-78a0c913ab0e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:47','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955879650-42fd-a57e-c3443017126d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:47','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('1696995587996d-4ad6-aebc-7769d8f14075','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:47','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:47','postman_update'),('16969955880206-4560-8260-382e1357bc6e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:48','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955880493-4e7d-b845-70245cfd9051','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:48','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955880723-4315-91f8-f8b40f05087d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:48','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955881064-41ee-9a8d-f8d5b825c1a0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:48','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955881323-4c6f-91be-eaaa7c37b3c1','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588161d-4999-aed4-876fce9d3718','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588185b-4c83-9625-2f182ae1ff6c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588206c-46b8-a493-419e09c2ff37','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955882315-4cf4-8066-f40b746d1a24','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955882515-4454-a0f8-c4d4954cd634','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955882789-4315-91ac-748e23d9a7c0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955883017-4b4a-a872-43183da8efa9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588327f-48d3-89a2-df9762fc8eff','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:48','16969204267977-47c0-8850-e9cb40000847','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955883652-4b9f-9873-81a25dbbf8fc','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955883977-4057-9a6a-ecdb9071631a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955884290-4531-ba40-73ff7c316227','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588462e-43e7-84c8-f9b70b0c42ad','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588495d-4410-8fb0-bb3e6d9d5e74','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955885429-4b45-82bb-bf6019c43b9a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955885720-49e5-8142-3eb87c16ec8f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('1696995588599e-4061-bc75-3d674cde249b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955886402-4346-8b39-dfd7d770a19b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:48','16969204805543-471d-80ac-33b3fca0b6fc','16969207105705-4be0-87f8-c300eab48610','2023-10-11 03:39:48','postman_update'),('16969955886986-4e1c-8373-fff078075e0a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955887443-415f-a661-ffac7bc91fbb','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955887841-4844-bbc4-93abd139dd61','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955888155-4ee6-a1c5-1808c662da5e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955888555-4f56-8131-5bae5f7a19de','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955888888-4387-b536-056af7da8f33','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955889146-4886-b385-671035c4afb3','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('1696995588957d-4030-99dc-83b02f419aaa','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('1696995588981e-4e2e-ba62-c91a47843863','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:48','16969136174891-4d2c-9b04-95021609d2bc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:48','postman_update'),('16969955890087-48a0-88d0-1fa2b5f8a370','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955890492-4bbf-a067-8789b714788b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955890735-449d-8ad2-ea6c6dba32a0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589100c-46d4-ab1d-0aa2f43554f2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589126c-4512-b72a-f75fdecbf833','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589151b-4bce-aa76-b0e312dfd9da','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955891780-4905-8612-d9d9a3e5b386','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589213e-466f-be62-ab84f1ae282c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955892357-48a1-8ddf-15b6dce85c00','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:49','1696920404566a-4b2f-9ec1-b263f0fe92d4','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589263c-48ed-a7ae-3b7cfb0b85d9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589287e-4c3c-81c6-bde120811675','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955893131-4fbb-abda-4d064a86ec7d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955893361-4e25-a241-268520a28ea7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955893602-408a-9049-6ee7bd9de489','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955893866-48aa-b32f-b18cbba5547c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589406e-4141-afcc-96ca36d63bbb','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955894276-4861-8f35-01fa6b9bfca7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955894488-4a2a-9abd-fabb15116dad','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:49','1696920417933b-46ad-90ca-67c614c75608','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955894686-4fc6-b836-2dc27b20ba56','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955894901-444c-8752-51c7e526e73b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589514b-487e-84f3-c18a321a0276','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955895347-4b20-929f-fc012a5df7e9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589559a-470f-a5fd-c6ec8945f749','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955895866-445d-9b04-1ea61093f15a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955896080-4ca0-8373-5229762cdbf1','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955896343-4353-b59e-f4a921f65ce1','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955896536-46c7-b970-0e5989516f04','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:49','1696920433515c-4ff8-8ee2-93d4a84ccf68','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955896768-4f6e-8a61-77a3087316f5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955897097-4bbe-85d4-1dae1f07acd2','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955897322-4277-8703-d98ac8d5bc4f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589751b-4e9d-bd6f-fe457b0d6c6c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955897763-4964-b623-663c98c4544a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955898014-4a63-a7ea-9e09f9613922','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955898258-41ec-b9c6-a17b71d7f51d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589864e-4d7c-8edf-ea65db1e2e68','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589889c-46d4-a517-a4e19a6f7cd5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:49','16969204267977-47c0-8850-e9cb40000847','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589908e-478f-a2e7-9b9075e15cb4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:49','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955899297-4bc3-8a88-530ae5fde032','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:49','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589950f-4f44-b8d8-fa03a7fdbae3','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:49','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955899727-479e-86fd-ba9488fda88b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:49','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('1696995589997a-4948-8d3c-57b66e2260e8','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:49','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:49','postman_update'),('16969955900188-42b1-b942-7c244839d8c5','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:50','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:50','postman_update'),('16969955900401-44a4-9c69-1d00875aa211','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:50','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:50','postman_update'),('16969955900684-4671-85a7-2f195fe32a67','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:50','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:50','postman_update'),('16969955900960-4364-8d03-e64e9caf5c53','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:50','16969204805543-471d-80ac-33b3fca0b6fc','1696920725231b-4827-87bc-a6783d9f97bd','2023-10-11 03:39:50','postman_update'),('1696995590118a-4ea1-b684-9573c52f3426','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955901410-456f-9c8a-5795d80b9834','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590164b-4d31-b7c8-8e5a15a0912d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590186e-41f4-ad07-1a90d9f6ab19','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955902140-4921-a412-b62e4d6226de','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590254c-4af1-adb5-f2c10948eeb0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590272c-4c31-b560-168fc105a0d0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955902942-4885-be21-94ffa0aca35c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590320b-4239-9221-3ec7f824c2a3','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:50','16969136174891-4d2c-9b04-95021609d2bc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955903449-441b-8431-9b92996836fb','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955903630-4322-a2c7-b8919770eb52','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955903826-4945-b395-63615352cdf4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590408b-4947-bcbb-2350ba0573d1','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590434c-400f-8641-795cfb39f081','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590461b-4310-a38f-09a5d5ed4341','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590484b-4dd0-bd51-ea615e5d1383','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590513c-4852-a3a4-a921cad177ac','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955905382-4837-a90f-e70995c72678','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:50','1696920404566a-4b2f-9ec1-b263f0fe92d4','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590559f-4a3c-aaff-a55f0b960d5e','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590585f-4469-b325-4ab4e3717027','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955906084-4562-a6a4-efd113de02d7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590635f-48c9-b8e7-4f83bc9b56e9','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590663e-4776-8bd5-0f24e66695de','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955906883-4898-abf8-cbcaf1f66d0b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955907226-43d7-8f73-8b5858eab1d6','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590745a-4375-8ec0-b75081c20134','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955907797-4c60-b95e-b78eddbbd98c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:50','1696920417933b-46ad-90ca-67c614c75608','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955908047-46d0-9480-4ad8c0b2f04f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590826a-452c-86b2-af5c826f4e62','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590854f-4236-a97d-52c6f3ce5ea0','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955908777-47ec-bde2-2225448dde74','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955909088-4dfc-ba85-aae87d3f0afe','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590935f-49b8-bc9a-4366cc40c1a6','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('1696995590959a-4f8e-a363-a804506f8b22','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955909866-4151-8fb1-a10117a66a2c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:50','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:50','postman_update'),('16969955910166-4521-a7b4-84ca5926f117','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:51','1696920433515c-4ff8-8ee2-93d4a84ccf68','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955910422-41f6-8c51-6e8b89dd4055','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('1696995591076d-4fac-bf8e-45b485e72558','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955911100-4532-ab41-62b06540ed4f','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955911626-46ce-8e6b-688eddf7eb3b','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955911857-4348-8804-1781cd30107d','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955912090-4afd-a43a-0d70b5f3677c','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955912344-4a68-b744-36d24aad71fa','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('1696995591252f-4b45-b7ac-603baa349276','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955912758-455f-8f53-2449b6e34f34','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:51','16969204267977-47c0-8850-e9cb40000847','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955913025-493e-b4ab-e280f20e15c7','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969108947186-4f33-a52a-ac422b682082',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955913258-46aa-9db2-0a7130d90d77','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920641941d-479d-b458-e478686196cd',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955913529-49e0-ab81-e5fb01f63d52','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969299537711-4e2a-b815-9d2958f55fca',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955913693-4e85-8ce4-4ccb68e7c940','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206504911-4569-b9cb-682ba4745c32',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955914037-46d4-8968-4d23dd387344','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969206701494-45f6-8c06-1612c6b9ca25',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955914282-409e-8527-1ec9c4857bd4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696920664397a-4be0-94c3-6dbce8a89913',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('16969955914554-49b5-b190-bf981d449d6a','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300169943-4588-873c-625688d386ce',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('1696995591483e-4e21-a2d7-aa52d7fcaa50','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'16969300242149-4325-9d4d-0cd0c763ba24',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update'),('1696995591502d-4f01-bc69-5b1036a23ca4','1696995583894b-4e44-a4e9-fe6bfa45661b',0,'1696930042282e-47ee-9fe3-d48951479cce',49000,'2023-10-11 03:39:51','16969204805543-471d-80ac-33b3fca0b6fc','16969206988510-49b6-9328-c64a58f24bda','2023-10-11 03:39:51','postman_update');
/*!40000 ALTER TABLE `charge_info_set` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-19 15:41:25
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db04
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_info` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `room_name` varchar(255) NOT NULL,
  `site_id` varchar(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_info`
--

LOCK TABLES `room_info` WRITE;
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
INSERT INTO `room_info` VALUES ('1697180081111f-4b99-bc5b-f79097b967dc',0,'2023-10-13 06:54:41','A','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 06:54:41','postman_update'),('16971852377301-4dd8-bdaf-8f181400a686',0,'2023-10-13 08:20:37','B','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 08:20:37','postman_update'),('16971853067378-4e1a-b9ae-ac315fa349ba',0,'2023-10-13 08:21:46','B','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 08:21:46','postman_update'),('1697185319029f-4386-a9d0-fa0c9cd48ac4',0,'2023-10-13 08:21:59','B','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 08:21:59','postman_update'),('1697185354325b-4234-9e97-df6e4709c8de',0,'2023-10-13 08:22:34','C','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 08:22:34','postman_update'),('16971853593000-426b-96ef-6d8bfe93cf7b',0,'2023-10-13 08:22:39','D','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-13 08:22:39','postman_update');
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_seat`
--

DROP TABLE IF EXISTS `room_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_seat` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `room_id` varchar(255) NOT NULL,
  `seat_id` varchar(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  `usable_status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_seat`
--

LOCK TABLES `room_seat` WRITE;
/*!40000 ALTER TABLE `room_seat` DISABLE KEYS */;
INSERT INTO `room_seat` VALUES ('16974426860943-47f2-9bc1-cbc5464e5d12',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426840728-4f82-bb51-fb22f67d9ca0','2023-10-16 07:51:26','dummy admin',1),('1697442686119e-4f40-ab9e-1cc625e8d802',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426841002-429b-8758-8aca84f2915f','2023-10-16 07:51:26','dummy admin',1),('1697442686139f-4443-be2b-eb3f33d5cd9d',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426841391-4596-9574-9aaabd2e8800','2023-10-16 07:51:26','dummy admin',1),('1697442686156e-49c3-8f88-c46e85f7cd42',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426842008-4ab7-b6f9-6e899324c122','2023-10-16 07:51:26','dummy admin',1),('1697442686181b-421f-b4c9-6b558e2beca4',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684228c-4355-a2e5-3ffcd3412170','2023-10-16 07:51:26','dummy admin',1),('16974426862002-45da-9a6f-5a5cbf9f45c4',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684257d-4f9e-b686-62ce2bc9f215','2023-10-16 07:51:26','dummy admin',1),('16974426862202-41b8-ba38-f9fff886a082',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684286e-49da-87bc-daadc8d9aaa7','2023-10-16 07:51:26','dummy admin',1),('16974426862477-4969-bee1-13a63c30a2a9',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426843179-4d9d-a4b7-b23227b165b7','2023-10-16 07:51:26','dummy admin',1),('16974426862728-4759-b725-db2649f31f2e',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426843413-40e9-9b52-eaac1931cd40','2023-10-16 07:51:26','dummy admin',1),('1697442686299a-4f3e-ac12-16e716f201ef',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426843667-418c-a065-748dcc6296a2','2023-10-16 07:51:26','dummy admin',1),('16974426863228-4033-8d9c-96aab18d2008',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684392a-4d2f-8af1-1f7731660079','2023-10-16 07:51:26','dummy admin',1),('1697442686347e-45b6-a146-ac7705f1393f',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426844167-48ea-acaa-a2309d372d92','2023-10-16 07:51:26','dummy admin',1),('16974426863762-4a97-abf4-99b440fd85b3',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684458f-41a7-878e-f9abcad5559d','2023-10-16 07:51:26','dummy admin',1),('1697442686401a-463e-9f0d-211ceeed0e69',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426844783-41f5-90a4-2a91c743ad6b','2023-10-16 07:51:26','dummy admin',1),('1697442686420b-449f-bc4b-1a3704ecb516',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684509c-4d06-bde2-3137a94ffa28','2023-10-16 07:51:26','dummy admin',1),('16974426864426-4414-8cf2-62314fe0d631',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426845338-4ea5-9386-b1124b8288e9','2023-10-16 07:51:26','dummy admin',1),('1697442686465a-4243-ae53-704d5e06c3f0',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684551b-4bc3-8b54-3d3d0e989f91','2023-10-16 07:51:26','dummy admin',1),('16974426864872-41a6-a706-e29fa2e45974',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684572e-40e3-a9b1-5416395e2e2d','2023-10-16 07:51:26','dummy admin',1),('16974426865066-47b5-aec5-5920eb1ebbe1',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684595f-49ba-b0a1-03bec93b67ff','2023-10-16 07:51:26','dummy admin',1),('16974426865279-4455-8019-2a1204583715',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426846174-4362-bde5-412524581bdc','2023-10-16 07:51:26','dummy admin',1),('16974426865501-4316-a7e7-be06ffb1eac4',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684634a-4a25-93c0-ae4977cc2adf','2023-10-16 07:51:26','dummy admin',1),('16974426865737-4858-9e31-f51392ae8b0e',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684659f-4c5b-8e16-7790b297e10f','2023-10-16 07:51:26','dummy admin',1),('1697442686600a-4d56-a439-50f069cf5cdd',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426846803-4122-b26a-c0209e0119a2','2023-10-16 07:51:26','dummy admin',1),('16974426866249-4f09-8634-0d5cd438b635',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684706c-439e-9bb9-3425af046226','2023-10-16 07:51:26','dummy admin',1),('16974426866469-46ee-a5e4-d59f0f87997f',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684730b-415b-b8d0-b07cbd5e6c5e','2023-10-16 07:51:26','dummy admin',1),('16974426866740-4538-a161-25bbdca336ca',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426847552-4718-a5aa-1a7e7c328e46','2023-10-16 07:51:26','dummy admin',1),('1697442686699c-421b-98fb-9ebd05a004e2',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684786b-49d4-87c8-881762180e71','2023-10-16 07:51:26','dummy admin',1),('16974426867217-442f-80ab-c673e7085851',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426848171-43ef-9a2a-27ee6d3b615e','2023-10-16 07:51:26','dummy admin',1),('1697442686746b-4b29-831a-a41271598755',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684842a-431e-90d9-809f09798332','2023-10-16 07:51:26','dummy admin',1),('16974426867697-4b2c-b41a-c246199ed7c9',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684871f-4b64-b4b7-c5ef2b11ff0a','2023-10-16 07:51:26','dummy admin',1),('16974426867934-4509-8fcd-ee4f390c26cf',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684899c-4837-a5e7-4a308a2c1727','2023-10-16 07:51:26','dummy admin',1),('1697442686817b-4a04-9742-42eda6ec13a6',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684924f-425f-9c6b-761b8ea8000b','2023-10-16 07:51:26','dummy admin',1),('16974426868442-4a3e-a66b-a3337af985cc',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426849491-43ec-870f-879fe9720aa8','2023-10-16 07:51:26','dummy admin',1),('16974426868656-4e48-969b-ce10b8610c5c',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442684977c-4455-8733-d95fa8a84050','2023-10-16 07:51:26','dummy admin',1),('16974426868866-43e8-80d6-7fe649aa5e27',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426849985-4c78-ab79-1b5c5fcbeb28','2023-10-16 07:51:26','dummy admin',1),('16974426869055-48fd-84b4-0ae8b30c41be',1,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426850273-4d8b-a5fe-81d9867ff7b8','2023-10-16 07:51:26','dummy admin',1),('16974426869277-48f1-a49d-9235d2a90e54',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442685053a-4bdd-996e-45ff7d7a6fd8','2023-10-16 07:51:26','dummy admin',1),('16974426869440-4846-827b-4148c6e9f629',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426850790-48f5-a949-6b6860aa16ba','2023-10-16 07:51:26','dummy admin',1),('16974426869665-4ca7-af4b-d35d91e0bbb4',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','1697442685109e-406b-b7b6-0431ad34cc46','2023-10-16 07:51:26','dummy admin',1),('1697442686992f-4228-b965-e9f39fb142d4',0,'2023-10-16 07:51:26','1697180081111f-4b99-bc5b-f79097b967dc','16974426851296-47fc-8fd2-4995bb0e0640','2023-10-16 07:51:26','dummy admin',1),('16974426870740-41c4-8823-d9498a2704b1',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426851528-4dfa-8a1e-c245266ba931','2023-10-16 07:51:27','dummy admin',1),('16974426870974-485e-a42a-63dc0518788e',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685176e-4d36-b4a0-af61c61ed78c','2023-10-16 07:51:27','dummy admin',1),('16974426871165-450e-ae1c-dfc7b3cb959e',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685207c-4e69-9704-cc3d470e6a7a','2023-10-16 07:51:27','dummy admin',1),('16974426871340-47e8-9009-88ac8681a119',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685229e-43cb-8483-db3901e16d70','2023-10-16 07:51:27','dummy admin',1),('16974426871581-436f-b002-f6879bad9890',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426852487-42e0-9a54-37b7cddae0b4','2023-10-16 07:51:27','dummy admin',1),('16974426871815-48eb-8877-d9bffd1d16e9',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426852666-4177-8bb1-dd53fc7803f9','2023-10-16 07:51:27','dummy admin',1),('16974426872010-4bb9-b248-d4dc2e2c1610',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426852867-4fa8-8cce-3c669df151e5','2023-10-16 07:51:27','dummy admin',1),('16974426872265-4dde-b65c-6abbc4e7ecc3',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426853094-4215-8e9e-15110a998305','2023-10-16 07:51:27','dummy admin',1),('1697442687248d-4732-aa9c-99a4264b2163',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685327c-41c1-af39-8623eded8ce0','2023-10-16 07:51:27','dummy admin',1),('1697442687266d-4b68-9420-c52eef7a225e',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426853518-413b-99d4-7f6948ed6bcf','2023-10-16 07:51:27','dummy admin',1),('16974426872885-4cf1-9573-11a4319859df',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426853735-477e-92e0-b9279519d0c6','2023-10-16 07:51:27','dummy admin',1),('1697442687310c-408c-8fda-b0430fddf39d',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685398f-405f-95fb-3cec54e5acf6','2023-10-16 07:51:27','dummy admin',1),('16974426873295-4a44-862d-9430fb00dde3',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685419a-4298-8efb-4d8243ebf35f','2023-10-16 07:51:27','dummy admin',1),('16974426873509-4cdf-8751-b3a50c089ec4',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426854426-46f3-a969-3e2c36b628f8','2023-10-16 07:51:27','dummy admin',1),('1697442687375e-4dcf-b4bc-11b13694b356',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426854649-4621-b472-016da9924a74','2023-10-16 07:51:27','dummy admin',1),('16974426873988-497c-9137-cfd812b2f025',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426854848-4e66-9f49-30b1cc173e31','2023-10-16 07:51:27','dummy admin',1),('1697442687420d-45e8-af7c-cc1dc4158978',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426855087-414a-9218-129b5f7a01ed','2023-10-16 07:51:27','dummy admin',1),('1697442687441a-4751-b933-0bf4b82febbb',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426855258-4ba0-acb1-e92cf2271b47','2023-10-16 07:51:27','dummy admin',1),('16974426874652-4759-b532-3955da03ede2',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426855457-4f11-a5d5-b7d23be393fd','2023-10-16 07:51:27','dummy admin',1),('1697442687486e-4817-a24b-84dc742a2c51',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685567f-440f-adff-a3a93417b468','2023-10-16 07:51:27','dummy admin',1),('16974426875091-4568-93f6-e6335d237e9b',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426855851-4048-a4c0-507f4ad0b754','2023-10-16 07:51:27','dummy admin',1),('1697442687532d-4f23-939f-501df64da2f3',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426856102-4fa7-a7e6-2c25afb2974c','2023-10-16 07:51:27','dummy admin',1),('16974426875530-449d-8b7b-0ae9cb63fdf4',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426856302-4191-b193-d5f69b986abf','2023-10-16 07:51:27','dummy admin',1),('16974426875753-4b98-a37a-059b74d7ae22',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426856458-42fd-b6e1-378852f33b0a','2023-10-16 07:51:27','dummy admin',1),('1697442687594b-4852-8a22-c0a962bce171',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426856680-4a57-ac51-57b7cc035a73','2023-10-16 07:51:27','dummy admin',1),('16974426876148-4c34-98ee-7d46b3647f06',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685693d-4a62-be78-c9c78b78e53b','2023-10-16 07:51:27','dummy admin',1),('16974426876350-4cee-acbd-b43c45ab7109',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685712a-4ec6-ad16-ba3dca8d37d1','2023-10-16 07:51:27','dummy admin',1),('16974426876603-4827-bb0d-f74c7059b32d',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426857348-40ab-8f37-aa1858cb0e3b','2023-10-16 07:51:27','dummy admin',1),('16974426876810-4d8f-9d0a-0cca4367d7da',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426857592-464c-8ee8-1495cbd48cc7','2023-10-16 07:51:27','dummy admin',1),('16974426877118-4b12-9ca7-ab6f8a357df8',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685777c-4b94-99d3-680adc712e85','2023-10-16 07:51:27','dummy admin',1),('16974426877482-44ef-b2bc-dd41f7f83b95',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685798a-4cd0-b550-7aef4ee31fbf','2023-10-16 07:51:27','dummy admin',1),('16974426877819-40b6-b931-6f1880779994',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426858162-4b52-8394-a53510050077','2023-10-16 07:51:27','dummy admin',1),('16974426878188-497a-8efe-66b597cfbde8',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426858350-4417-bd65-e1f5938cb088','2023-10-16 07:51:27','dummy admin',1),('1697442687866a-4f8a-b3d5-7f9cb67d9f40',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685859e-403b-85f2-4db09068dc6c','2023-10-16 07:51:27','dummy admin',1),('16974426878945-4878-ae55-f281773dfb2c',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685880f-4b62-affd-312307b2ac5b','2023-10-16 07:51:27','dummy admin',1),('1697442687916c-4d1e-8444-5a82c2ddb631',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426858997-4131-a66e-3a54b3a3eb62','2023-10-16 07:51:27','dummy admin',1),('16974426879451-4b74-af1b-fb07f26f6d7a',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685922a-4f46-926f-d6fb6d82488b','2023-10-16 07:51:27','dummy admin',1),('1697442687967a-4279-a3b9-2cbd9d6b50fc',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','1697442685947e-4eec-9566-9deb05a19f7f','2023-10-16 07:51:27','dummy admin',1),('16974426879839-4c76-a050-92304655debb',0,'2023-10-16 07:51:27','1697180081111f-4b99-bc5b-f79097b967dc','16974426859672-4a67-88b2-0d439702e3fb','2023-10-16 07:51:27','dummy admin',1),('16974426880072-4b69-bc09-2d6b53497622',0,'2023-10-16 07:51:28','1697180081111f-4b99-bc5b-f79097b967dc','16974426859896-48a7-a85a-19793c61b829','2023-10-16 07:51:28','dummy admin',1),('16974426880287-463a-90ef-02a3dd28d3e4',0,'2023-10-16 07:51:28','1697180081111f-4b99-bc5b-f79097b967dc','16974426860072-44e9-8d71-3e7f2f2269a1','2023-10-16 07:51:28','dummy admin',1),('16974426880444-4b9e-a131-011bb8fb7e21',0,'2023-10-16 07:51:28','1697180081111f-4b99-bc5b-f79097b967dc','16974426860296-4f6f-96a0-68e7c02a0079','2023-10-16 07:51:28','dummy admin',1),('1697442688066f-45a5-9df2-4d306e6afbe8',0,'2023-10-16 07:51:28','1697180081111f-4b99-bc5b-f79097b967dc','16974426860499-47cd-98a1-0197e138ea9d','2023-10-16 07:51:28','dummy admin',1),('1697442688084b-49bd-be8b-444957683622',0,'2023-10-16 07:51:28','1697180081111f-4b99-bc5b-f79097b967dc','1697442686072a-401b-9008-24289f99cabb','2023-10-16 07:51:28','dummy admin',1);
/*!40000 ALTER TABLE `room_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_gradle`
--

DROP TABLE IF EXISTS `seat_gradle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_gradle` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `member_visible_flg` int NOT NULL DEFAULT '1',
  `regist_time` datetime(6) DEFAULT NULL,
  `seat_grade_name` varchar(255) NOT NULL,
  `sort_no` int NOT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_gradle`
--

LOCK TABLES `seat_gradle` WRITE;
/*!40000 ALTER TABLE `seat_gradle` DISABLE KEYS */;
INSERT INTO `seat_gradle` VALUES ('1697438390331c-4569-ad77-4c42403df99e',0,1,NULL,'Couple Seat',60,NULL,'postman_update'),('16974384299331-4609-8151-a654ad30c88b',0,1,NULL,'Premium Seat',40,NULL,'postman_update'),('16974384677231-4429-b9a0-38694408934d',0,1,NULL,'Premium Plus Seat',50,NULL,'postman_update'),('1697438478695e-46de-a121-f9569c8d5098',0,1,NULL,'Basic Seat',10,NULL,'postman_update');
/*!40000 ALTER TABLE `seat_gradle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_master`
--

DROP TABLE IF EXISTS `seat_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_master` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `seat_colume` int NOT NULL,
  `seat_gradle_id` varchar(255) DEFAULT NULL,
  `seat_row` int NOT NULL,
  `seat_size` int NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_master`
--

LOCK TABLES `seat_master` WRITE;
/*!40000 ALTER TABLE `seat_master` DISABLE KEYS */;
INSERT INTO `seat_master` VALUES ('16974426840728-4f82-bb51-fb22f67d9ca0',0,'2023-10-16 07:51:24',1,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426841002-429b-8758-8aca84f2915f',0,'2023-10-16 07:51:24',2,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426841391-4596-9574-9aaabd2e8800',0,'2023-10-16 07:51:24',3,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426842008-4ab7-b6f9-6e899324c122',0,'2023-10-16 07:51:24',4,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('1697442684228c-4355-a2e5-3ffcd3412170',0,'2023-10-16 07:51:24',5,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('1697442684257d-4f9e-b686-62ce2bc9f215',0,'2023-10-16 07:51:24',6,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('1697442684286e-49da-87bc-daadc8d9aaa7',0,'2023-10-16 07:51:24',7,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426843179-4d9d-a4b7-b23227b165b7',0,'2023-10-16 07:51:24',8,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426843413-40e9-9b52-eaac1931cd40',0,'2023-10-16 07:51:24',9,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426843667-418c-a065-748dcc6296a2',0,'2023-10-16 07:51:24',10,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('1697442684392a-4d2f-8af1-1f7731660079',0,'2023-10-16 07:51:24',11,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('16974426844167-48ea-acaa-a2309d372d92',0,'2023-10-16 07:51:24',12,'1697438478695e-46de-a121-f9569c8d5098',1,1,'2023-10-16 07:51:24','postman_update'),('1697442684458f-41a7-878e-f9abcad5559d',0,'2023-10-16 07:51:24',1,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('16974426844783-41f5-90a4-2a91c743ad6b',0,'2023-10-16 07:51:24',2,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684509c-4d06-bde2-3137a94ffa28',0,'2023-10-16 07:51:24',3,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('16974426845338-4ea5-9386-b1124b8288e9',0,'2023-10-16 07:51:24',4,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684551b-4bc3-8b54-3d3d0e989f91',0,'2023-10-16 07:51:24',5,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684572e-40e3-a9b1-5416395e2e2d',0,'2023-10-16 07:51:24',6,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684595f-49ba-b0a1-03bec93b67ff',0,'2023-10-16 07:51:24',7,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('16974426846174-4362-bde5-412524581bdc',0,'2023-10-16 07:51:24',8,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684634a-4a25-93c0-ae4977cc2adf',0,'2023-10-16 07:51:24',9,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684659f-4c5b-8e16-7790b297e10f',0,'2023-10-16 07:51:24',10,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('16974426846803-4122-b26a-c0209e0119a2',0,'2023-10-16 07:51:24',11,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684706c-439e-9bb9-3425af046226',0,'2023-10-16 07:51:24',12,'1697438478695e-46de-a121-f9569c8d5098',2,1,'2023-10-16 07:51:24','postman_update'),('1697442684730b-415b-b8d0-b07cbd5e6c5e',0,'2023-10-16 07:51:24',1,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('16974426847552-4718-a5aa-1a7e7c328e46',0,'2023-10-16 07:51:24',2,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684786b-49d4-87c8-881762180e71',0,'2023-10-16 07:51:24',3,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('16974426848171-43ef-9a2a-27ee6d3b615e',0,'2023-10-16 07:51:24',4,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684842a-431e-90d9-809f09798332',0,'2023-10-16 07:51:24',5,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684871f-4b64-b4b7-c5ef2b11ff0a',0,'2023-10-16 07:51:24',6,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684899c-4837-a5e7-4a308a2c1727',0,'2023-10-16 07:51:24',7,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684924f-425f-9c6b-761b8ea8000b',0,'2023-10-16 07:51:24',8,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('16974426849491-43ec-870f-879fe9720aa8',0,'2023-10-16 07:51:24',9,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('1697442684977c-4455-8733-d95fa8a84050',0,'2023-10-16 07:51:24',10,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:24','postman_update'),('16974426849985-4c78-ab79-1b5c5fcbeb28',0,'2023-10-16 07:51:25',11,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:25','postman_update'),('16974426850273-4d8b-a5fe-81d9867ff7b8',0,'2023-10-16 07:51:25',12,'1697438478695e-46de-a121-f9569c8d5098',3,1,'2023-10-16 07:51:25','postman_update'),('1697442685053a-4bdd-996e-45ff7d7a6fd8',0,'2023-10-16 07:51:25',1,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426850790-48f5-a949-6b6860aa16ba',0,'2023-10-16 07:51:25',2,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('1697442685109e-406b-b7b6-0431ad34cc46',0,'2023-10-16 07:51:25',3,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426851296-47fc-8fd2-4995bb0e0640',0,'2023-10-16 07:51:25',4,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426851528-4dfa-8a1e-c245266ba931',0,'2023-10-16 07:51:25',5,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('1697442685176e-4d36-b4a0-af61c61ed78c',0,'2023-10-16 07:51:25',6,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('1697442685207c-4e69-9704-cc3d470e6a7a',0,'2023-10-16 07:51:25',7,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('1697442685229e-43cb-8483-db3901e16d70',0,'2023-10-16 07:51:25',8,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426852487-42e0-9a54-37b7cddae0b4',0,'2023-10-16 07:51:25',9,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426852666-4177-8bb1-dd53fc7803f9',0,'2023-10-16 07:51:25',10,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426852867-4fa8-8cce-3c669df151e5',0,'2023-10-16 07:51:25',11,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('16974426853094-4215-8e9e-15110a998305',0,'2023-10-16 07:51:25',12,'1697438478695e-46de-a121-f9569c8d5098',4,1,'2023-10-16 07:51:25','postman_update'),('1697442685327c-41c1-af39-8623eded8ce0',0,'2023-10-16 07:51:25',1,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426853518-413b-99d4-7f6948ed6bcf',0,'2023-10-16 07:51:25',2,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426853735-477e-92e0-b9279519d0c6',0,'2023-10-16 07:51:25',3,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('1697442685398f-405f-95fb-3cec54e5acf6',0,'2023-10-16 07:51:25',4,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('1697442685419a-4298-8efb-4d8243ebf35f',0,'2023-10-16 07:51:25',5,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426854426-46f3-a969-3e2c36b628f8',0,'2023-10-16 07:51:25',6,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426854649-4621-b472-016da9924a74',0,'2023-10-16 07:51:25',7,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426854848-4e66-9f49-30b1cc173e31',0,'2023-10-16 07:51:25',8,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426855087-414a-9218-129b5f7a01ed',0,'2023-10-16 07:51:25',9,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426855258-4ba0-acb1-e92cf2271b47',0,'2023-10-16 07:51:25',10,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426855457-4f11-a5d5-b7d23be393fd',0,'2023-10-16 07:51:25',11,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('1697442685567f-440f-adff-a3a93417b468',0,'2023-10-16 07:51:25',12,'1697438478695e-46de-a121-f9569c8d5098',5,1,'2023-10-16 07:51:25','postman_update'),('16974426855851-4048-a4c0-507f4ad0b754',0,'2023-10-16 07:51:25',1,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426856102-4fa7-a7e6-2c25afb2974c',0,'2023-10-16 07:51:25',2,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426856302-4191-b193-d5f69b986abf',0,'2023-10-16 07:51:25',3,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426856458-42fd-b6e1-378852f33b0a',0,'2023-10-16 07:51:25',4,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426856680-4a57-ac51-57b7cc035a73',0,'2023-10-16 07:51:25',5,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('1697442685693d-4a62-be78-c9c78b78e53b',0,'2023-10-16 07:51:25',6,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('1697442685712a-4ec6-ad16-ba3dca8d37d1',0,'2023-10-16 07:51:25',7,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426857348-40ab-8f37-aa1858cb0e3b',0,'2023-10-16 07:51:25',8,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426857592-464c-8ee8-1495cbd48cc7',0,'2023-10-16 07:51:25',9,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('1697442685777c-4b94-99d3-680adc712e85',0,'2023-10-16 07:51:25',10,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('1697442685798a-4cd0-b550-7aef4ee31fbf',0,'2023-10-16 07:51:25',11,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426858162-4b52-8394-a53510050077',0,'2023-10-16 07:51:25',12,'1697438478695e-46de-a121-f9569c8d5098',6,1,'2023-10-16 07:51:25','postman_update'),('16974426858350-4417-bd65-e1f5938cb088',0,'2023-10-16 07:51:25',1,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('1697442685859e-403b-85f2-4db09068dc6c',0,'2023-10-16 07:51:25',2,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('1697442685880f-4b62-affd-312307b2ac5b',0,'2023-10-16 07:51:25',3,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('16974426858997-4131-a66e-3a54b3a3eb62',0,'2023-10-16 07:51:25',4,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('1697442685922a-4f46-926f-d6fb6d82488b',0,'2023-10-16 07:51:25',5,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('1697442685947e-4eec-9566-9deb05a19f7f',0,'2023-10-16 07:51:25',6,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('16974426859672-4a67-88b2-0d439702e3fb',0,'2023-10-16 07:51:25',7,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('16974426859896-48a7-a85a-19793c61b829',0,'2023-10-16 07:51:25',8,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:25','postman_update'),('16974426860072-44e9-8d71-3e7f2f2269a1',0,'2023-10-16 07:51:26',9,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:26','postman_update'),('16974426860296-4f6f-96a0-68e7c02a0079',0,'2023-10-16 07:51:26',10,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:26','postman_update'),('16974426860499-47cd-98a1-0197e138ea9d',0,'2023-10-16 07:51:26',11,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:26','postman_update'),('1697442686072a-401b-9008-24289f99cabb',0,'2023-10-16 07:51:26',12,'1697438478695e-46de-a121-f9569c8d5098',7,1,'2023-10-16 07:51:26','postman_update');
/*!40000 ALTER TABLE `seat_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_time`
--

DROP TABLE IF EXISTS `show_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_time` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `end_time` datetime(6) NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `room_id` varchar(255) NOT NULL,
  `site_id` varchar(255) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_time`
--

LOCK TABLES `show_time` WRITE;
/*!40000 ALTER TABLE `show_time` DISABLE KEYS */;
INSERT INTO `show_time` VALUES ('16976870878912-4449-bdef-5213ff019475',0,'2023-10-19 12:44:41.000000','16976866086539-49f5-b4e8-af3174d76023','2023-10-19 03:44:47','1697180081111f-4b99-bc5b-f79097b967dc','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-19 12:44:41.000000','2023-10-19 03:44:47','postman_update'),('1697687177543b-4530-9bcf-915de39be813',0,'2023-10-19 12:46:09.000000','16976866086539-49f5-b4e8-af3174d76023','2023-10-19 03:46:17','1697180081111f-4b99-bc5b-f79097b967dc','16971678522423-4d4e-a18b-5666d8f07d11','2023-10-19 12:46:09.000000','2023-10-19 03:46:17','postman_update');
/*!40000 ALTER TABLE `show_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_gradle`
--

DROP TABLE IF EXISTS `site_gradle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_gradle` (
  `id` varchar(255) NOT NULL,
  `del_flg` int DEFAULT '0',
  `member_visible_flg` int NOT NULL DEFAULT '1',
  `regist_time` datetime(6) DEFAULT NULL,
  `site_grade_name` varchar(255) NOT NULL,
  `sort_no` int NOT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT 'postman_update',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_gradle`
--

LOCK TABLES `site_gradle` WRITE;
/*!40000 ALTER TABLE `site_gradle` DISABLE KEYS */;
INSERT INTO `site_gradle` VALUES ('1696913612645a-403f-bf60-4ebb2265611a',0,1,NULL,'Basic Site',10,NULL,'postman_update'),('16969206988510-49b6-9328-c64a58f24bda',0,1,NULL,'Premium Plus Site',50,NULL,'postman_update'),('16969207105705-4be0-87f8-c300eab48610',0,1,NULL,'Middle Plus Site',30,NULL,'postman_update'),('16969207150595-419e-9b2d-e5536bb4513e',0,1,NULL,'Middle Site',20,NULL,'postman_update'),('1696920725231b-4827-87bc-a6783d9f97bd',0,1,NULL,'Premium Site',40,NULL,'postman_update'),('16976162202725-4348-83af-f0f8c0224e36',0,0,NULL,'Premium Site Test',40,NULL,'postman_update');
/*!40000 ALTER TABLE `site_gradle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_info`
--

DROP TABLE IF EXISTS `site_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_info` (
  `id` varchar(255) NOT NULL,
  `access_info` varchar(255) DEFAULT NULL,
  `del_flg` int DEFAULT '0',
  `information` varchar(255) DEFAULT NULL,
  `localtion` varchar(255) NOT NULL,
  `notice` varchar(255) DEFAULT NULL,
  `regist_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `site_gradle_id` varchar(255) NOT NULL,
  `site_lat_y` double NOT NULL,
  `site_lon_x` double NOT NULL,
  `site_name` varchar(255) NOT NULL,
  `site_zip_no` varchar(255) NOT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_info`
--

LOCK TABLES `site_info` WRITE;
/*!40000 ALTER TABLE `site_info` DISABLE KEYS */;
INSERT INTO `site_info` VALUES ('16971678522423-4d4e-a18b-5666d8f07d11','Access Info',0,'Information','Location','Notice','2023-10-13 03:30:52','1696913612645a-403f-bf60-4ebb2265611a',0,0,'Site Name','Site ZipNo','2023-10-13 03:30:52',NULL);
/*!40000 ALTER TABLE `site_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-19 15:41:25
