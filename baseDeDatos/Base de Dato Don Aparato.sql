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
ducumento varchar(20),
idProducto int,
foreign key (ducumento) references cliente(documento),
foreign key (idProducto) references producto(idProducto)
);


drop table cliente;



