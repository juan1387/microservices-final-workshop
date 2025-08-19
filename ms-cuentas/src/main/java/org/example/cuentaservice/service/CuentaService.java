package org.example.cuentaservice.service;

import org.example.cuentaservice.DTO.Banco;
import org.example.cuentaservice.model.Cuenta;
import org.example.cuentaservice.repository.ICuentaRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CuentaService {
    private final DatabaseClient db;
    private final BancoService bancoService;
    private final ICuentaRepository iCuentaRepository;

    public CuentaService(DatabaseClient db, BancoService bancoService, ICuentaRepository iCuentaRepository) {
        this.db = db;
        this.bancoService = bancoService;
        this.iCuentaRepository = iCuentaRepository;
    }

    public Mono<Cuenta> crearCuenta(Cuenta cuenta){
      return  bancoService.getBancoById(cuenta.getIdbanco()).flatMap(banco -> {
          System.out.println("El banoc es: "+banco );
          return db.sql("SELECT NEXT VALUE FOR seq_nro_cuenta AS seq") // H2 (en Postgres: SELECT nextval('seq_nro_cuenta') AS seq)
                  .map(row -> row.get("seq", Long.class))
                  .one()
                  .flatMap(seq -> {
                      Cuenta micuenta = new Cuenta();
                      micuenta.setNroCuenta(seq.toString());
                      micuenta.setIdbanco(banco.id());
                      micuenta.setTitular(cuenta.getTitular());
                      return iCuentaRepository.save(micuenta);
                  });

        });
    }

    public Mono<Cuenta> actualizarCuenta(Cuenta cuenta){
        return iCuentaRepository.save(cuenta);
    }

    public Flux<Cuenta> obtenerlasCuentas(){
        return iCuentaRepository.findAll();
    }

    public Mono<Cuenta> cuentporId(Long idcuenta){
        return iCuentaRepository.findById(idcuenta);
    }

    public Mono<Banco> bancoporId(Long idBanco){
       return bancoService.getBancoById(idBanco);
    }
}
