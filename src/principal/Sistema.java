package principal;

import java.io.Serializable;
import java.util.List;

import outrasClases.ID;
import exception.AtributoException;
import exception.CadastraException;
import exception.CampoLoginException;
import exception.CamposException;
import exception.LoginException;
import exception.LoginInexistenteException;
import exception.PostException;
import exception.PostSomInexistenteException;
import exception.PostSomInvalidException;
import exception.SessaoException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;
import exception.UsuarioInexistenteException;
import gerenciadores.GerenciadorSessao;
import gerenciadores.GerenciadorSons;
import gerenciadores.GerenciadorUsuario;

public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciadorUsuario gerenciadorUsuario;
	private GerenciadorSons gerenciadorSons;
	private GerenciadorSessao gerenciadorSessao;
	
	/**
	 * Construtor
	 */
	public Sistema() {
		zerarSistema();
	}
	
	/**
	 * Zera o Sistema, reiniciado as variáveis.
	 */
	public void zerarSistema() {
		gerenciadorUsuario = new GerenciadorUsuario();
		gerenciadorSons = new GerenciadorSons();
		gerenciadorSessao = new GerenciadorSessao();
	}
	
	/**
	 * Encerra o Sistema
	 */
	public void encerrarSistema() {
		// TODO não sei o que fazer aqui 
	}
	
	/**
	 * Cadastra um novo usuário
	 * 
	 * @param login
	 * @param senha
	 * @param nome
	 * @param email
	 * @throws CadastraException 
	 * @throws CamposException 
	 */
	public void criarUsuario(String login, String senha, String nome, String email) 
			throws CamposException, CadastraException {
		gerenciadorUsuario.criarUsuario(login, senha, nome, email);
	}
	
	/**
	 * Inicia sessao do usuario
	 * 
	 * @param login
	 * @param senha
	 * @return idSessao
	 * @throws UsuarioInexistenteException
	 * @throws CamposException
	 * @throws LoginInexistenteException 
	 */
	public ID abrirSessao(String login, String senha) 
			throws UsuarioInexistenteException, CamposException, LoginInexistenteException {
		if (gerenciadorUsuario.verificaCadastro(login, senha)) {
			return gerenciadorSessao.abrirSessao(gerenciadorUsuario.getID_User(login));
		}
		// de acordo com testes do easy
		throw new CampoLoginException();
	}

	/**
	 * Retorna o atributo do usuário que possui o login
	 * 
	 * @param login
	 * @param atributo
	 * @return Atributo especificado
	 * @throws CamposException
	 * @throws AtributoException
	 * @throws UsuarioInexistenteException
	 */
	public String getAtributoUsuario(String login, String atributo) 
			throws CamposException, AtributoException, UsuarioInexistenteException {
		return gerenciadorUsuario.getAtributo(login, atributo);
	}
	
	/**
	 * Retorna o perfil musical do usuário logado na sessão
	 * 
	 * @param idSessao
	 * @return lista de IDs de sons
	 * @throws SessaoException
	 */
	public List<ID> getPerfilMusical(ID idSessao) throws SessaoException {
		// TODO mudar isso para que o facade não precise conhecer ID, nem Som
		ID idUser = gerenciadorSessao.getUsuario(idSessao);
		// TODO testar se idUser nunca será null
		return gerenciadorUsuario.getPerfilMusical(idUser);
	}

	/**
	 * Retorna ID_Som convertido para String
	 * 
	 * @param idSessaoStr
	 * @param link
	 * @param dataCriacao
	 * @return String que representa o ID_Som
	 * @throws PostException
	 * @throws SessaoException
	 * @throws CamposException
	 */
	public String postarSom(String idSessaoStr, String link, String dataCriacao) 
			throws PostException, SessaoException, CamposException {
		//TODO de quem é a responsábilidade de criar ID?
		ID idsessao = new ID(idSessaoStr);
		gerenciadorSessao.verificaID(idsessao);
		ID idUserDaSessao = gerenciadorSessao.getUsuario(idsessao);
		ID idSom = gerenciadorSons.criarSom(idsessao, link, dataCriacao);
		gerenciadorUsuario.postarSom(idUserDaSessao, idSom);
		return idSom.toString();
	}
	
	/**
	 * Retorna Atributo de som
	 * 
	 * @param idSom
	 * @param atributo
	 * @return Atributo: link ou dataCriação
	 * @throws AtributoException
	 * @throws PostException
	 */
	public String getAtributoSom(String idSom, String atributo)
			throws AtributoException, PostException {
		//TODO de quem é a responsábilidade de criar ID?
		// ID ID_Som = new ID((Long.parseLong(idSom)));
		return gerenciadorSons.getAtributoSom(idSom, atributo);
	}
	
	/**
	 * Encerra sessão do usuário
	 * @param login
	 * @throws UsuarioInexistenteException 
	 * @throws CamposException 
	 * @throws LoginInexistenteException 
	 */
	public void encerrarSessao(String login) throws UsuarioInexistenteException, CamposException, LoginInexistenteException {
		gerenciadorSessao.encerrarSessao(gerenciadorUsuario.getID_User(login));		
	}
	
	
	// ----------------------- US02 ----------------------
	
	/**
	 * Retorna o ID do usuário da sessao aberta
	 * @param idSessao
	 * @return ID_User
	 * @throws SessaoException
	 */
	public ID getIDUsuario(String idSessao) 
			throws SessaoException {
		return gerenciadorSessao.getUsuario(idSessao);
	}

	/**
	 * Retorna o número de seguidores do usuário
	 * 
	 * @param idSessao
	 * @return int
	 * @throws SessaoException
	 */
	public int getNumeroDeSeguidores(String idSessao) throws SessaoException {
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getNumeroDeSeguidores(ID_User);
	}
	
	public void seguirUsuario(String idSessaoQuemSegue, String loginQuemEhSeguido) 
			throws SessaoException, CamposException, LoginException, UsuarioInexistenteException {
		ID ID_User1 = getIDUsuario(idSessaoQuemSegue);
		ID ID_User2 = gerenciadorUsuario.getID_User(loginQuemEhSeguido);
		gerenciadorUsuario.seguirUsuario(ID_User1, ID_User2);
	}
	
	
	public List<ID> getFontesDeSons(String idSessao) 
			throws SessaoException {
		// TODO penser em outra solução para evitar que outras classes conheçam
		// gerenciadorSons
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getFontesDeSons(ID_User);		
	}
	
	public List<ID> getListaDeSeguidores(String idSessao) 
			throws SessaoException {
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getSeguidores(ID_User);
	}

	// ------------------- US03 --------------------
	public List<ID> getVisaoDosSons(String idSessao) 
			throws SessaoException {	
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getVisaoDosSons(ID_User);
	}

	public List<ID> getSonsFavoritos(String idSessao) 
			throws SessaoInvalidaException, SessaoInexistenteException {
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getSonsFavoritos(ID_User);
	}

	/**
	 * Retorna o feed extra do usuário que é composto pelos sons favoritos de
	 * quem ele segue
	 * 
	 * @param idSessao
	 * @return List<ID> de sons
	 * @throws SessaoInvalidaException
	 * @throws SessaoInexistenteException
	 */
	public List<ID> getFeedExtra(String idSessao)
			throws SessaoInvalidaException, SessaoInexistenteException {
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		return gerenciadorUsuario.getFeedExtra(ID_User);
	}

	public void favoritarSom(String idSessao, String idSom) 
			throws SessaoInvalidaException, SessaoInexistenteException, PostSomInvalidException, PostSomInexistenteException {
		ID ID_User = gerenciadorSessao.getUsuario(idSessao);
		ID ID_Som = gerenciadorSons.getID_Som(idSom);
		gerenciadorUsuario.favoritarSom(ID_User, ID_Som);
	}
	
	// ------------------- US04 --------------------
}