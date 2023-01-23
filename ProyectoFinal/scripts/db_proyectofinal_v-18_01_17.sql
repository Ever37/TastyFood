-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: proyecto_final
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

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
-- Table structure for table `categoria_de_producto`
--

DROP TABLE IF EXISTS `categoria_de_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_de_producto` (
  `id_categoria` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_de_producto`
--

LOCK TABLES `categoria_de_producto` WRITE;
/*!40000 ALTER TABLE `categoria_de_producto` DISABLE KEYS */;
INSERT INTO `categoria_de_producto` VALUES (1,'Fast food',NULL),(2,'Helados',NULL),(3,'Viandas',NULL),(4,'Pizzas',NULL),(5,'Bebidas',NULL),(6,'Gourmet',NULL),(7,'Otros',NULL);
/*!40000 ALTER TABLE `categoria_de_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercio`
--

DROP TABLE IF EXISTS `comercio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comercio` (
  `id_comercio` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `direccion` varchar(70) NOT NULL,
  `nro` varchar(45) NOT NULL,
  `piso` varchar(45) DEFAULT NULL,
  `depto` varchar(45) DEFAULT NULL,
  `cuit` varchar(45) DEFAULT NULL,
  `realiza_envios` int(11) NOT NULL,
  `tiempo_envio` varchar(45) DEFAULT NULL,
  `costo_envio` double DEFAULT NULL,
  `fecha_alta` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `fecha_baja` timestamp(6) NULL DEFAULT NULL,
  `horario` bigint(11) DEFAULT NULL,
  `usuario` bigint(11) NOT NULL,
  `valoracion_total` double NOT NULL DEFAULT '0',
  `estado` varchar(1) NOT NULL,
  PRIMARY KEY (`id_comercio`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`),
  KEY `fk_comercio_horario_idx` (`horario`),
  KEY `fk_comercio_usuario_idx` (`usuario`),
  CONSTRAINT `fk_comercio_horario` FOREIGN KEY (`horario`) REFERENCES `horario` (`id_horario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comercio_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercio`
--

LOCK TABLES `comercio` WRITE;
/*!40000 ALTER TABLE `comercio` DISABLE KEYS */;
INSERT INTO `comercio` VALUES (1,'Don Corleone','España ','1111',NULL,NULL,NULL,1,'15:00',30,'2017-01-09 03:00:00.000000',NULL,NULL,1,0,''),(2,'Luiggi','Bulevar Villada','1234',NULL,NULL,NULL,1,'30:00',15,'2017-01-09 12:11:32.016008',NULL,NULL,2,0,''),(3,'Cocina Artesanal','Lavalle ','4535',NULL,NULL,NULL,1,'45:00',20,'2017-01-09 12:11:32.016006',NULL,NULL,3,0,''),(4,'Artes gourmet','España ','2313',NULL,NULL,NULL,1,'30:00',30,'2017-01-12 11:37:03.536232',NULL,NULL,4,0,'');
/*!40000 ALTER TABLE `comercio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consulta` (
  `id_consulta` bigint(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `asunto` varchar(30) NOT NULL,
  `mensaje` varchar(150) NOT NULL,
  `fecha_alta` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `estado` varchar(1) DEFAULT NULL,
  `usuario` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id_consulta`),
  KEY `fk_consulta_usuario_idx` (`usuario`),
  CONSTRAINT `fk_consulta_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Estados de una consulta:\nP = Pendiente\nC = Cerrada';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id_grupo` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_grupo`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`),
  UNIQUE KEY `id_grupo_UNIQUE` (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Administrador'),(3,'Cliente'),(2,'Vendedor');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario` (
  `id_horario` bigint(11) NOT NULL AUTO_INCREMENT,
  `franja_horaria_lunes` varchar(45) NOT NULL,
  `franja_horaria_martes` varchar(45) NOT NULL,
  `franja_horaria_miercoles` varchar(45) NOT NULL,
  `franja_horaria_jueves` varchar(45) NOT NULL,
  `franja_horaria_viernes` varchar(45) NOT NULL,
  `franja_horaria_sabado` varchar(45) NOT NULL,
  `franja_horaria_domingo` varchar(45) NOT NULL,
  `comercio` bigint(11) NOT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `fk_horario_comercio_idx` (`comercio`),
  CONSTRAINT `fk_horario_comercio` FOREIGN KEY (`comercio`) REFERENCES `comercio` (`id_comercio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_de_pedido`
--

DROP TABLE IF EXISTS `item_de_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_de_pedido` (
  `nro_item_de_pedido` bigint(11) NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `aclaraciones` varchar(100) DEFAULT NULL,
  `subtotal` double NOT NULL,
  `pedido` bigint(11) NOT NULL,
  `producto` bigint(11) NOT NULL,
  PRIMARY KEY (`nro_item_de_pedido`),
  KEY `fk_id_pedido_idx` (`pedido`),
  KEY `fk_producto_item_idx` (`producto`),
  CONSTRAINT `fk_id_pedido` FOREIGN KEY (`pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_item` FOREIGN KEY (`producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_de_pedido`
--

LOCK TABLES `item_de_pedido` WRITE;
/*!40000 ALTER TABLE `item_de_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_de_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opinion`
--

DROP TABLE IF EXISTS `opinion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opinion` (
  `id_opinion` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(150) NOT NULL,
  `valoracion` int(11) NOT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `comercio` bigint(11) NOT NULL,
  PRIMARY KEY (`id_opinion`),
  KEY `fk_opinion_comercio_idx` (`comercio`),
  CONSTRAINT `fk_opinion_comercio` FOREIGN KEY (`comercio`) REFERENCES `comercio` (`id_comercio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opinion`
--

LOCK TABLES `opinion` WRITE;
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `id_pedido` bigint(11) NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime(6) NOT NULL,
  `direccion` varchar(70) NOT NULL,
  `nro` varchar(45) NOT NULL,
  `piso` varchar(45) DEFAULT NULL,
  `total` double NOT NULL DEFAULT '0',
  `estado` varchar(45) NOT NULL DEFAULT 'Iniciado',
  `observaciones` varchar(50) DEFAULT NULL,
  `con_envio` int(11) NOT NULL,
  `usuario` bigint(11) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `fk_id_usuario_idx` (`usuario`),
  CONSTRAINT `fk_id_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso` (
  `id_permiso` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(20) CHARACTER SET latin1 NOT NULL,
  `descripcionCompleta` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_permiso`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,'RU','Solo lectura'),(2,'CU','Creación y lectura'),(3,'SU','Acceso total'),(4,'SA','Sin acceso');
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio_producto`
--

DROP TABLE IF EXISTS `precio_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precio_producto` (
  `id_precio_producto` bigint(11) NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `fecha_desde` datetime(6) NOT NULL,
  `fecha_hasta` datetime(6) DEFAULT NULL,
  `tipo_precio` int(11) NOT NULL,
  `producto` bigint(11) NOT NULL,
  PRIMARY KEY (`id_precio_producto`),
  KEY `fk_id_producto_idx` (`producto`),
  CONSTRAINT `fk_id_producto` FOREIGN KEY (`producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio_producto`
--

LOCK TABLES `precio_producto` WRITE;
/*!40000 ALTER TABLE `precio_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `precio_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id_producto` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `categoria` bigint(11) NOT NULL,
  `comercio` bigint(11) NOT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`),
  KEY `fk_categoria_producto_idx` (`categoria`),
  KEY `fk_producto_comercio_idx` (`comercio`),
  CONSTRAINT `fk_categoria_producto` FOREIGN KEY (`categoria`) REFERENCES `categoria_de_producto` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_comercio` FOREIGN KEY (`comercio`) REFERENCES `comercio` (`id_comercio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Pizza muzzarela','12 porciones',NULL,4,1),(2,'Pizza cantimpalo','12 porciones',NULL,4,1),(3,'Pizza roquefort','12 porciones',NULL,4,1),(4,'Pizza anchoas','12 porciones',NULL,4,1),(5,'Torpedo común','Para 1 persona',NULL,1,2),(6,'Torpedo super','Para 1 persona',NULL,1,2),(7,'Torpedo familiar','Para 2 personas',NULL,1,2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requerimiento`
--

DROP TABLE IF EXISTS `requerimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requerimiento` (
  `id_requerimiento` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `alias` varchar(10) NOT NULL,
  `workflow` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_requerimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requerimiento`
--

LOCK TABLES `requerimiento` WRITE;
/*!40000 ALTER TABLE `requerimiento` DISABLE KEYS */;
INSERT INTO `requerimiento` VALUES (1,'Administrador','ADMIN',0);
/*!40000 ALTER TABLE `requerimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requerimiento_permiso`
--

DROP TABLE IF EXISTS `requerimiento_permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requerimiento_permiso` (
  `id_requerimiento_permiso` bigint(20) NOT NULL AUTO_INCREMENT,
  `rol` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `id_requerimiento` bigint(20) NOT NULL,
  `id_permiso` int(11) NOT NULL,
  PRIMARY KEY (`id_requerimiento_permiso`),
  KEY `fk_requerimiento-permiso_requerimiento_idx` (`id_requerimiento`),
  KEY `requerimiento_permiso_permiso_fk_idx` (`id_permiso`),
  CONSTRAINT `requerimiento_permiso_permiso_fk` FOREIGN KEY (`id_permiso`) REFERENCES `permiso` (`id_permiso`) ON UPDATE CASCADE,
  CONSTRAINT `requerimiento_permiso_requerimiento_fk` FOREIGN KEY (`id_requerimiento`) REFERENCES `requerimiento` (`id_requerimiento`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requerimiento_permiso`
--

LOCK TABLES `requerimiento_permiso` WRITE;
/*!40000 ALTER TABLE `requerimiento_permiso` DISABLE KEYS */;
INSERT INTO `requerimiento_permiso` VALUES (1,'ADMIN',1,3);
/*!40000 ALTER TABLE `requerimiento_permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requerimiento_permiso_grupo`
--

DROP TABLE IF EXISTS `requerimiento_permiso_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requerimiento_permiso_grupo` (
  `id_requerimiento_permiso_grupo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_grupo` bigint(20) NOT NULL,
  `id_requerimiento_permiso` bigint(20) NOT NULL,
  PRIMARY KEY (`id_requerimiento_permiso_grupo`),
  KEY `requerimiento_permiso_grupo_grupo_fk_idx` (`id_grupo`),
  KEY `requerimiento_permiso_grupo_requerimiento_permiso_fk_idx` (`id_requerimiento_permiso`),
  CONSTRAINT `requerimiento_permiso_grupo_grupo_fk` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id_grupo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `requerimiento_permiso_grupo_requerimiento_permiso_fk` FOREIGN KEY (`id_requerimiento_permiso`) REFERENCES `requerimiento_permiso` (`id_requerimiento_permiso`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requerimiento_permiso_grupo`
--

LOCK TABLES `requerimiento_permiso_grupo` WRITE;
/*!40000 ALTER TABLE `requerimiento_permiso_grupo` DISABLE KEYS */;
INSERT INTO `requerimiento_permiso_grupo` VALUES (1,1,1);
/*!40000 ALTER TABLE `requerimiento_permiso_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sabor`
--

DROP TABLE IF EXISTS `sabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sabor` (
  `id_sabor` bigint(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `producto` bigint(11) NOT NULL,
  PRIMARY KEY (`id_sabor`),
  KEY `fk_id_producto_idx` (`producto`),
  KEY `fk_sabor_producto_idx` (`producto`),
  CONSTRAINT `fk_sabor_producto` FOREIGN KEY (`producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sabor`
--

LOCK TABLES `sabor` WRITE;
/*!40000 ALTER TABLE `sabor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_comercio`
--

DROP TABLE IF EXISTS `telefono_comercio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono_comercio` (
  `id_telefono_comercio` bigint(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(45) NOT NULL,
  `fecha_baja` datetime(6) DEFAULT NULL,
  `comercio` bigint(11) NOT NULL,
  PRIMARY KEY (`id_telefono_comercio`),
  UNIQUE KEY `numero_UNIQUE` (`numero`),
  KEY `fk_telefono_comercio_idx` (`comercio`),
  CONSTRAINT `fk_telefono_comercio` FOREIGN KEY (`comercio`) REFERENCES `comercio` (`id_comercio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_comercio`
--

LOCK TABLES `telefono_comercio` WRITE;
/*!40000 ALTER TABLE `telefono_comercio` DISABLE KEYS */;
INSERT INTO `telefono_comercio` VALUES (1,'3464698661',NULL,1),(2,'123456789','2017-01-12 00:00:00.000000',1);
/*!40000 ALTER TABLE `telefono_comercio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `valoracion` double DEFAULT '0',
  `fecha_baja` date DEFAULT NULL,
  `notificaciones` varchar(1) DEFAULT NULL,
  `id_grupo` bigint(11) DEFAULT NULL,
  `id_comercio` bigint(11) DEFAULT NULL,
  `fecha_alta` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario_UNIQUE` (`nombre_usuario`),
  KEY `fk_usuario_grupo_idx` (`id_grupo`),
  CONSTRAINT `fk_usuario_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id_grupo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Ever.B','12345678','Ever','Blua','34646698661','','everblua@gmail.com',0,NULL,'S',NULL,NULL,'2017-01-11 12:23:37.106037'),(2,'Juan.P','a5s5d4as8d','Juan','Perez','165498498','6478797','juan@gmail.com',0,NULL,'S',NULL,NULL,'2017-01-11 12:23:37.106037'),(3,'Rojas','45ads78','Maria','Rojas','','1123123','rojas@gmail.com',0,NULL,'S',NULL,NULL,'2017-01-11 12:23:37.106037'),(4,'Caiga.Burguer','aljshdlk123','Ruben','Caifa','56412637','971297','caifa@gmail.com',0,NULL,'S',NULL,NULL,'2017-01-12 11:36:27.495410'),(5,'qweqwe','13123123','asdasd','asdasd','2341234','123123','everblua@gmail.com',0,NULL,'S',NULL,NULL,'2017-01-17 14:32:44.534846'),(6,'Admin','admin','Ever','Blua','32156987','654654','everblua@gmail.com',0,NULL,'N',1,NULL,'2017-01-18 13:43:11.152316');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-18 10:44:25
