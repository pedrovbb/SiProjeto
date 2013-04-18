package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import outrasClases.Util_DATA;
import outrasClases.Util_ID;
import exception.CamposException;
import exception.PostException;
import exception.SessaoException;

import principal.Sistema;

@ManagedBean
@ViewScoped
public class PostarSomMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sistema sistema;
	private String linkSom;

	public PostarSomMB() {
		sistema = ObjetoSistema.getSistema();
	}
	
	public Sistema getSistema() {
		return sistema;
	}
	
	public void postarSom() throws PostException, SessaoException, CamposException{
		this.sistema = ObjetoSistema.getSistema();
		String ID_Sessao = Util_ID.getCurrentID_SessionJSF();
		String dataAtual = Util_DATA.getCurrentData();
		sistema.postarSom(ID_Sessao, getLinkSom(), dataAtual);
		ObjetoSistema.setSistema(this.sistema);
		linkSom = null;
	}

	public String getLinkSom() {
		return linkSom;
	}

	public void setLinkSom(String linkSom) {
		this.linkSom = linkSom;
	}
}
