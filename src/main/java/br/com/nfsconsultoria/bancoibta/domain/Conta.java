package br.com.nfsconsultoria.bancoibta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by luissantos on 29/06/16.
 */
@Entity
public class Conta extends GenericDomain {

    @Column(nullable = false, length = 45)
    private String tipo;

    @Column(nullable = false, length = 45, unique = true)
    private String conta;

    @Column(nullable = false, length = 45)
    private Double saldo;

    @Column(nullable = false, length = 45)
    private Double limite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pessoa titular;

    @ManyToOne
    @JoinColumn
    private Pessoa adicional;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public Pessoa getAdicional() {
        return adicional;
    }

    public void setAdicional(Pessoa adicional) {
        this.adicional = adicional;
    }
}
