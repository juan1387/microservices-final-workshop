package org.example.bancoservices.repository;

import org.example.bancoservices.model.Banco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface  IBancoRepository extends ReactiveCrudRepository<Banco,Long > {
}
