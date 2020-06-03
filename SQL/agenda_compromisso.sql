-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: agenda
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `compromisso`
--

DROP TABLE IF EXISTS `compromisso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compromisso` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipo_compromisso` int(10) unsigned DEFAULT NULL,
  `dt_data` datetime NOT NULL,
  `forma_pagamento` int(10) unsigned NOT NULL,
  `titulo` varchar(200) CHARACTER SET latin1 NOT NULL,
  `descricao` varchar(1000) CHARACTER SET latin1 NOT NULL,
  `efetuado` tinyint(1) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `prestacoes` smallint(6) DEFAULT NULL,
  `id_sub_tipo_compromisso` int(10) unsigned NOT NULL,
  `comprovantes` blob,
  `extencao_comprovante` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `prestacoes_pagas` int(11) NOT NULL,
  `local_comprovante` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tipo_compromisso` (`id_tipo_compromisso`),
  KEY `FK_sub_tipo_compromisso` (`id_sub_tipo_compromisso`),
  CONSTRAINT `FK_tipo_compromisso` FOREIGN KEY (`id_tipo_compromisso`) REFERENCES `tipo_compromisso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1162 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compromisso`
--
