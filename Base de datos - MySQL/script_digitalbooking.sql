-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema digitalbooking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema digitalbooking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `digitalbooking` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `digitalbooking` ;

-- -----------------------------------------------------
-- Table `digitalbooking`.`caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`caracteristicas` (
  `caracteristicas_id` BIGINT NOT NULL AUTO_INCREMENT,
  `icono` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`caracteristicas_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`categorias` (
  `categorias_id` BIGINT NOT NULL AUTO_INCREMENT,
  `cantidad_productos` INT NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NOT NULL,
  `titulo` VARCHAR(50) NOT NULL,
  `url_imagen` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`categorias_id`),
  UNIQUE INDEX `UK_ovtw43omtmum2ljucdt7bgo34` (`titulo` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`ciudades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`ciudades` (
  `ciudades_id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `nombre_pais` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ciudades_id`),
  UNIQUE INDEX `UK_4s6dlla8rc87qomrxq2q2js74` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`productos` (
  `productos_id` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(255) NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `normas` VARCHAR(255) NULL DEFAULT NULL,
  `politica_cancelacion` VARCHAR(255) NULL DEFAULT NULL,
  `saludyseguridad` VARCHAR(255) NULL DEFAULT NULL,
  `categorias_id` BIGINT NULL DEFAULT NULL,
  `ciudades_id` BIGINT NOT NULL,
  PRIMARY KEY (`productos_id`),
  INDEX `FKstfxoqd4ovfw6208wypspgf9n` (`categorias_id` ASC) VISIBLE,
  INDEX `FKgugefibw4s79j145gayem66va` (`ciudades_id` ASC) VISIBLE,
  CONSTRAINT `FKgugefibw4s79j145gayem66va`
    FOREIGN KEY (`ciudades_id`)
    REFERENCES `digitalbooking`.`ciudades` (`ciudades_id`),
  CONSTRAINT `FKstfxoqd4ovfw6208wypspgf9n`
    FOREIGN KEY (`categorias_id`)
    REFERENCES `digitalbooking`.`categorias` (`categorias_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`imagenes` (
  `imagenes_id` BIGINT NOT NULL AUTO_INCREMENT,
  `productos_id` BIGINT NULL DEFAULT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `url_imagen` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`imagenes_id`),
  INDEX `FK8mbe8b2to3lk77sdqqsljo0bl` (`productos_id` ASC) VISIBLE,
  CONSTRAINT `FK8mbe8b2to3lk77sdqqsljo0bl`
    FOREIGN KEY (`productos_id`)
    REFERENCES `digitalbooking`.`productos` (`productos_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`productos_has_caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`productos_has_caracteristicas` (
  `productos_id` BIGINT NOT NULL,
  `caracteristicas_id` BIGINT NOT NULL,
  INDEX `FKnxojufb596oehgb5a13e90c3b` (`caracteristicas_id` ASC) VISIBLE,
  INDEX `FKiwadtwd7xb1tss31tl9a7m8w1` (`productos_id` ASC) VISIBLE,
  CONSTRAINT `FKiwadtwd7xb1tss31tl9a7m8w1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `digitalbooking`.`productos` (`productos_id`),
  CONSTRAINT `FKnxojufb596oehgb5a13e90c3b`
    FOREIGN KEY (`caracteristicas_id`)
    REFERENCES `digitalbooking`.`caracteristicas` (`caracteristicas_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`roles` (
  `roles_id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`roles_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`usuarios` (
  `usuarios_id` BIGINT NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `rol_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`usuarios_id`),
  UNIQUE INDEX `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email` ASC) VISIBLE,
  INDEX `FKqf5elo4jcq7qrt83oi0qmenjo` (`rol_id` ASC) VISIBLE,
  CONSTRAINT `FKqf5elo4jcq7qrt83oi0qmenjo`
    FOREIGN KEY (`rol_id`)
    REFERENCES `digitalbooking`.`roles` (`roles_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`puntuaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`puntuaciones` (
  `puntuaciones_id` BIGINT NOT NULL AUTO_INCREMENT,
  `productos_id` BIGINT NULL DEFAULT NULL,
  `puntuacion` INT NULL DEFAULT NULL,
  `usuarios_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`puntuaciones_id`),
  INDEX `FKp907t8034d2r9e265n1pfeacy` (`usuarios_id` ASC) VISIBLE,
  INDEX `FKp2dpi9f369jwnp745v6ntum6v` (`productos_id` ASC) VISIBLE,
  CONSTRAINT `FKp2dpi9f369jwnp745v6ntum6v`
    FOREIGN KEY (`productos_id`)
    REFERENCES `digitalbooking`.`productos` (`productos_id`),
  CONSTRAINT `FKp907t8034d2r9e265n1pfeacy`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `digitalbooking`.`usuarios` (`usuarios_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `digitalbooking`.`reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `digitalbooking`.`reservas` (
  `reservas_id` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha_final` DATE NULL DEFAULT NULL,
  `fecha_inicial` DATE NULL DEFAULT NULL,
  `hora_de_reserva` DOUBLE NULL DEFAULT NULL,
  `productos_id` BIGINT NULL DEFAULT NULL,
  `usuarios_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`reservas_id`),
  INDEX `FKirb5nusdi45kv31f3ibk6648v` (`usuarios_id` ASC) VISIBLE,
  INDEX `FKhpc4jjufbc7f4s46bwcnw0001` (`productos_id` ASC) VISIBLE,
  CONSTRAINT `FKhpc4jjufbc7f4s46bwcnw0001`
    FOREIGN KEY (`productos_id`)
    REFERENCES `digitalbooking`.`productos` (`productos_id`),
  CONSTRAINT `FKirb5nusdi45kv31f3ibk6648v`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `digitalbooking`.`usuarios` (`usuarios_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
