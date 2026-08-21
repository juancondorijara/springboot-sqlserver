USE model;


SELECT DB_NAME() AS BaseDeDatosActual;


CREATE TABLE CUSTOMER (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    DNI CHAR(8) NOT NULL,
    CELLPHONE CHAR(9) NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    STATE CHAR(1) NOT NULL,
    CREATED_AT DATETIME NULL DEFAULT GETDATE(),              -- Fecha de creación
    UPDATED_AT DATETIME NULL,                                -- Fecha de última actualización
    DELETED_AT DATETIME NULL,                                -- Fecha de eliminación lógica
    RESTORED_AT DATETIME NULL                                -- Fecha de restauración 
);


INSERT INTO CUSTOMER 
(DNI, CELLPHONE, FIRST_NAME, LAST_NAME, STATE)
VALUES 
('74564578', '987654321', 'Juan', 'Sanchez', 'A'),
('87654321', '912345678', 'María', 'Lopez', 'A'),
('45678912', '999888777', 'Carlos', 'Ramirez', 'A');


SELECT * FROM CUSTOMER;


DROP TABLE CUSTOMER;

