package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import exception.SessaoException;

import outrasClases.ID;
import outrasClases.Som;
import outrasClases.Util_ID;

import principal.Sistema;

@ManagedBean
@SessionScoped
public class SistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sistema sistema;

	public SistemaMB() {
		this.sistema = ObjetoSistema.getSistema();
	}
	
	public List<Som> getSonsPostados() throws SessaoException{
		this.sistema = ObjetoSistema.getSistema();
		// TODO não sei para aonde vai a mensagem de erro
        String sessionID = Util_ID.getCurrentID_SessionJSF();
        List<ID> idSons = sistema.getPerfilMusical(sessionID);
        List<Som> sonsFeed = sistema.getSonsPostados(idSons);
        ObjetoSistema.setSistema(this.sistema);
        imprimeArray(sonsFeed);
        imprimeArray(idSons);
		return sonsFeed;
	}

	private void imprimeArray(List<?> sonsFeed) {
		String result = "";
		for (Object obj : sonsFeed) {
			result += obj.toString() + " ";
		}
		System.out.println(result.trim());
	}
	
}
