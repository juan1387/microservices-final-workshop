package org.example.trasanccionesservices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("transacciones")
public class Transacciones {
    @Id
    private Long id;
    private Long cuentaorigen;
    private Long cuentadestino;
    private String tipo;
    private Long monto;
    private Long impuesto;
    private LocalDateTime fechatransaccion;

    public Transacciones() {
    }

    public Transacciones(Long id, Long cuentaorigen, Long cuentadestino, String tipo, Long monto, Long impuesto, LocalDateTime fechatransaccion) {
        this.id = id;
        this.cuentaorigen = cuentaorigen;
        this.cuentadestino = cuentadestino;
        this.tipo = tipo;
        this.monto = monto;
        this.impuesto = impuesto;
        this.fechatransaccion = fechatransaccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuentaorigen() {
        return cuentaorigen;
    }

    public void setCuentaorigen(Long cuentaorigen) {
        this.cuentaorigen = cuentaorigen;
    }

    public Long getCuentadestino() {
        return cuentadestino;
    }

    public void setCuentadestino(Long cuentadestino) {
        this.cuentadestino = cuentadestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public Long getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Long impuesto) {
        this.impuesto = impuesto;
    }

    public LocalDateTime getFechatransaccion() {
        return fechatransaccion;
    }

    public void setFechatransaccion(LocalDateTime fechatransaccion) {
        this.fechatransaccion = fechatransaccion;
    }
}
