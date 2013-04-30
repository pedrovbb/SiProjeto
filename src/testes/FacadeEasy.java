package testes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import outrasClases.ID;


import exception.AtributoException;
import exception.CadastraException;
import exception.CamposException;
import exception.LoginException;
import exception.LoginInexistenteException;
import exception.PostException;
import exception.PostSomInvalidException;
import exception.RegraDeComposicaoInexistente;
import exception.RegraDeComposicaoInvalida;
import exception.SessaoException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;
import exception.UsuarioInexistenteException;
import principal.Sistema;

public class FacadeEasy {

	private Sistema sistema;
	
	// ---------------- US01 -----------------
	public FacadeEasy(){
		sistema = new Sistema();
	}
	
	public void zerarSistema(){
		sistema.zerarSistema();
	}

	public void criarUsuario(String login, String senha, String nome, String email)
			throws CamposException, CadastraException{
		sistema.criarUsuario(login, senha, nome, email);
	}

	public String abrirSessao(String login, String senha) 
			throws UsuarioInexistenteException, CamposException, LoginInexistenteException{
		return sistema.abrirSessao(login, senha).toString();
	}
	
	public String getAtributoUsuario(String login, String atributo)
			throws AtributoException, CamposException, UsuarioInexistenteException{
		return sistema.getAtributoUsuario(login, atributo);
	}

	public String getPerfilMusical(String idSessao) throws SessaoException{
		return converte(sistema.getPerfilMusical(new ID(idSessao)));
	}
	
	public String postarSom(String idSessao, String link, String dataCriacao) 
			throws PostException, SessaoException, CamposException{
		return sistema.postarSom(idSessao, link, dataCriacao);
	}
	
	public String getAtributoSom(String idSom, String atributo) 
			throws AtributoException, PostException{
		return sistema.getAtributoSom(idSom, atributo);
	}
	
	public void encerrarSessao(String login) 
			throws UsuarioInexistenteException, CamposException, LoginInexistenteException {
		sistema.encerrarSessao(login);
	}
	
	public void encerrarSistema(){
		sistema.encerrarSistema();
	}
	
	// ----------------- US02 ------------------
	public String getIDUsuario(String idSessao) throws SessaoException{
		return sistema.getIDUsuario(idSessao).toString();
	}
	
	public int  getNumeroDeSeguidores(String idSessao) throws SessaoException{
		return sistema.getNumeroDeSeguidores(idSessao);
	}
	
	public void seguirUsuario(String idSessao, String login) 
			throws CamposException, LoginException, SessaoException, PostSomInvalidException, UsuarioInexistenteException {
		sistema.seguirUsuario(idSessao, login);
	}
	
	public String getFontesDeSons(String idSessao) throws SessaoException {
		return converte(sistema.getFontesDeSons(idSessao));
	}
	
	public String getListaDeSeguidores(String idSessao) throws SessaoException {
		return converte(sistema.getListaDeSeguidores(idSessao));
	}
	
	public String getVisaoDosSons(String idSessao) throws SessaoException {
		return converte(sistema.getVisaoDosSons(idSessao));
	}

	public String getSonsFavoritos(String idSessao) throws SessaoException {
		return converte(sistema.getSonsFavoritos(idSessao));
	}
	
	public String getFeedExtra(String idSessao) throws SessaoException {
		return converte(sistema.getFeedExtra(idSessao));
	}
	
	public void favoritarSom(String idSessao, String idSom) throws PostException, SessaoException{
		sistema.favoritarSom(idSessao, idSom);
	}
//-----------------US5-------------------/
	public String getFirstCompositionRule(){
		return "PRIMEIRO OS SONS POSTADOS MAIS RECENTEMENTE PELAS FONTES DE SONS";
	}
	public String getSecondCompositionRule(){
		return "PRIMEIRO OS SONS COM MAIS FAVORITOS";
	}
	
	public String getThirdCompositionRule(){
		return "PRIMEIRO SONS DE FONTES DAS QUAIS FAVORITEI SONS NO PASSADO";
	}
	public String getMainFeed(String idSessao) throws SessaoInvalidaException, SessaoInexistenteException{
		return converte(sistema.getMainFeed(idSessao));
	}
	
	public void setMainFeedRule(String idSessao, String regra) 
			throws SessaoInvalidaException, SessaoInexistenteException, RegraDeComposicaoInvalida, RegraDeComposicaoInexistente {
		sistema.setMainFeedRule(idSessao, regra);
	}
	
	// ---------------- AUXILIARES ------------------
	
	private String converte(List<ID> list) {
		List<String> perfil = new ArrayList<String>();
		Iterator<ID> it = list.iterator();
		while (it.hasNext()) {
			ID id = (ID) it.next();
			perfil.add(id.toString());
		}
		return perfil.toString().replace("[", "{").replace("]", "}").replace(" ", "");
	}
}
