package br.com.nfsconsultoria.bancoibta.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by luissantos on 29/06/16.
 */
@Entity
public class Pessoa extends GenericDomain {
    @Column(length = 45, nullable = false)
    private String nome;

    @Column(unique = true, nullable = false, length = 15)
    private String cpf;

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
}
