-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.26 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para con_info
DROP DATABASE IF EXISTS `con_info`;
CREATE DATABASE IF NOT EXISTS `con_info` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `con_info`;


-- Volcando estructura para tabla con_info.cliente_hotel_innova
DROP TABLE IF EXISTS `cliente_hotel_innova`;
CREATE TABLE IF NOT EXISTS `cliente_hotel_innova` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(255) NOT NULL DEFAULT '0',
  `Apellidos` varchar(255) NOT NULL DEFAULT '0',
  `Habitación` varchar(255) NOT NULL DEFAULT '0',
  `num_personas` int(11) NOT NULL DEFAULT '0',
  `Placa_Auto` varchar(10) NOT NULL DEFAULT '0',
  `forma_pago` varchar(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.cliente_hotel_innova: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente_hotel_innova` DISABLE KEYS */;
REPLACE INTO `cliente_hotel_innova` (`id_cliente`, `Nombres`, `Apellidos`, `Habitación`, `num_personas`, `Placa_Auto`, `forma_pago`) VALUES
	(1, 'Fernando', 'Hernández Durán', '510', 4, 'ISB-MEX-91', 'Pay Pal');
/*!40000 ALTER TABLE `cliente_hotel_innova` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.despertador
DROP TABLE IF EXISTS `despertador`;
CREATE TABLE IF NOT EXISTS `despertador` (
  `id_despertador` int(11) NOT NULL AUTO_INCREMENT,
  `for_cliente_id` int(11) NOT NULL,
  `fecha` varchar(150) NOT NULL,
  `hora` varchar(150) NOT NULL,
  `observaciones` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_despertador`),
  KEY `fk_idCliente` (`for_cliente_id`),
  CONSTRAINT `fk_idCliente` FOREIGN KEY (`for_cliente_id`) REFERENCES `user_app` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.despertador: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `despertador` DISABLE KEYS */;
REPLACE INTO `despertador` (`id_despertador`, `for_cliente_id`, `fecha`, `hora`, `observaciones`) VALUES
	(6, 1, '2016/2/15', '06:19am', 'cis'),
	(7, 1, '2016/2/15', '06:51am', 'chj');
/*!40000 ALTER TABLE `despertador` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.reser_gym
DROP TABLE IF EXISTS `reser_gym`;
CREATE TABLE IF NOT EXISTS `reser_gym` (
  `id_reser_gym` int(11) NOT NULL AUTO_INCREMENT,
  `for_cliented` int(11) NOT NULL DEFAULT '0',
  `fecha` varchar(150) NOT NULL DEFAULT '0',
  `hora` varchar(150) NOT NULL DEFAULT '0',
  `observaciones` varchar(150) NOT NULL DEFAULT '0',
  `actividad` varchar(150) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_reser_gym`),
  KEY `fk_cliente_gym` (`for_cliented`),
  CONSTRAINT `fk_cliente_gym` FOREIGN KEY (`for_cliented`) REFERENCES `user_app` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.reser_gym: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `reser_gym` DISABLE KEYS */;
REPLACE INTO `reser_gym` (`id_reser_gym`, `for_cliented`, `fecha`, `hora`, `observaciones`, `actividad`) VALUES
	(1, 1, '2015/13/2', '07:14am', 'pnko', 'Aparatos');
/*!40000 ALTER TABLE `reser_gym` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.rest_reservacion
DROP TABLE IF EXISTS `rest_reservacion`;
CREATE TABLE IF NOT EXISTS `rest_reservacion` (
  `id_reser` int(11) NOT NULL AUTO_INCREMENT,
  `for_id_cliente` int(11) NOT NULL DEFAULT '0',
  `ubicacion_mesa` varchar(150) NOT NULL DEFAULT '0',
  `fecha` varchar(150) NOT NULL DEFAULT '0',
  `hora` varchar(150) NOT NULL DEFAULT '0',
  `total_personas` varchar(200) NOT NULL DEFAULT '0',
  `observaciones` varchar(900) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_reser`),
  KEY `fk_cliente` (`for_id_cliente`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`for_id_cliente`) REFERENCES `user_app` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.rest_reservacion: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `rest_reservacion` DISABLE KEYS */;
REPLACE INTO `rest_reservacion` (`id_reser`, `for_id_cliente`, `ubicacion_mesa`, `fecha`, `hora`, `total_personas`, `observaciones`) VALUES
	(2, 1, 'Area no Fumadores', '2015/13/4', '19:13pm', 'Mesa para 2', 'lola'),
	(4, 1, 'Pene', '02', '1', 'Mesa para 3', 'Primer'),
	(8, 1, 'fas', 'asdfasdf', '123', 'asdfasdf', 'fdsgv'),
	(9, 1, 'Area Niños', '2015/13/16', '03:04am', 'En Barra', 'comes'),
	(12, 1, 'Area Niños', '2016/13/15', '04:18am', 'En Barra', 'b'),
	(13, 1, 'Area Niños', '2015/13/16', '04:18am', 'En Barra', 'hh'),
	(14, 1, 'Area Niños', '2016/6/15', '04:19am', 'En Barra', 'gtt'),
	(19, 1, 'Area Niños', '2015/13/24', '05:43am', 'En Barra', 'mesa para 10'),
	(20, 1, 'Area Niños', '2015/13/24', '04:25am', 'En Barra', 'mesa para 11');
/*!40000 ALTER TABLE `rest_reservacion` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.salida_hotel
DROP TABLE IF EXISTS `salida_hotel`;
CREATE TABLE IF NOT EXISTS `salida_hotel` (
  `id_salida` int(11) NOT NULL AUTO_INCREMENT,
  `for_cliente_salida` int(11) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `hora` varchar(50) NOT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_salida`),
  KEY `fk_cliente_sa` (`for_cliente_salida`),
  CONSTRAINT `fk_cliente_sa` FOREIGN KEY (`for_cliente_salida`) REFERENCES `user_app` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.salida_hotel: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `salida_hotel` DISABLE KEYS */;
REPLACE INTO `salida_hotel` (`id_salida`, `for_cliente_salida`, `fecha`, `hora`, `observaciones`) VALUES
	(1, 1, '2016/6/1', '13:10pm', 'pouyt');
/*!40000 ALTER TABLE `salida_hotel` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.taxi_uber
DROP TABLE IF EXISTS `taxi_uber`;
CREATE TABLE IF NOT EXISTS `taxi_uber` (
  `id_tax` int(11) NOT NULL AUTO_INCREMENT,
  `for_cliente_d` int(11) NOT NULL DEFAULT '0',
  `origen` varchar(150) NOT NULL DEFAULT '0',
  `destino` varchar(150) NOT NULL DEFAULT '0',
  `fecha` char(50) NOT NULL DEFAULT '0',
  `hora` varchar(50) NOT NULL DEFAULT '0',
  `observaciones` varchar(250) DEFAULT '0',
  PRIMARY KEY (`id_tax`),
  KEY `fk_cliente_uber` (`for_cliente_d`),
  CONSTRAINT `fk_cliente_uber` FOREIGN KEY (`for_cliente_d`) REFERENCES `user_app` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.taxi_uber: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `taxi_uber` DISABLE KEYS */;
REPLACE INTO `taxi_uber` (`id_tax`, `for_cliente_d`, `origen`, `destino`, `fecha`, `hora`, `observaciones`) VALUES
	(1, 1, 'caribe', 'mahaukal', '2015/13/5', '19:14pm', '20$\n'),
	(2, 1, 'oa', 'SN', '2015/13/15', '05:36am', '90$');
/*!40000 ALTER TABLE `taxi_uber` ENABLE KEYS */;


-- Volcando estructura para tabla con_info.user_app
DROP TABLE IF EXISTS `user_app`;
CREATE TABLE IF NOT EXISTS `user_app` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `fk_id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_id_cliente` (`fk_id_cliente`),
  CONSTRAINT `fk_id_cliente` FOREIGN KEY (`fk_id_cliente`) REFERENCES `cliente_hotel_innova` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla con_info.user_app: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user_app` DISABLE KEYS */;
REPLACE INTO `user_app` (`id_usuario`, `usuario`, `contrasena`, `fk_id_cliente`) VALUES
	(1, 'user', '123', 1);
/*!40000 ALTER TABLE `user_app` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
