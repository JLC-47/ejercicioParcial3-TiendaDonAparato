create database tienaDonAparato;
use tienaDonAparato;

create table cliente(
documento varchar(20) primary key,
nombre varchar(50),
edad int,
tipo varchar (1),
apellido varchar(50),
telefono varchar(50)
);



create table producto(
idProducto int primary key,
nombre varchar(50),
cantidad int,
precio double,
descripcion varchar(150)
);

create table compra(
idCompra int auto_increment primary key,
detalleCompra varchar (200),
descuento double,
precioCompra double,
total double,
fechaCompra date,
documento varchar(20),
idProducto int,
foreign key (documento) references cliente(documento),
foreign key (idProducto) references producto(idProducto)
);


drop table compra;

INSERT INTO cliente (documento, nombre, apellido, edad, tipo, telefono) VALUES 
('1090666', 'Beatriz', 'Pinzón', 29, 'A', '3015556677'),
('1090777', 'Nicolas', 'Mora', 31, 'B', '3128889900'),
('1090888', 'Sandra', 'Patiño', 26, 'N', '3201112244'), 
('1090999', 'Freddy', 'Contreras', 34, ' ', '3157773322'),
('1091000', 'Patricia', 'Fernández', 27, 'C', '3104445588'),
('1091100', 'Hermes', 'Pinzón', 55, 'A', '3002221199'),
('1091200', 'Julia', 'Solano', 52, 'N', '3116664433');


INSERT INTO producto (idProducto, nombre, cantidad, precio, descripcion) VALUES 
(109, 'Protoboard 830 puntos', 25, 18000, 'Tablilla de pruebas con base adhesiva'),
(110, 'Pack Resistencias (100u)', 100, 12000, 'Valores variados desde 10 ohm hasta 1M ohm'),
(111, 'Capacitor Electrolítico', 200, 500, '1000uF a 25V para filtrado de fuente'),
(112, 'Arduino Uno R3', 12, 65000, 'Placa de desarrollo microcontrolador ATmega328P'),
(113, 'Sensor Ultrasónico', 30, 15000, 'Módulo HC-SR04 para medición de distancia'),
(114, 'Display LCD 16x2', 15, 22000, 'Pantalla con retroiluminación azul e interfaz I2C'),
(115, 'Módulo Bluetooth HC-05', 10, 28000, 'Comunicación inalámbrica serial maestro/esclavo');


INSERT INTO compra (detalleCompra, descuento, precioCompra, total, fechaCompra, documento, idProducto) VALUES 
('Compra taller educativo', 2000, 18000, 16000, '2026-03-20', '1090666', 109),
('Suministros varios', 0, 12000, 12000, '2026-03-21', '1090888', 110),
('Proyecto robótica', 5000, 65000, 60000, '2026-03-22', '1091000', 112),
('Repuestos electrónicos', 1000, 15000, 14000, '2026-03-23', '1091200', 113),
('Kit visualización', 0, 22000, 22000, '2026-03-24', '1090777', 114),
('Componentes filtrado', 0, 500, 500, '2026-03-25', '1090999', 111),
('Modulo comunicacion', 3000, 28000, 25000, '2026-03-26', '1091100', 115);


select * from cliente;
