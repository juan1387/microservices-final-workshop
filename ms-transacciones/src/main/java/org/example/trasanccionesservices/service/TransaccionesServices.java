package org.example.trasanccionesservices.service;

import org.example.trasanccionesservices.model.Transacciones;
import org.example.trasanccionesservices.repository.ItransaccionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransaccionesServices {
    private  final ItransaccionRepository repotransacciones;

    public TransaccionesServices(ItransaccionRepository repotransacciones) {
        this.repotransacciones = repotransacciones;
    }

    public Mono<Transacciones> guardarTransaccion(Transacciones transaccion){
        return repotransacciones.save(transaccion);
    }

    public Flux<Transacciones> obtenerTrasacciones(){
        return repotransacciones.findAll();
    }
}
