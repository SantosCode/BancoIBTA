package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.contaPoupancaDAO;
import br.com.nfsconsultoria.bancoibta.domain.contaPoupanca;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by luis on 07/06/16.
 */
@ManagedBean
@ViewScoped
public class contaPBean {

    private contaPoupanca cPoupanca;
    private List<contaPoupanca> cPoupancas;

    public contaPBean() {
        contaPoupancaDAO contaDAO = new contaPoupancaDAO();
        this.cPoupancas = contaDAO.listar();
    }

    public contaPoupanca getcCorrente() {
        return cPoupanca;
    }

    public void setcPoupanca(contaPoupanca cPoupanca) {
        this.cPoupanca = cPoupanca;
    }

    public List<contaPoupanca> getcPoupancas() {
        return cPoupancas;
    }

    public void setcPoupancas(List<contaPoupanca> cPoupancas) {
        this.cPoupancas = cPoupancas;
    }

    @PostConstruct
    public void listar() {
        try {
            contaPoupancaDAO contaDAO = new contaPoupancaDAO();
            this.cPoupancas = contaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar conta poupança");
            erro.printStackTrace();
        }
    }

    public void novo() {
        this.cPoupanca = new contaPoupanca();
    }

    public void salvar() {
        try {
            contaPoupancaDAO contaDAO = new contaPoupancaDAO();
            contaDAO.merge(cPoupanca);
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
            cPoupanca = (contaPoupanca) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar editar conta poupança");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            cPoupanca = (contaPoupanca) evento.getComponent().getAttributes()
                    .get("contaSelecionada");
            contaPoupancaDAO contaDAO = new contaPoupancaDAO();
            contaDAO.excluir(cPoupanca);
            listar();
            novo();
            Messages.addGlobalInfo("Conta poupanca excluida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar excluir conta poupança");
            erro.printStackTrace();
        }
    }
}
