/* Populate tables Clientes */
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ana', 'Venegas', 'avenegas_barrita@gmail.com', NOW(), '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('David', 'Utreras', 'dutreras69@gmail.com', NOW(), '');

/* Populate tabla productos */
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Hamburguesa 1', 'Burger', 2500, 'Si', NOW(), '7c607792-fccf-452d-a1ee-430345d0156a_burguer (1).png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Hamburguesa 2', 'Burger', 3000, 'Si', NOW(), '26ecc447-66c5-4385-a098-561c175b8da1_burguer (2).png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Hamburguesa 3', 'Burger', 3300, 'Si', NOW(), '1560dcc5-ba17-4dc1-825f-78c3bb3e39a8_burguer (3).png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza M 1', 'Pizza', 3500, 'Si', NOW(), '303b7a5c-9edc-4503-a6b2-832d2dac0acf_pizza m.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza M 2', 'Pizza', 4000, 'Si', NOW(), '303b7a5c-9edc-4503-a6b2-832d2dac0acf_pizza m.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza M 3', 'Pizza', 4500, 'Si', NOW(), '303b7a5c-9edc-4503-a6b2-832d2dac0acf_pizza m.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza L 1', 'Pizza', 4500, 'Si', NOW(), '99a38dc4-4bc0-4eb2-aa07-e8f6b858b1e5_pizza l.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza L 2', 'Pizza', 5000, 'Si', NOW(), '99a38dc4-4bc0-4eb2-aa07-e8f6b858b1e5_pizza l.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza L 3', 'Pizza', 5500, 'Si', NOW(), '99a38dc4-4bc0-4eb2-aa07-e8f6b858b1e5_pizza l.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza XL 1', 'Pizza', 5500, 'Si', NOW(), '4cfe3fe6-0a69-4c5e-bcc1-60c99d55dced_pizzaxl.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza XL 2', 'Pizza', 6500, 'Si', NOW(), '4cfe3fe6-0a69-4c5e-bcc1-60c99d55dced_pizzaxl.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Pizza XL 3', 'Pizza', 6000, 'Si', NOW(), '4cfe3fe6-0a69-4c5e-bcc1-60c99d55dced_pizzaxl.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Tabla Carne', 'Tabla', 6000, 'Si', NOW(), '6257643d-f8e2-4439-b59e-8d548699f072_french-fries.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Tabla Lomito', 'Tabla', 6000, 'Si', NOW(), '6257643d-f8e2-4439-b59e-8d548699f072_french-fries.png');
INSERT INTO productos (nombre, categoria, precio, disponibilidad, create_at, foto) VALUES('Tabla Vegeteriana', 'Tabla', 6000,'Si', NOW(), '6257643d-f8e2-4439-b59e-8d548699f072_french-fries.png');

/* Creamos algunas facturas */