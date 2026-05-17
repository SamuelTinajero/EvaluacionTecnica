IF NOT EXISTS (SELECT 1 FROM sys.databases WHERE name = 'castores_inventario')
BEGIN
    CREATE DATABASE castores_inventario;
END
GO

USE castores_inventario;
GO

IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'roles')
BEGIN
    CREATE TABLE roles (
        idRol INT PRIMARY KEY IDENTITY(1,1),
        nombre VARCHAR(50) NOT NULL
    );
END
GO

IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'usuarios')
BEGIN
    CREATE TABLE usuarios (
        idUsuario INT PRIMARY KEY IDENTITY(1,1),
        nombre VARCHAR(100) NOT NULL,
        correo VARCHAR(50) NOT NULL UNIQUE,
        contrasena VARCHAR(255) NOT NULL,
        idRol INT NOT NULL,
        estatus INT NOT NULL DEFAULT 1,
        CONSTRAINT fk_usuarios_rol FOREIGN KEY (idRol) REFERENCES roles(idRol)
    );
END
GO

IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'productos')
BEGIN
    CREATE TABLE productos (
        idProducto INT PRIMARY KEY IDENTITY(1,1),
        nombre VARCHAR(100) NOT NULL,
        descripcion VARCHAR(255),
        stock INT NOT NULL DEFAULT 0,
        estatus INT NOT NULL DEFAULT 1,
        fechaCreacion DATETIME NOT NULL DEFAULT GETDATE(),
        fechaActualizacion DATETIME null,
        modificadoPor VARCHAR(100),
        creadoPor VARCHAR(100)
    );
END
GO

IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'movimientos')
BEGIN
    CREATE TABLE movimientos (
        idMovimiento INT PRIMARY KEY IDENTITY(1,1),
        idProducto INT NOT NULL,
        idUsuario INT NOT NULL,
        tipoMovimiento VARCHAR(10) NOT NULL,
        cantidad INT NOT NULL,
        stockAnterior INT NOT NULL,
        stockNuevo INT NOT NULL,
        fechaMovimiento DATETIME NOT NULL DEFAULT GETDATE(),
        CONSTRAINT fk_movimientos_producto FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
        CONSTRAINT fk_movimientos_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
    );
END
GO

DELETE FROM roles;
INSERT INTO roles (nombre) VALUES ('ADMINISTRADOR');
INSERT INTO roles (nombre) VALUES ('ALMACENISTA');
GO

DELETE FROM usuarios;
INSERT INTO usuarios (nombre, correo, contrasena, idRol, estatus) 
VALUES (
    'Admin',
    'admin@castores.com',
    '$2a$10$A.2R3YTlVwsZwZZvOQQv6ON2bzxd18hnGuv7X.2YdkyX.MFosrth.',
    1,
    1
);

INSERT INTO usuarios (nombre, correo, contrasena, idRol, estatus)
VALUES (
    'Almacenista',
    'almacen@castores.com',
    '$2a$10$DiF.Zz2OO7jxBzUcBR9Mie5uJF16gp4E7P7Z7FvSdqVAQuAGZ0n9W',
    2,
    1
);
GO

DELETE FROM productos;
INSERT INTO productos (nombre, descripcion, stock, estatus, fechaCreacion)
VALUES 
    ('Caja de Cartón Doble Canal 60x40x40 cm',      'Caja corrugada doble canal reforzada, resistencia de hasta 30 kg, ideal para envíos de larga distancia',                        200, 1, GETDATE()),
    ('Caja de Cartón Simple Canal 40x30x20 cm',      'Caja corrugada estándar para envíos nacionales, material reciclado certificado FSC, resistencia de hasta 15 kg',               350, 1, GETDATE()),
    ('Sobre Burbuja Kraft 25x35 cm',                 'Sobre acolchado con burbuja de polietileno interior, exterior kraft marrón, autoadhesivo, protección contra golpes y humedad', 500, 1, GETDATE()),
    ('Rollo Film Stretch Transparente 45 cm x 300 m','Film estirable de polietileno de alta resistencia para paletizado y embalaje secundario, calibre 23 micras',                   80,  1, GETDATE()),
    ('Cinta de Embalaje Scotch 3M 48mm x 100 m',    'Cinta adhesiva acrílica de alta adherencia, resistente a temperaturas extremas entre -10 °C y 60 °C, transparente',            300, 1, GETDATE()),
    ('Papel Burbuja Kraft 1.20 m x 50 m',            'Rollo de papel burbuja laminado con kraft, protección premium para artículos frágiles, burbuja de 10 mm de diámetro',         60,  1, GETDATE()),
    ('Pallet de Madera 120x80 cm (Euro Pallet)',     'Pallet europeo estándar EUR/EPAL certificado, madera de pino tratada HT, carga estática de hasta 4,000 kg',                    40,  1, GETDATE()),
    ('Esquinero Cartón Prensado 35 mm x 35 mm x 1 m','Cantonera de cartón compacto para protección de aristas en tarimas y cajas grandes, resistencia al aplastamiento de 800 N',   150, 1, GETDATE());    
GO

PRINT 'Base de datos castores_inventario creada exitosamente.';
