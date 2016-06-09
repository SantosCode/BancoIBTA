package br.com.nfsconsultoria.bancoibta.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.nfsconsultoria.bancoibta.dao.contaCorrenteDAO;
import br.com.nfsconsultoria.bancoibta.dao.contaPoupancaDAO;
import br.com.nfsconsultoria.bancoibta.domain.contaCorrente;
import br.com.nfsconsultoria.bancoibta.domain.contaPoupanca;

/**
 * Created by luis on 07/06/16.
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class saqueBean implements Serializable {

	private contaCorrente cCorrente;
	private List<contaCorrente> cCorrentes;
	private contaPoupanca cPoupanca;
	private List<contaPoupanca> cPoupancas;
	private Double valor;
	private Long codigo;

	public saqueBean() {
		contaCorrenteDAO contaDAO = new contaCorrenteDAO();
		contaPoupancaDAO contaPDAO = new contaPoupancaDAO();
		this.cCorrentes = contaDAO.listar();
		this.cPoupancas = contaPDAO.listar();
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

	public contaPoupanca getcPoupanca() {
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void listar() {
		try {
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			contaPoupancaDAO contaPDAO = new contaPoupancaDAO();
			this.cCorrentes = contaDAO.listar();
			this.cPoupancas = contaPDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar listar conta corrente");
			erro.printStackTrace();
		}
	}

	public void novo() {
		this.cCorrente = new contaCorrente();
		this.cPoupanca = new contaPoupanca();
	}

	public void saqueCorrente() {
		try {
			contaCorrenteDAO contaDAO = new contaCorrenteDAO();
			cCorrente = contaDAO.buscar(codigo);
			cCorrente.setSaldo(cCorrente.getSaldo() - valor);
			contaDAO.merge(cCorrente);
			novo();
			listar();
			Messages.addGlobalInfo("Saque efetuado com suceso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu o erro " + erro.getMessage() + " ao tentar sacar");
		}
	}

	public void saquePoupanca() {
		try {
			contaPoupancaDAO contaPDAO = new contaPoupancaDAO();
			cPoupanca = contaPDAO.buscar(codigo);
			if (cPoupanca.getSaldo() > valor) {
				cPoupanca.setSaldo(cPoupanca.getSaldo() - valor);
			} else {
				Messages.addGlobalError("Saldo insulficiente");
			}
			Messages.addGlobalInfo("Saque efetuado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocooreu o erro " + erro.getMessage() + " ao tentar sacar");
		}
	}
}
