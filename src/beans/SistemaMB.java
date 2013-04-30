package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import exception.RegraDeComposicaoInexistente;
import exception.RegraDeComposicaoInvalida;
import exception.SessaoException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;

import outrasClases.ID;
import outrasClases.Som;
import outrasClases.Util_ID;

import principal.Sistema;

@ManagedBean
@ViewScoped
public class SistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sistema sistema;
	private String regra;

	public SistemaMB() {
		this.sistema = ObjetoSistema.getSistema();
		this.regra = "PRIMEIRO OS SONS POSTADOS MAIS RECENTEMENTE PELAS FONTES DE SONS";
	}
	
	public List<Som> getSonsPostados() throws SessaoException{
		this.sistema = ObjetoSistema.getSistema();
		// TODO mensagem de erro explode na cara do usuario
        String sessionID = Util_ID.getCurrentID_SessionJSF();
        List<ID> idSons = sistema.getPerfilMusical(sessionID);
        List<Som> sonsFeed = sistema.getSonsPostados(idSons);
        ObjetoSistema.setSistema(this.sistema);
		return sonsFeed;
	}
	
	public List<Som> getSonsExtras() 
			throws SessaoInvalidaException, RegraDeComposicaoInvalida, RegraDeComposicaoInexistente{
		this.sistema = ObjetoSistema.getSistema();
		// TODO mensagem de erro explode na cara do usuario
        String sessionID = Util_ID.getCurrentID_SessionJSF();
        try {
			sistema.setMainFeedRule(sessionID, regra);
			List<ID> idSons = sistema.getMainFeed(sessionID);
			List<Som> sonsFeed = sistema.getSonsPostados(idSons);
			ObjetoSistema.setSistema(this.sistema);
			return sonsFeed;
		} catch (SessaoInexistenteException e) {
		}
        return null;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}
	
	public String reset(){
		new SistemaMB();
		return "index";
	}
	
}
