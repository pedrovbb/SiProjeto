package beans;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import principal.Sistema;

@ManagedBean
@ViewScoped
public class LoginCadastroMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sistema sistema;
	private String campoLogin, campoSenha, campoNome, campoEmail;

	public LoginCadastroMB() {
		sistema = ObjetoSistema.getSistema();
	}
	
	public Sistema getSistema() {
		return sistema;
	}

	public String getCampoLogin() {
		return campoLogin;
	}

	public void setCampoLogin(String campoLogin) {
		this.campoLogin = campoLogin;
	}

	public String getCampoSenha() {
		return campoSenha;
	}

	public void setCampoSenha(String campoSenha) {
		this.campoSenha = campoSenha;
	}

	public String getCampoNome() {
		return campoNome;
	}

	public void setCampoNome(String campoNome) {
		this.campoNome = campoNome;
	}

	public String getCampoEmail() {
		return campoEmail;
	}

	public void setCampoEmail(String campEmail) {
		this.campoEmail = campEmail;
	}

	public String doEfetuarLogin() {
		String result = null;
		try {
			sistema.abrirSessao(campoLogin, campoSenha);
			result =  "principal";
		}catch(Exception e){
			addMemsagem("Erro ao logar: " + e.getMessage());
		}
		reiniciaCampos();
		ObjetoSistema.setSistema(this.sistema);
		return result;
	}

	/**
	 * Adiciona uma mensagem de erro na interface
	 * 
	 * @param titulo
	 * @param message
	 */
	private void addMemsagem(String message) {
		// Obtem a instância atual do FacesContext e adiciona a mensagem de erro nele.
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(message));
	}

	public String efetuarCadastro() {
		String result = null;
		try {
			sistema.criarUsuario(campoLogin, campoSenha, campoNome, campoEmail);
			doEfetuarLogin();
			result = "principal";
		} catch (Exception e) {
			addMemsagem("Erro cadastrar novo usuário..." + e.getMessage());
		}
		reiniciaCampos();
		ObjetoSistema.setSistema(this.sistema);
		return result;
	}
	
	public String reiniciar() {
		reiniciaCampos();
		return "index";
	}

	private void reiniciaCampos() {
		campoLogin = null;
		campoSenha = null;
		campoNome = null;
		campoEmail = null;
	}
}
