package org.example.trasanccionesservices.repository;

import org.example.trasanccionesservices.model.Transacciones;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItransaccionRepository extends ReactiveCrudRepository<Transacciones,Long> {

    Flux<Transacciones> findByCuentaorigen(Long cuentaorigen);
}
