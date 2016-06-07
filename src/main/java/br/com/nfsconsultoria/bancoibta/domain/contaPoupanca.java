package br.com.nfsconsultoria.bancoibta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luissantos on 06/06/16.
 */
@SuppressWarnings("serial")
@Entity
public class contaPoupanca extends GenericDomain {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 15)
    private String cpf;

    @Column(nullable = false, length = 45)
    private Long conta;

    @Column(nullable = false, length = 45)
    private Float saldo;

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

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
