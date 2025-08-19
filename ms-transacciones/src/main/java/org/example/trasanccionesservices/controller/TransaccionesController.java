package org.example.trasanccionesservices.controller;

import org.example.trasanccionesservices.model.Transacciones;
import org.example.trasanccionesservices.service.TransaccionesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionesController {
    private final TransaccionesServices servitrans;

    public TransaccionesController(TransaccionesServices servitrans) {
        this.servitrans = servitrans;
    }

    @PostMapping
    public Mono<ResponseEntity<Transacciones>> creaTrasaccion(@RequestBody Transacciones transaccion){
        return servitrans.guardarTransaccion(transaccion).map(trans->{
            return ResponseEntity.ok(trans);
        });
    }

    @GetMapping
    public Flux<Transacciones> obtenerTransacciones(){
        return servitrans.obtenerTrasacciones();
    }
}
