CREATE TABLE bancos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

INSERT INTO bancos (nombre, descripcion) VALUES
('Banco de Bogotá', 'Entidad bancaria con cobertura nacional'),
('Bancolombia', 'Banco colombiano con operaciones internacionales'),
('Davivienda', 'Banco enfocado en soluciones de vivienda y crédito'),
('Banco Popular', 'Entidad enfocada en créditos de consumo'),
('Banco Agrario', 'Banco especializado en el sector agropecuario'),
('Scotiabank Colpatria', 'Banco internacional con presencia en Colombia'),
('BBVA Colombia', 'Sucursal del banco multinacional BBVA'),
('Banco Itaú', 'Entidad de origen brasileño con operaciones en Colombia');
