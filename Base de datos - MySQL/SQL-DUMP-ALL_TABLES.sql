-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: digitalbooking
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `caracteristicas`
--

INSERT INTO `caracteristicas` VALUES (1,'fas fa-location-arrow','GPS');
INSERT INTO `caracteristicas` VALUES (2,'fas fa-tablet-alt','Touch');
INSERT INTO `caracteristicas` VALUES (3,'fas fa-wifi','Wifi');
INSERT INTO `caracteristicas` VALUES (4,'fas fa-wind','Air');
INSERT INTO `caracteristicas` VALUES (5,'fas fa-play-circle','Audio');

--
-- Dumping data for table `categorias`
--
--- https://img.digibook.link/

INSERT INTO `categorias` VALUES (1, null, 'Autos Deportivos','Deportivo','https://img.digibook.link/Categoria_1_deportivo_AUDI-Q5.jpg');
INSERT INTO `categorias` VALUES (2, null, 'Autos Lujosos','Lujoso','https://img.digibook.link/Categoria_2_lujoso_AUDI-Q7.jpg');
INSERT INTO `categorias` VALUES (3, null, 'Autos Económicos','Económico','https://img.digibook.link/Categoria_3_economico_AUDI-A4.jpg');
INSERT INTO `categorias` VALUES (4, null, 'Autos Eléctricos','Eléctrico','https://img.digibook.link/Categoria_4_electrico_Q8.webp');

--
-- Dumping data for table `ciudades`
--

INSERT INTO `ciudades` VALUES (1,'Rosario','Argentina');
INSERT INTO `ciudades` VALUES (2,'Cordoba','Argentina');
INSERT INTO `ciudades` VALUES (3,'Buenos Aires','Argentina');
INSERT INTO `ciudades` VALUES (4,'Bariloche','Argentina');

--
-- Dumping data for table `productos`
--


INSERT INTO `productos` VALUES (1,'Está situado en la ciudad de Rosario, en la Avenida Siempreviva 1234. El auto deportivo Chevrolet Joy Sedán es un carro sencillo, funcional y con un precio muy atractivo. Es una muy buena opción para quienes buscan un auto familiar accesible.','Avenida Siempreviva 1234','Joy Sedán', 'Se admiten mascotas en este vehículo de alquiler de DigitalBooking.', 'Si hacés una reserva y no te presentás a la hora pactada tenemos una tolerancia de espera de 59 minutos, una vez pasados se considera no show y la reserva queda cancelada.', 'La licencia del conductor debe ser válida durante todo el período de arriendo.', 1,1);
INSERT INTO `productos` VALUES (2,'Está situado en la ciudad de Córdoba en la Avenida Bolivar 456. El auto deportivo Tracker Turbo te ofrece una transmisión automática de seis velocidades, con la opción Active Select para cambio manual de marchas, y un potente motor turbo.','Avenida Bolivar 456','Tracker Turbo', 'Se admiten mascotas en este vehículo de alquiler de DigitalBooking. ', 'Se aceptan cancelaciones hasta un día natural antes de la fecha de la reserva.', 'La licencia del conductor debe ser válida durante todo el período de arriendo.', 1,2);
INSERT INTO `productos` VALUES (3,'Está situado en la ciudad de Buenos Aires en la Avenida San Martin 1500. El auto lujoso Onix Turbo Gt, fue reconocido por el CESVI como el auto más seguro en la Categoría Chicos y como el más completo en relación precio-contenido de seguridad.','San Martin 1500','Onix Turbo Gt', 'La edad mínima para alquilar los vehículos es de 21 años. ', 'Se aceptan cancelaciones hasta un día natural antes de la fecha de la reserva.', 'La licencia del conductor debe ser válida durante todo el período de arriendo.', 2,3);
INSERT INTO `productos` VALUES (4,'Está situado en la ciudad de Bariloche en la Avenida Libertad 2300. El auto lujoso Audi A6 es un modelo del segmento E Ejecutivo, lanzado en el 1994, siendo el sucesor del Audi 100, incorporando motores tanto diésel con tracción delantera o quattro.','Avenida Libertad 2300','Audi A6', 'Check-out: 10:00', 'Se aceptan cancelaciones hasta un día natural antes de la fecha de la reserva.', 'Se ofrece un seguro de auto de alquiler y productos de protección.',2,4);
INSERT INTO `productos` VALUES (5,'Está situado en la ciudad de Córdoba en la Avenida Moreno 896. Manejar el auto económico Onix Turbo es vivir una experiencia única, por su diseño, tecnología y confort. Cuentas con conectividad total y soluciones innovadoras, como computadora a bordo.','Avenida Moreno 896','Onix Turbo', 'La edad mínima para alquilar los vehículos es de 21 años. ', 'Si hacés una reserva y no te presentás a la hora pactada tenemos una tolerancia de espera de 59 minutos, una vez pasados se considera no show y la reserva queda cancelada.', 'Se ofrece un seguro de auto de alquiler y productos de protección.',3,2);
INSERT INTO `productos` VALUES (6,'Está situado en la ciudad de Buenos Aires en Milán al 2000. El auto económico Chevrolet Captiva es un SUV de tamaño medio desarrollado por la división coreana de General Motors y lanzado al mercado en 2006. Es un SUV de cinco puertas con motor delantero.','Milán 2000','Captiva', 'La edad mínima para alquilar los vehículos es de 21 años. ', 'Si la reserva se cancela dentro de las 24 horas previas a la recogida o si no se utiliza la reserva, se aplicará una tarifa de $50 por concepto de cancelación.', 'Depósito de seguridad', 3,3);
INSERT INTO `productos` VALUES (7,'Está situado en la ciudad de Rosario en Turín 670. El auto eléctrico Chevrolet Beat es un sedán que luce juvenil y deportivo gracias a sus faros de gran tamaño. Cuenta con un diseño que evocan los autos de competición conformando así un conjunto moderno.','Turín 670','Beat', 'Check-out: 10:00', 'Si la reserva se cancela dentro de las 24 horas previas a la recogida o si no se utiliza la reserva, se aplicará una tarifa de $50 por concepto de cancelación.', 'Se ofrece un seguro de auto de alquiler y productos de protección.',4,1);
INSERT INTO `productos` VALUES (8,'Está situado en la ciudad de Bariloche en Cuba 5200. El auto eléctrico Chevrolet Onix RS tiene apariencia de hot-hatch y motor turbo de 1.0 litro. El Onix RS se queda con el propulsor turbo de 1.0 litro, capaz de generar 115 hp y 118 lb-pie.','Cuba 5200','Onix RS', 'Se admiten mascotas en este vehículo de alquiler de DigitalBooking.', 'Si la reserva se cancela dentro de las 24 horas previas a la recogida o si no se utiliza la reserva, se aplicará una tarifa de $50 por concepto de cancelación.', 'Se ofrece un seguro de auto de alquiler y productos de protección.',4,4);


--
-- Dumping data for table `imagenes`
--

INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (1,1,'Joy Sedan - Imagen principal','https://img.digibook.link/Recomendacion_1_joy-sedan.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (2,2,'Tracker Turbo - Imagen principal','https://img.digibook.link/Recomendacion_2_tracker-turbo.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (3,3,'Onix Turbo Gt - Imagen principal','https://img.digibook.link/Recomendacion_3_onix-turbo.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (4,4,'Audi A6 - Imagen principal','https://img.digibook.link/Recomendacion_4_audi_A6.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (5,5,'Onix Turbo - Imagen principal','https://img.digibook.link/Recomendacion_5_onix-turbo.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (6,6,'Captiva - Imagen principal','https://img.digibook.link/Recomendacion_6_captiva.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (7,7,'Beat - Imagen principal','https://img.digibook.link/Recomendacion_7_beat.png');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (8,8,'Onix RS - Imagen principal','https://img.digibook.link/Recomendacion_8_onix-RS.png');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (9,1,'Audi Q5 - auto completo','https://img.digibook.link/car-604019__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (10,1,'Audi Q5 - volante de costado','https://img.digibook.link/car-933704__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (11,1,'Audi Q5 - rueda trasera','https://img.digibook.link/car-tire-1031579__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (12,1,'Audi Q5- volante de frente','https://img.digibook.link/audi-a3-2095801__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (13,1,'Audi Q5 - caja de cambios','https://img.digibook.link/mercedes-benz-1470151__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (14,2,'Audi Q7 - auto completo','https://img.digibook.link/car-1890494__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (15,2,'Audi Q7 - parte trasera - ROTA','https://img.digibook.link/car-1890494__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (16,2,'Audi Q7 - volante','https://img.digibook.link/audi-5911624__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (17,2,'Audi Q7 - volante y velocimetro','https://img.digibook.link/steering-wheel-5954529__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (18,2,'Audi Q7 - logo de audi','https://img.digibook.link/audi-5024616__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (19,3,'Bmwl3 - auto de frente','https://img.digibook.link/bmw-768688__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (20,3,'Bmwl3 - asiento del conductor','https://img.digibook.link/bmw-1911913_960_720.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (21,3,'Bmwl3 - logo BMW','https://img.digibook.link/automotive-1838744__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (22,3,'Bmwl3 - velocimetro','https://img.digibook.link/bmw-1399427__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (23,3,'Bmwl3 - caja de cambios','https://img.digibook.link/bmw-1399430__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (24,4,'Kia Sportage - auto de frente','https://img.digibook.link/kia-2382086__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (25,4,'Kia Sportage - volante y airbag','https://img.digibook.link/kia-2382089__480.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (26,4,'Kia Sportage - caja de cambios','https://img.digibook.link/transmission-6292021__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (27,4,'Kia Sportage - luz y rueda delantera','https://img.digibook.link/kia-stinger-gt-2854653__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (28,4,'Kia Sportage - luz y rueda delantera encendida','https://img.digibook.link/kia-sports-car-2773258__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (29,5,'Mazda Cx 30 - auto de costado','https://img.digibook.link/car-6122175__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (30,5,'Mazda Cx 30 - volante','https://img.digibook.link/auto-3209449__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (31,5,'Mazda Cx 30 - caja de cambios','https://img.digibook.link/car-6379534__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (32,5,'Mazda Cx 30 - freno de mano','https://img.digibook.link/auto-3209443__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (33,5,'Mazda Cx 30 - motor','https://img.digibook.link/car-6379541__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (34,6,'Nissan March - auto de frente','https://img.digibook.link/car-3321213__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (35,6,'Nissan March - rueda','https://img.digibook.link/nissan-gtr-3174157__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (36,6,'Nissan March - lado conductor por dentro','https://img.digibook.link/nissan-885310__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (37,6,'Nissan March - auto de atrás','https://img.digibook.link/car-3321212__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (38,6,'Nissan March - lado acompañante por dentro','https://img.digibook.link/car-3321211__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (39,7,'Renault Logan - auto de frente','https://img.digibook.link/renault-2942017__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (40,7,'Renault Logan - falta la imagen','https://img.digibook.link/car-2247146__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (41,7,'Renault Logan - boton','https://img.digibook.link/dashboard-1162965__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (42,7,'Renault Logan - motor','https://img.digibook.link/car-engine-1548434__340.webp');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (43,7,'Renault Logan - llanta','https://img.digibook.link/wheel-rim-2194373__340.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (44,8,'Spark GT - auto de frente de lado derecho','https://img.digibook.link/20181107-Spark-Gt-Activ-2019.png');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (45,8,'Spark GT - auto de su lado derecho','https://img.digibook.link/images1.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (46,8,'Spark GT - auto de frente de lado izquierdo','https://img.digibook.link/RT_V_8ec8cfe1989e465ca2d8c1067ecb25bc.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (47,8,'Spark GT - auto de parte trasera','https://img.digibook.link/images.jpg');
INSERT INTO `imagenes` (imagenes_id,  productos_id, titulo, url_imagen) VALUES (48,8,'Spark GT  - auto de frente de lado derecho - bosque','https://img.digibook.link/2019-spark-activ-01.jpg');



--
-- Dumping data for table `productos_has_caracteristicas`
--
INSERT INTO `productos_has_caracteristicas` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7);
INSERT INTO `productos_has_caracteristicas` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7);
INSERT INTO `productos_has_caracteristicas` VALUES (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7);
INSERT INTO `productos_has_caracteristicas` VALUES (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7);
INSERT INTO `productos_has_caracteristicas` VALUES (5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7);
INSERT INTO `productos_has_caracteristicas` VALUES (6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7);
INSERT INTO `productos_has_caracteristicas` VALUES (7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7);
INSERT INTO `productos_has_caracteristicas` VALUES (8,1),(8,2),(8,3),(8,4),(8,5),(8,6),(8,7);

--
-- Dumping data for table `puntuaciones`
--


--
-- Dumping data for table `reservas`
--
INSERT INTO `reservas` VALUES (1, "2021-12-15", "2021-12-10", 5.0, 1, 1);
INSERT INTO `reservas` VALUES (2, "2022-01-06", "2022-01-03", 6.0, 2, 1);
INSERT INTO `reservas` VALUES (3, "2022-01-08", "2022-01-01", 7.0, 3, 1);


--
-- Dumping data for table `roles`
--
INSERT INTO `roles` VALUES (1,'ADMIN');
INSERT INTO `roles` VALUES (2,'USER');

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` VALUES (1,'Díaz','diego@mail.com','Diego','$2a$10$4W6MUqJ6oYg9Gyn3hCwemO1SlndwkeWh98iiJfrLz1AIbSp6MLpNu',2);
INSERT INTO `usuarios` VALUES (2,'Acosta','brenda@mail.com','Brenda','$2a$10$2iZS4qvnM.QBy2WnHdDGueX/jESqcO3YeLUExtm/Bc.Y59GTbpNkC',2);
INSERT INTO `usuarios` VALUES (3,'Andrade','ayelen@mail.com','Ayelén','$2a$10$fhFr/.mNV8DVhIU0cesPg.Skd8Pr3L3CR85JRDdBdiCGFdB.TVC5.',2);
INSERT INTO `usuarios` VALUES (4,'Amaya','lara@mail.com','Lara','$2a$10$OSd1O.v5BvddzE97P6lxEeOG.8hzRgYKTjpjunJ5QqGz17f6w3Ame',2);
INSERT INTO `usuarios` VALUES (5,'Campo','jesus@mail.com','Jesus','$2a$10$dR2RhG2vK1yVcEiempurs.2WkwBXZ/UH5I3khfthRVcno.J8k.znq',2);
INSERT INTO `usuarios` VALUES (6,'Orozco','andrea@mail.com','Andrea','$2a$10$paEehYfaSFNPl2ZwZLhRCuX0QBmlsmnEJQ2.Lz4ESleetURWhSIX6',2);
INSERT INTO `usuarios` VALUES (7,'Rodriguez','admin@mail.com','Bruno','$2a$10$paEehYfaSFNPl2ZwZLhRCuX0QBmlsmnEJQ2.Lz4ESleetURWhSIX6',1);

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
