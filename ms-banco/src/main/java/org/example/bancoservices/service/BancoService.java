package org.example.bancoservices.service;

import org.example.bancoservices.model.Banco;
import org.example.bancoservices.repository.IBancoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BancoService {
    private final IBancoRepository iBancoRepository;

    public BancoService(IBancoRepository iBancoRepository) {
        this.iBancoRepository = iBancoRepository;
    }

    public Flux<Banco> getAll(){
        return iBancoRepository.findAll();
    }

    public Mono<Banco> getById(Long id){
        return iBancoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Banco no encontrado")));
    }

    public Mono<Banco> createBanco(Banco banco){
        return iBancoRepository.save(banco);
    }
}
