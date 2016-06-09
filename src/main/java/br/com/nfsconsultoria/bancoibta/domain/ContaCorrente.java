package br.com.nfsconsultoria.bancoibta.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luissantos on 06/06/16.
 */
@SuppressWarnings("serial")
@Entity
public class ContaCorrente extends GenericDomain implements Serializable {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @Column(nullable = false, length = 45)
    private String conta;

    @Column(nullable = false, length = 45)
    private Double saldo;

    @Column(nullable = false, length = 45)
    private Double limite;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
