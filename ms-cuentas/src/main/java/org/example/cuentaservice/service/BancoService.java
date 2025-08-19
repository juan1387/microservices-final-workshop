package org.example.cuentaservice.service;

import org.example.cuentaservice.DTO.Banco;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BancoService {
    private final WebClient webClient;

    public BancoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Banco> getBancoById(Long idbanco){
        System.out.println("Ingreso a consultar banco: "+ idbanco);
        return  webClient.get()
                .uri("/api/banco/{idbanco}", idbanco)
                .retrieve()
                .bodyToMono(Banco.class);
    }
}
