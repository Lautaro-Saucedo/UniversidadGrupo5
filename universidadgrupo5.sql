-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2021 a las 03:18:17
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `universidadgrupo5`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id_alumno` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(15) NOT NULL,
  `fecha_nac` date NOT NULL,
  `legajo` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id_alumno`, `nombre`, `apellido`, `fecha_nac`, `legajo`, `estado`) VALUES
(1, 'Lautaro', 'Saucedo', '2016-01-10', 100, 0),
(2, 'Brennana', 'Turnering', '2018-01-10', 109, 1),
(3, 'Christine', 'Wilson', '1994-01-17', 102, 0),
(4, 'Chadwick', 'Garrett', '1953-11-12', 103, 1),
(5, 'Neville', 'Dorsey', '1961-03-20', 104, 1),
(6, 'Cheyenne', 'Bryant', '2019-01-31', 105, 0),
(7, 'Dennis', 'Mills', '2006-01-17', 106, 0),
(8, 'Penelope', 'Harmon', '2013-05-17', 107, 1),
(9, 'Scott', 'Marshall', '1962-11-10', 108, 1),
(10, 'Fabricio', 'Molina', '1994-05-12', 101, 1),
(11, 'Aileen', 'Mcconnell', '1975-05-27', 110, 1),
(12, 'Brandon', 'Hudson', '1968-04-17', 111, 0),
(13, 'Bryar', 'Pickett', '2021-05-20', 112, 1),
(14, 'Zahir', 'Webster', '1966-04-16', 113, 1),
(15, 'Ora', 'Walters', '1997-11-23', 114, 0),
(4316, 'lauch', 'sauce', '2021-05-13', 123, 1),
(4317, 'Ariel', 'Saucedo', '2006-11-22', 9090, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursada`
--

CREATE TABLE `cursada` (
  `id_cursada` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `nota` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cursada`
--

INSERT INTO `cursada` (`id_cursada`, `id_alumno`, `id_materia`, `nota`) VALUES
(1, 8, 3, 6),
(3, 14, 6, 9),
(4, 9, 12, 4),
(5, 5, 1, 4),
(6, 11, 11, 8),
(11, 11, 2, 8),
(17, 14, 13, 4),
(18, 10, 2, 8),
(26, 14, 3, 6),
(31, 2, 2, 3),
(34, 2, 1, 0),
(37, 2, 6, 0),
(40, 2, 10, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `id_materia` int(11) NOT NULL,
  `nombre_materia` varchar(20) NOT NULL,
  `año` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`id_materia`, `nombre_materia`, `año`, `estado`) VALUES
(1, 'Matematicas', 1, 1),
(2, 'Laboratorio I', 1, 1),
(3, 'Admin. de DB', 1, 1),
(4, 'Lengua', 2, 0),
(5, 'Historia', 2, 0),
(6, 'Economia', 1, 1),
(7, 'Ingles', 3, 0),
(8, 'Musica', 1, 0),
(9, 'Informatica', 2, 0),
(10, 'Pugilismo', 1, 1),
(11, 'Biologia', 1, 1),
(12, 'Tarot', 2, 1),
(13, 'Alquimia', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id_alumno`),
  ADD UNIQUE KEY `legajo` (`legajo`);

--
-- Indices de la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD PRIMARY KEY (`id_cursada`),
  ADD KEY `id_alumno` (`id_alumno`),
  ADD KEY `id_materia` (`id_materia`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`id_materia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4318;

--
-- AUTO_INCREMENT de la tabla `cursada`
--
ALTER TABLE `cursada`
  MODIFY `id_cursada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `id_materia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3416;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD CONSTRAINT `cursada_ibfk_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`),
  ADD CONSTRAINT `cursada_ibfk_2` FOREIGN KEY (`id_materia`) REFERENCES `materia` (`id_materia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
