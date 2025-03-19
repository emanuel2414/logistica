-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-03-2025 a las 21:33:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `logistica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `despacho`
--

CREATE TABLE `despacho` (
  `id_despacho` int(100) NOT NULL,
  `clie_nombre_completo` varchar(255) DEFAULT NULL,
  `clie_documento` int(12) NOT NULL,
  `clie_direccion_entrega` varchar(255) DEFAULT NULL,
  `clie_contacto` int(15) NOT NULL,
  `cod_seguimiento` varchar(255) DEFAULT NULL,
  `desp_estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `despacho`
--

INSERT INTO `despacho` (`id_despacho`, `clie_nombre_completo`, `clie_documento`, `clie_direccion_entrega`, `clie_contacto`, `cod_seguimiento`, `desp_estado`) VALUES
(42, 'Juan Pérez', 123456789, 'Calle 123 #45-67', 987654321, 'ABC123', 'En transito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_recepcion`
--

CREATE TABLE `historial_recepcion` (
  `id_historial` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_cambio` datetime(6) DEFAULT NULL,
  `tipo_cambio` enum('CONFIRMACION','CREACION','ELIMINACION','MODIFICACION') DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `id_recepcion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productodespacho`
--

CREATE TABLE `productodespacho` (
  `id_producto` bigint(20) NOT NULL,
  `id_despacho` int(100) DEFAULT NULL,
  `prod_codigo` varchar(255) DEFAULT NULL,
  `prod_cantidad` int(100) NOT NULL,
  `prod_descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productodespacho`
--

INSERT INTO `productodespacho` (`id_producto`, `id_despacho`, `prod_codigo`, `prod_cantidad`, `prod_descripcion`) VALUES
(35, 42, 'PROD001', 2, 'Producto A'),
(36, 42, 'PROD002', 1, 'Producto B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_recepcion`
--

CREATE TABLE `producto_recepcion` (
  `id_producto_recepcion` bigint(20) NOT NULL,
  `cantidad` double DEFAULT NULL,
  `estado_producto` enum('ACEPTADO','PENDIENTE','RECHAZADO') DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `unidad_medida` varchar(255) DEFAULT NULL,
  `id_recepcion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` bigint(20) NOT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nit` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recepcion`
--

CREATE TABLE `recepcion` (
  `id_recepcion` bigint(20) NOT NULL,
  `estado` enum('CANCELADA','CONFIRMADA','PENDIENTE') DEFAULT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `numero_orden_compra` varchar(255) DEFAULT NULL,
  `id_proveedor` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportista`
--

CREATE TABLE `transportista` (
  `tran_documento` int(100) NOT NULL,
  `tran_razon_social` varchar(255) DEFAULT NULL,
  `tran_contacto` int(15) NOT NULL,
  `tran_tipo_vehiculo` varchar(255) DEFAULT NULL,
  `tran_capacidad_carga` varchar(255) DEFAULT NULL,
  `tran_estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transportista`
--

INSERT INTO `transportista` (`tran_documento`, `tran_razon_social`, `tran_contacto`, `tran_tipo_vehiculo`, `tran_capacidad_carga`, `tran_estado`) VALUES
(1234567890, 'PROWEBSPORTS SAS', 123456, 'Camion', '1 Tonelada', 'activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportistadespacho`
--

CREATE TABLE `transportistadespacho` (
  `id_tran_desp` bigint(20) NOT NULL,
  `tran_documento` int(100) NOT NULL,
  `id_despacho` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transportistadespacho`
--

INSERT INTO `transportistadespacho` (`id_tran_desp`, `tran_documento`, `id_despacho`) VALUES
(3, 1234567890, 42);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `despacho`
--
ALTER TABLE `despacho`
  ADD PRIMARY KEY (`id_despacho`);

--
-- Indices de la tabla `historial_recepcion`
--
ALTER TABLE `historial_recepcion`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `FKdjdplupdm510m3kqmrmjeciuw` (`id_recepcion`);

--
-- Indices de la tabla `productodespacho`
--
ALTER TABLE `productodespacho`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_despacho` (`id_despacho`);

--
-- Indices de la tabla `producto_recepcion`
--
ALTER TABLE `producto_recepcion`
  ADD PRIMARY KEY (`id_producto_recepcion`),
  ADD KEY `FKcxs2x845gkhpiqys85656s22a` (`id_recepcion`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD UNIQUE KEY `UKgusu9fumbiddffr4fv33qh7mj` (`nit`);

--
-- Indices de la tabla `recepcion`
--
ALTER TABLE `recepcion`
  ADD PRIMARY KEY (`id_recepcion`),
  ADD KEY `FKg3j1hir627vsriweht5g4av97` (`id_proveedor`);

--
-- Indices de la tabla `transportista`
--
ALTER TABLE `transportista`
  ADD PRIMARY KEY (`tran_documento`);

--
-- Indices de la tabla `transportistadespacho`
--
ALTER TABLE `transportistadespacho`
  ADD PRIMARY KEY (`id_tran_desp`),
  ADD KEY `id_despacho` (`id_despacho`),
  ADD KEY `tran_documento` (`tran_documento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `despacho`
--
ALTER TABLE `despacho`
  MODIFY `id_despacho` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `historial_recepcion`
--
ALTER TABLE `historial_recepcion`
  MODIFY `id_historial` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productodespacho`
--
ALTER TABLE `productodespacho`
  MODIFY `id_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `producto_recepcion`
--
ALTER TABLE `producto_recepcion`
  MODIFY `id_producto_recepcion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recepcion`
--
ALTER TABLE `recepcion`
  MODIFY `id_recepcion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transportistadespacho`
--
ALTER TABLE `transportistadespacho`
  MODIFY `id_tran_desp` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial_recepcion`
--
ALTER TABLE `historial_recepcion`
  ADD CONSTRAINT `FKdjdplupdm510m3kqmrmjeciuw` FOREIGN KEY (`id_recepcion`) REFERENCES `recepcion` (`id_recepcion`);

--
-- Filtros para la tabla `productodespacho`
--
ALTER TABLE `productodespacho`
  ADD CONSTRAINT `productodespacho_ibfk_1` FOREIGN KEY (`id_despacho`) REFERENCES `despacho` (`id_despacho`);

--
-- Filtros para la tabla `producto_recepcion`
--
ALTER TABLE `producto_recepcion`
  ADD CONSTRAINT `FKcxs2x845gkhpiqys85656s22a` FOREIGN KEY (`id_recepcion`) REFERENCES `recepcion` (`id_recepcion`);

--
-- Filtros para la tabla `recepcion`
--
ALTER TABLE `recepcion`
  ADD CONSTRAINT `FKg3j1hir627vsriweht5g4av97` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Filtros para la tabla `transportistadespacho`
--
ALTER TABLE `transportistadespacho`
  ADD CONSTRAINT `transportistadespacho_ibfk_1` FOREIGN KEY (`id_despacho`) REFERENCES `despacho` (`id_despacho`),
  ADD CONSTRAINT `transportistadespacho_ibfk_2` FOREIGN KEY (`tran_documento`) REFERENCES `transportista` (`tran_documento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
