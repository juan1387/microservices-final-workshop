CREATE TABLE IF NOT EXISTS transacciones (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  cuentaorigen      BIGINT,
  cuentadestino     BIGINT,
  tipo              VARCHAR(50),
  monto             BIGINT,
  impuesto          BIGINT,
  fechatransaccion  TIMESTAMP
);