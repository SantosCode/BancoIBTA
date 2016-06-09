package br.com.nfsconsultoria.bancoibta.bean;

import br.com.nfsconsultoria.bancoibta.dao.contaCorrenteDAO;
import br.com.nfsconsultoria.bancoibta.domain.contaCorrente;
import org.omnifaces.util.Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luis on 07/06/16.
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class contaCBean implements Serializable {

	private contaCorrente cCorrente;
	private List<contaCorrente> cCorrentes;
	private Double valor;

	public contaCBean() {
		contaCorrenteDAO contaDAO = new contaCorrenteDAO();
		this.cCorrentes = contaDAO.listar();
	}

	public contaCorrente getcCorrente() {
		return cCorrente;
	}

	public void setcCorrente(contaCorrente cCorrente) {
		this.cCorrente = cCorrente;
	}

	public List<contaCorrente> getcCorrentes() {
		return cCorrentes;
	}

	public void setcCorrentes(List<contaCorrente> cCorrentes) {
		this.cCorrentes = cCorrentes;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void listar() {
		try {
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			this.cCorrentes = contaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
			+ " ao tentar listar conta corrente");
			erro.printStackTrace();
		}
	}

	public void novo() {
		this.cCorrente = new contaCorrente();
	}

	public void salvar() {
		try {
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			contaDAO.merge(cCorrente);
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
			cCorrente = (contaCorrente) evento.getComponent().getAttributes()
					.get("contaSelecionada");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
			+ " ao tentar editar conta corrente");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {
			cCorrente = (contaCorrente) evento.getComponent().getAttributes()
					.get("contaSelecionada");
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			contaDAO.excluir(cCorrente);
			listar();
			novo();
			Messages.addGlobalInfo("Conta corrente excluida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
			+ " ao tentar excluir conta corrente");
			erro.printStackTrace();
		}
	}

	public void sacar(ActionEvent evento) {

		try {
			listar();
			cCorrente = (contaCorrente) evento.getComponent().getAttributes()
					.get("contaSelecionada");
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			if (valor <= cCorrente.getSaldo()) {
				cCorrente.setSaldo(cCorrente.getSaldo() - valor);
				contaDAO.merge(cCorrente);
			} else {
				Messages.addGlobalError("Saldo insuficiente");
			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage()
			+ " ao tentar editar conta corrente");
			erro.printStackTrace();
		}
	}
}
