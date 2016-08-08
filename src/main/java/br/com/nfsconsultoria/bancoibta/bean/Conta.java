/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfsconsultoria.bancoibta.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luissantos
 */
@Entity
@Table(name = "Conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByCodigo", query = "SELECT c FROM Conta c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Conta.findByConta", query = "SELECT c FROM Conta c WHERE c.conta = :conta"),
    @NamedQuery(name = "Conta.findByLimite", query = "SELECT c FROM Conta c WHERE c.limite = :limite"),
    @NamedQuery(name = "Conta.findBySaldo", query = "SELECT c FROM Conta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Conta.findByTipo", query = "SELECT c FROM Conta c WHERE c.tipo = :tipo")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "conta")
    private String conta;
    @Basic(optional = false)
    @Column(name = "limite")
    private double limite;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "titular_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Pessoa titularCodigo;
    @JoinColumn(name = "adicional_codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Pessoa adicionalCodigo;

    public Conta() {
    }

    public Conta(Long codigo) {
        this.codigo = codigo;
    }

    public Conta(Long codigo, String conta, double limite, double saldo, String tipo) {
        this.codigo = codigo;
        this.conta = conta;
        this.limite = limite;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getTitularCodigo() {
        return titularCodigo;
    }

    public void setTitularCodigo(Pessoa titularCodigo) {
        this.titularCodigo = titularCodigo;
    }

    public Pessoa getAdicionalCodigo() {
        return adicionalCodigo;
    }

    public void setAdicionalCodigo(Pessoa adicionalCodigo) {
        this.adicionalCodigo = adicionalCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.nfsconsultoria.bancoibta.bean.Conta[ codigo=" + codigo + " ]";
    }
    
}
