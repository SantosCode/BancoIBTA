package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.ContaPoupancaDAO;
import br.com.nfsconsultoria.bancoibta.domain.ContaPoupanca;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by luissantos on 07/06/16.
 *
 * @author luissantos
 */
@ManagedBean
@SessionScoped
public class ContaPBean {

    private ContaPoupanca poupanca;
    private List<ContaPoupanca> poupancas;
    private Double valor;

    public ContaPBean() {
        ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
        this.poupancas = contaDAO.listar();
    }

    public ContaPoupanca getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(ContaPoupanca poupanca) {
        this.poupanca = poupanca;
    }

    public List<ContaPoupanca> getPoupancas() {
        return poupancas;
    }

    public void setPoupancas(List<ContaPoupanca> poupancas) {
        this.poupancas = poupancas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void listar() {
        try {
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            this.poupancas = contaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar conta poupança");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.poupanca = new ContaPoupanca();
    }

    public void salvar() {
        try {
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            contaDAO.merge(poupanca);
            listar();
            novo();
            Messages.addGlobalInfo("Conta poupança salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar salvar conta poupança");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {

        try {
            listar();
            poupanca = (ContaPoupanca) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta poupança");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            poupanca = (ContaPoupanca) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            contaDAO.excluir(poupanca);
            listar();
            novo();
            Messages.addGlobalInfo("Conta poupanca excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir conta poupança");
            erro.printStackTrace();
        }
    }

    public void sacar() {

        try {
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            if (valor != null && valor > 0) {

                if (valor <= poupanca.getSaldo()) {
                    poupanca.setSaldo(poupanca.getSaldo() - valor);
                    contaDAO.merge(poupanca);
                    this.valor = null;
                    listar();
                } else {
                    Messages.addGlobalError("Saldo insuficiente");
                    listar();
                    this.valor = null;
                }
            } else {
                Messages.addGlobalError("Valor incorreto");
            }

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta corrente");
            erro.printStackTrace();
        }
    }

    public void depositar() {

        try {
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            if (valor != null && valor > 0) {
                poupanca.setSaldo(poupanca.getSaldo() + valor);
                contaDAO.merge(poupanca);
                this.valor = null;
                listar();
            } else {
                Messages.addGlobalError("Deposito inválido");
                listar();
                this.valor = null;
            }

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta corrente");
            erro.printStackTrace();
        }
    }

    public void reajuste(ActionEvent evento) {

        try {
            poupanca = (ContaPoupanca) evento.getComponent().getAttributes().get("contaSelecionada");
            valor = this.poupanca.getSaldo() * (1 + 0.02);
            ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
            poupanca.setSaldo(valor);
            contaDAO.merge(poupanca);
            this.valor = null;
            listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta corrente");
            erro.printStackTrace();
        }
    }
}
