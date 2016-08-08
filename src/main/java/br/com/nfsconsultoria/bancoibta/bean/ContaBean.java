package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.ContaDAO;
import br.com.nfsconsultoria.bancoibta.dao.PessoaDAO;
import br.com.nfsconsultoria.bancoibta.domain.Conta;
import br.com.nfsconsultoria.bancoibta.domain.Pessoa;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

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
public class ContaBean {

    private Conta conta;
    private List<Conta> contas;
    private List<Pessoa> pessoas;
    private Double valor;

    public ContaBean() {
        ContaDAO contaDAO = new ContaDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        this.contas = contaDAO.listar();
        this.pessoas = pessoaDAO.listarLazy("pessoas");
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void listar() {
        try {
            ContaDAO contaDAO = new ContaDAO();
            this.contas = contaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar a conta");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.conta = new Conta();
    }

    public void salvar() {
        try {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.merge(conta);
            listar();
            novo();
            Messages.addGlobalInfo("Conta salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar salvar conta");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {

        try {
            conta = (Conta) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            conta = (Conta) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.excluir(conta);
            listar();
            Messages.addGlobalInfo("Conta excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir conta");
            erro.printStackTrace();
        }
    }

    public void sacar() {

        try {
            ContaDAO contaDAO = new ContaDAO();
            if (valor != null && valor > 0) {

                if (valor <= conta.getSaldo()) {
                    conta.setSaldo(conta.getSaldo() - valor);
                    contaDAO.merge(conta);
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
                    + " ao tentar editar conta");
            erro.printStackTrace();
        }
    }

    public void depositar() {

        try {
            ContaDAO contaDAO = new ContaDAO();
            if (valor != null && valor > 0) {
                conta.setSaldo(conta.getSaldo() + valor);
                contaDAO.merge(conta);
                this.valor = null;
                listar();
            } else {
                Messages.addGlobalError("Depósito inválido");
                listar();
                this.valor = null;
            }

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta");
            erro.printStackTrace();
        }
    }

    public void reajuste(ActionEvent evento) {

        try {
            conta = (Conta) evento.getComponent().getAttributes().get("poupaSelecionada");
            if (conta.getTipo().equals("Conta Poupança")) {
                valor = this.conta.getSaldo() * (1 + 0.02);
                ContaDAO contaDAO = new ContaDAO();
                conta.setSaldo(valor);
                contaDAO.merge(conta);
                this.valor = null;
                listar();
            } else {
                Messages.addGlobalError("Esta conta não pode ser reajustada");
            }
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar reajustar a conta");
            erro.printStackTrace();
        }
    }

    public void exibeLimite() {
        try {
            SelectOneMenu menu = (SelectOneMenu) Faces.getViewRoot()
                    .findComponent("formCadastro:tipo");
            InputText txtLimite = (InputText) Faces.getViewRoot()
                    .findComponent("formCadastro:limite");
            if (menu.getValue().equals("Conta Poupança")) {
                txtLimite.setValue(0D);
                txtLimite.setDisabled(true);
                txtLimite.setRequired(false);
            } else {
                txtLimite.setValue("");
                txtLimite.setDisabled(false);
                txtLimite.setRequired(true);
            }
        } catch (RuntimeException erro) {
            erro.printStackTrace();
        }
    }
}
