package org.example.bancoservices.controller;

import org.example.bancoservices.model.Banco;
import org.example.bancoservices.service.BancoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/banco")
public class BancoController {
    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping
    public Flux<Banco> getAll(){
        return bancoService.getAll();
    }

    @GetMapping("/{idBanco}")
    public Mono<Banco> getById(@PathVariable Long idBanco){

        return bancoService.getById(idBanco);
    }

    @PostMapping("/crearbanco")
    public Mono<ResponseEntity<Banco>> createBanco(@RequestBody Banco banco){
        return bancoService.createBanco(banco).map(resBanco -> {
            return ResponseEntity.status(200).body(resBanco);
        });
    }

    @PostMapping("/{idbanco}/updatebanco")
    public Mono<ResponseEntity<Banco>> updateBanco(@PathVariable Long idbanco, @RequestBody Banco banco){

       return bancoService.getById(idbanco)
               .switchIfEmpty(Mono.error(new RuntimeException("El item mencionado no existe")))
               .flatMap(resbanco ->{
                Banco bancActualizado = new Banco(resbanco.getId(),banco.getNombre(),banco.getDescripcion());
                return bancoService.createBanco(bancActualizado);
                }).map(mibanco ->{
                    return ResponseEntity.status(200).body(mibanco);
               });
    }

}
