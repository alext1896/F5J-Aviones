CREATE DATABASE  IF NOT EXISTS `F5J` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `F5J`;
-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: F5J
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `Prueba`
--

DROP TABLE IF EXISTS `Prueba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prueba` (
  `idPrueba` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha` timestamp(4) NULL DEFAULT NULL,
  `limite` timestamp(4) NULL DEFAULT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPrueba`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prueba`
--

LOCK TABLES `Prueba` WRITE;
/*!40000 ALTER TABLE `Prueba` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prueba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `numLicencia` int(11) NOT NULL,
  `nombreUsuario` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`numLicencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-24 11:52:26

DROP TABLE IF EXISTS `Competicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Competicion` (
  `idCompeticion` int(11) NOT NULL AUTO_INCREMENT,
  `idPrueba` int(11) DEFAULT NULL,
  `numLicencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCompeticion`),
  KEY `fk_idPrueba_competicion_idx` (`idPrueba`),
  KEY `fk_idPiloto_Competicion_idx` (`numLicencia`),
  CONSTRAINT `fk_idPiloto_Competicion` FOREIGN KEY (`numLicencia`) REFERENCES `Usuario` (`numLicencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idPrueba_competicion` FOREIGN KEY (`idPrueba`) REFERENCES `Prueba` (`idPrueba`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('1', 'Enero', '2020-01-01', '2020-01-28', 'Madrid');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('2', 'Febrero', '2020-02-01', '2020-02-28', 'Bilbao');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('3', 'Marzo', '2020-03-01', '2020-03-28', 'Madrid');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('4', 'Abril', '2020-04-01', '2020-04-28', 'Barcelona');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('5', 'Mayo', '2020-05-01', '2020-05-28', 'Madrid');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('6', 'Junio', '2020-06-01', '2020-06-28', 'Madrid');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('7', 'Julio', '2020-07-01', '2020-07-28', 'Zaragoza');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('8', 'Agosto', '2020-08-01', '2020-08-28', 'Valencia');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('9', 'Septiembre', '2020-09-01', '2020-09-28', 'Madrid');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('10', 'Octubre', '2020-10-01', '2020-10-28', 'Huesca');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('11', 'Noviembre', '2020-11-01', '2020-11-28', 'Sevilla');

INSERT INTO `F5J`.`Prueba` (`idPrueba`, `nombre`, `fecha`, `limite`, `ciudad`) VALUES ('12', 'Diciembre', '2020-12-01', '2020-12-28', 'A Coruña');

