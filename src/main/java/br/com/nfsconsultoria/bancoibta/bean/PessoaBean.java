package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.PessoaDAO;
import br.com.nfsconsultoria.bancoibta.domain.Pessoa;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by luissantos on 30/06/16.
 */
@ManagedBean
@SessionScoped
public class PessoaBean {

    private Pessoa pessoa;
    private List<Pessoa> pessoas;

    public PessoaBean() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        this.pessoas = pessoaDAO.listar();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void listar() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            this.pessoas = pessoaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar listar cliente");
            erro.printStackTrace();
        }
    }

    public void novo() {
        pessoa = new Pessoa();
    }

    public void salvar() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.merge(pessoa);
            Messages.addGlobalInfo("Cliente salva com suceso");
            listar();
            novo();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
                    + " ao tentar salvar cliente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            pessoa = (Pessoa) evento.getComponent().getAttributes()
                    .get("pessoaSelecionada");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar selecionar cliente");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoa = (Pessoa) evento.getComponent().getAttributes()
                    .get("pessoaSelecionada");
            pessoaDAO.excluir(pessoa);
            listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocoreu o erro " + erro.getMessage()
                    + " ao tentar excluir cliente");
            erro.printStackTrace();
        }
    }
}
