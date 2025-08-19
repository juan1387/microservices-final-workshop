package org.example.cuentaservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("cuenta")
public class Cuenta {
    @Id
    private Long id;
    private Long idbanco;
    private String nrocuenta;
    private String titular;

    public Cuenta(Long id, Long idbanco, String nroCuenta, String titular) {
        this.id = id;
        this.idbanco = idbanco;
        this.nrocuenta = nroCuenta;
        this.titular = titular;
    }

    public Cuenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Long idbanco) {
        this.idbanco = idbanco;
    }

    public String getNroCuenta() {
        return nrocuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nrocuenta = nroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
