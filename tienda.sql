-- MySQL dump 10.17  Distrib 10.3.22-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	10.3.22-MariaDB-1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_productos` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `cantidad` int(11) DEFAULT 0,
  PRIMARY KEY (`id_productos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'DM2CJ0c47H','descripcion de DM2CJ0c47H',1),(3,'xgg4yLuWlk','descripcion de xgg4yLuWlk',11),(4,'40zAcfV8L9','descripcion de 40zAcfV8L9',6),(5,'jkyfBChXCB','descripcion de jkyfBChXCB',3),(6,'tGy91gn5Fs','descripcion de tGy91gn5Fs',16),(7,'cEYZ9BI3uv','descripcion de cEYZ9BI3uv',11),(8,'MA9LoD0v6D','descripcion de MA9LoD0v6D',13),(9,'xUzqLRhlAe','descripcion de xUzqLRhlAe',8),(10,'9CfVmxpP1G','descripcion de 9CfVmxpP1G',3),(11,'XH7UzHOioV','descripcion de XH7UzHOioV',21),(12,'xSUXsS3cTb','descripcion de xSUXsS3cTb',3),(13,'UcglHkopQz','descripcion de UcglHkopQz',2),(14,'CTS5VMGQsF','descripcion de CTS5VMGQsF',11),(15,'85iNCLPkdO','descripcion de 85iNCLPkdO',10),(16,'AqbkB9gei2','descripcion de AqbkB9gei2',16),(17,'9eAXjuS2Q2','descripcion de 9eAXjuS2Q2',10),(18,'Ytcvszuqu2','descripcion de Ytcvszuqu2',11),(19,'ytSV4EZRDZ','descripcion de ytSV4EZRDZ',13),(20,'rrcii7btRj','descripcion de rrcii7btRj',5),(21,'GQlPEeXRIe','descripcion de GQlPEeXRIe',4),(22,'JMDiiurq7T','descripcion de JMDiiurq7T',2),(23,'QRYFKvBTkS','descripcion de QRYFKvBTkS',15),(24,'xAoXY3rQ9T','descripcion de xAoXY3rQ9T',7),(26,'VE7PZ3HoDN','descripcion de VE7PZ3HoDN',5),(27,'8b5mGkHKXL','descripcion de 8b5mGkHKXL',11),(28,'hy5OPmrPd6','descripcion de hy5OPmrPd6',4),(29,'zRAiDeunfI','descripcion de zRAiDeunfI',1),(30,'l9rToNepI0','descripcion de l9rToNepI0',1),(31,'RJbGDTvEQb','descripcion de RJbGDTvEQb',3),(32,'Ca2SIDJHs3','descripcion de Ca2SIDJHs3',5),(33,'pbCqULNjhd','descripcion de pbCqULNjhd',2),(34,'mKQd6BGGN3','descripcion de mKQd6BGGN3',1),(35,'68Uy3a4IIe','descripcion de 68Uy3a4IIe',5),(36,'bpdjNFY9h4','descripcion de bpdjNFY9h4',4),(39,'BDLoPTcJb1','descripcion de BDLoPTcJb1',5),(40,'O5vvHV5Lnm','descripcion de O5vvHV5Lnm',12),(41,'cex0vu3QYH','descripcion de cex0vu3QYH',5),(43,'NSXl4ldyeD','descripcion de NSXl4ldyeD',13),(44,'kPhToVW68J','descripcion de kPhToVW68J',1),(45,'7Ue4aiTzqb','descripcion de 7Ue4aiTzqb',9),(46,'95ULr766GY','descripcion de 95ULr766GY',1),(47,'xzdt3bz1uY','descripcion de xzdt3bz1uY',10),(48,'CimRQ4l4UT','descripcion de CimRQ4l4UT',1),(49,'KiNe2tF8FG','descripcion de KiNe2tF8FG',14),(50,'U5vNmLKnXE','descripcion de U5vNmLKnXE',9),(51,'36aKLXzGGg','descripcion de 36aKLXzGGg',12),(52,'6rUkeWh4fj','descripcion de 6rUkeWh4fj',12),(53,'S91yykuqyh','descripcion de S91yykuqyh',19),(54,'RknI0YEh7Q','descripcion de RknI0YEh7Q',2),(55,'gvXv47bc1j','descripcion de gvXv47bc1j',5),(56,'uvF91JHsjC','descripcion de uvF91JHsjC',0),(57,'dMr7n26Dek','descripcion de dMr7n26Dek',1),(58,'R04JIhiR9L','descripcion de R04JIhiR9L',6),(59,'sQSLjQyZQt','descripcion de sQSLjQyZQt',1),(60,'t42jm1Lvl1','descripcion de t42jm1Lvl1',10),(61,'7qSdkL6LRZ','descripcion de 7qSdkL6LRZ',0),(62,'PgYI14ZLZ4','descripcion de PgYI14ZLZ4',1),(63,'fE2GKDoVlb','descripcion de fE2GKDoVlb',6),(64,'byPiOJtXoB','descripcion de byPiOJtXoB',8),(65,'MyecxG6Guo','descripcion de MyecxG6Guo',3),(66,'DLjvCFnfpG','descripcion de DLjvCFnfpG',1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-19 12:20:09
