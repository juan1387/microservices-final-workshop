package org.example.cuentaservice.repository;

import org.example.cuentaservice.model.Cuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICuentaRepository extends ReactiveCrudRepository<Cuenta,Long> {
}
