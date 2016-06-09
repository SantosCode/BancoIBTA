package br.com.nfsconsultoria.bancoibta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by luissantos on 06/06/16.
 * @author luissantos
 */
@SuppressWarnings("serial")
@Entity
public class ContaPoupanca extends GenericDomain implements Serializable {

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @Column(nullable = false, length = 45)
    private String conta;

    @Column(nullable = false, length = 45)
    private Double saldo;

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
}
