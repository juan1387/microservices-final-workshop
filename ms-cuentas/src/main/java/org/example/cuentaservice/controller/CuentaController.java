package org.example.cuentaservice.controller;

import org.example.cuentaservice.DTO.Transacciones;
import org.example.cuentaservice.grpc.ListTransaccionesResponse;
import org.example.cuentaservice.grpc.TransaccionesResponse;
import org.example.cuentaservice.grpc.TrasaccioneConsumer;
import org.example.cuentaservice.model.Cuenta;
import org.example.cuentaservice.service.BancoService;
import org.example.cuentaservice.service.CuentaService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {


   private final CuentaService cuentaService;
   private final TrasaccioneConsumer grpcConsumer;


    public CuentaController(CuentaService cuentaService, TrasaccioneConsumer grpcConsumer) {
        this.cuentaService = cuentaService;

        this.grpcConsumer = grpcConsumer;
    }

    @PostMapping
    public Mono<ResponseEntity<Cuenta>> createCuenta(@RequestBody Cuenta cuenta){
        return  cuentaService.crearCuenta(cuenta).map(cuent ->{
            return ResponseEntity.ok(cuent);
        });

    }

    @GetMapping
    public Flux<Cuenta> obtenerCuentas(){
        return cuentaService.obtenerlasCuentas();

    }

    @GetMapping("/transa/{numcuenta}")
    public Mono<ResponseEntity<Flux<Transacciones>>> getTransacciones(@PathVariable Long numcuenta){
        List<TransaccionesResponse> list =  grpcConsumer.getTransacciones(numcuenta).getTransaccionesList();
        return Mono.just(ResponseEntity.ok().body(Flux.fromIterable(list).map(item->{


            List<Transacciones> reslista = new ArrayList<>();
            Transacciones tra = new Transacciones(item.getId(),item.getCuentaorigen(),item.getCuentadestino(),item.getTipo(),item.getValortransaccion(),item.getImpuesto(),LocalDateTime.parse(item.getFecha()));


            return tra;

        })));



    }


    @PostMapping("/{idcuenta}/updatecuenta")
    public Mono<ResponseEntity<Cuenta>> updateBanco(@PathVariable Long idcuenta, @RequestBody Cuenta cuenta){

        return cuentaService.cuentporId(idcuenta)
                .switchIfEmpty(Mono.error(new RuntimeException("El item mencionado no existe")))
                .flatMap(resCuenta ->{
                   return cuentaService.bancoporId(cuenta.getIdbanco())
                            .switchIfEmpty(Mono.error(new RuntimeException("el Banco no existe")))
                            .flatMap(banFull ->{
                                Cuenta cuentaUpdate = new Cuenta();
                                cuentaUpdate.setId(idcuenta);
                                cuentaUpdate.setIdbanco(cuenta.getIdbanco());
                                cuentaUpdate.setTitular(cuenta.getTitular());
                                cuentaUpdate.setNroCuenta(resCuenta.getNroCuenta());
                                return cuentaService.actualizarCuenta(cuentaUpdate);
                            });

                }).map(mibanco ->{
                    return ResponseEntity.status(200).body(mibanco);
                });
    }}
