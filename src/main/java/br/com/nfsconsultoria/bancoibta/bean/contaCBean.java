package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.ContaCorrenteDAO;
import br.com.nfsconsultoria.bancoibta.domain.ContaCorrente;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luissantos on 07/06/16.
 * @author luissantos
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ContaCBean implements Serializable {

    private ContaCorrente corrente;
    private List<ContaCorrente> correntes;
    private Double valor;

    public ContaCBean() {
        ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
        this.correntes = contaDAO.listar();
    }

    public ContaCorrente getCorrente() {
        return corrente;
    }

    public void setCorrente(ContaCorrente corrente) {
        this.corrente = corrente;
    }

    public List<ContaCorrente> getCorrentes() {
        return correntes;
    }

    public void setCorrentes(List<ContaCorrente> correntes) {
        this.correntes = correntes;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void listar() {
        try {
            ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
            this.correntes = contaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar conta corrente");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.corrente = new ContaCorrente();
    }

    public void salvar() {
        try {
            ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
            contaDAO.merge(corrente);
            listar();
            novo();
            Messages.addGlobalInfo("Conta corrente salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar salvar conta corrente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {

        try {
            listar();
            corrente = (ContaCorrente) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta corrente");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            corrente = (ContaCorrente) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
            ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
            contaDAO.excluir(corrente);
            listar();
            novo();
            Messages.addGlobalInfo("Conta corrente excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir conta corrente");
            erro.printStackTrace();
        }
    }

    public void sacar() {

        try {
            ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
            if (valor != null && valor > 0) {

                if (valor <= corrente.getSaldo()) {
                    corrente.setSaldo(corrente.getSaldo() - valor);
                    contaDAO.merge(corrente);
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
            ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
            if (valor != null && valor > 0) {
                corrente.setSaldo(corrente.getSaldo() + valor);
                contaDAO.merge(corrente);
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
}
