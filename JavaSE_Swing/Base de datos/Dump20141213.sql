-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: libreria
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libros` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `AUTOR` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `EDITORIAL` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ISBN` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `PUBLICACION` int(11) DEFAULT NULL,
  `PRECIO` double DEFAULT NULL,
  `DESCRIPCION` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'Libro1','Autor1','Editorial1','1',1,23,'Descripcion de prueba. hgdafkjadhgfiwuygefiweugfiwefgiwegffygewuifygewuiyewfguywefguyewfg'),(2,'Libro2','Autor1','Editorial1','2',1,1.99,'douiwhrfkiuwhfikwuhrfikuwhefikouhewfiuewhfiwuehfiuewhf'),(3,'Libro3','Anónimo','Editorial2','3',1,200,'khdagkjhsagfkjgwdefkjgwefjgwfghewefgwhefghwjefghwjefghwjfgefew'),(4,'Libro4','Autor2','Editorial2','4',2,23,'kdahjfhkdgakjfhgsadjfhgsdjghfsahjdgfhjsdgfhjsdgfhj'),(5,'Libro5','Autor Desocnocido','Editorial 3','5',2,23,'ajdhckjdhflsdjhfljaehfdslkjhfkjdsahkjdf');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-13 20:15:12
