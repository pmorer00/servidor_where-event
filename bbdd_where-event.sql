-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-06-2019 a las 18:08:48
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bbdd_where-event`
--
CREATE DATABASE IF NOT EXISTS `bbdd_where-event` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `bbdd_where-event`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` int(10) NOT NULL AUTO_INCREMENT,
  `id_categoria_padre` int(10) DEFAULT NULL,
  `nombre` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(250) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `id_categoria_padre` (`id_categoria_padre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `id_categoria_padre`, `nombre`, `descripcion`) VALUES
(1, NULL, 'Futbol', 'Futboleros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE IF NOT EXISTS `eventos` (
  `id_evento` int(50) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(25) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(250) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_inicio` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_fin` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ubicacion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_evento`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id_evento`, `id_usuario`, `nombre`, `descripcion`, `fecha_inicio`, `fecha_fin`, `ubicacion`) VALUES
(1, 17, 'Quedadita maja', 'Vamos a pasarlo muy bien', '2019-06-05 00:00:00', NULL, '42.615257, -5.557873'),
(2, 13, 'Partido futbol cancha', 'Partido de futbol en la cancha de detrás de la uni', '2019-06-04 00:00:00', '2019-06-29 00:00:00', '42.615257, -5.557873'),
(3, 7, 'Quedada conocer gente futbol', 'Quedada conocer gente jugando a futbol', '2019-06-26 00:00:00', NULL, '42.665257, -5.577873'),
(4, 13, 'Partido baloncesto', 'Partido baloncesto', '02/06/2019 15:54', '03/06/2019 15:54', '37.422068484440004, -122.08413898944855'),
(5, 21, 'asd', 'asfa', '02/06/2019 16:00', '11/06/2019 16:00', '37.422035999517064, -122.08288840949535'),
(6, 21, 'Rubn Fernndez', 'hdksks', '02/06/2019 18:04', '02/06/2019 18:25', '42.618726267964675, -5.565965585410594');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eve_cat`
--

CREATE TABLE IF NOT EXISTS `eve_cat` (
  `id_categoria` int(10) NOT NULL,
  `id_evento` int(50) NOT NULL,
  PRIMARY KEY (`id_categoria`,`id_evento`),
  KEY `id_evento` (`id_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(25) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(150) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasenya` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `es_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nickname`, `email`, `contrasenya`, `es_admin`) VALUES
(3, 'usuario3', 'usuario3@email.com', 'asdf1234', 0),
(7, 'pepe', 'correo@email.com', 'asdf1234', 0),
(8, 'pepa', 'correa@email.com', 'asdf1234', 0),
(9, 'pepi', 'correi@email.com', 'asdf1234', 0),
(11, 'asdf@', 'asdf@', 'asdf', 0),
(13, '12345', '12345@12345.com', '12345', 0),
(16, 'pablo', 'pablo', 'pablo', 0),
(17, 'nombreenemail', 'emailennombre@', 'contrasenya', 0),
(18, 'usurio2', 'sadw@', 'asdq', 0),
(19, 'usuario2123', 'asdw2123@42', 'wq231234', 0),
(21, '1234', '1234@com.com', '1234', 1),
(22, '123456', '12345@1234dsfdf5.com', '12345', 0);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD CONSTRAINT `categorias_ibfk_1` FOREIGN KEY (`id_categoria_padre`) REFERENCES `categorias` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `eventos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `eve_cat`
--
ALTER TABLE `eve_cat`
  ADD CONSTRAINT `eve_cat_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`) ON UPDATE CASCADE,
  ADD CONSTRAINT `eve_cat_ibfk_2` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id_evento`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
