package org.example.cuentaservice.DTO;

import java.time.LocalDateTime;

public record Transacciones(Long id, Long cuentaorigen,Long cuentadestino,String tipo,Long monto,Long impuesto,LocalDateTime fechatransaccion) {


}
