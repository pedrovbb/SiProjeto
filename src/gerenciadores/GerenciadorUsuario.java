package gerenciadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exception.AtributoException;
import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.CadastraException;
import exception.CampoEmailException;
import exception.CampoLoginException;
import exception.CampoNomeException;
import exception.CampoSenhaException;
import exception.CamposException;
import exception.LoginException;
import exception.LoginInexistenteException;
import exception.UsuarioInexistenteException;

import outrasClases.ID;
import outrasClases.Usuario;

public class GerenciadorUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	// <idUsuario | objUsuario >
	private Map<ID, Usuario> usuarioCadastrados;

	private enum Campos {
		LOGIN, SENHA, EMAIL, NOME;
	}

	/**
	 * Construtor
	 */
	public GerenciadorUsuario() {
		usuarioCadastrados = new HashMap<ID, Usuario>();
	}

	/**
	 * Criar Usuario
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
		System.out.println("a");
		verificaExcecoes(login, email, senha, nome);
		System.out.println("b");
		verificaExistancia(login, email);
		Usuario usuario = new Usuario(login, senha, nome, email);
		System.out.println("c");
		usuarioCadastrados.put(usuario.getID(), usuario);
	}

	/**
	 * Verifica se esta cadastrado e senha correta
	 * 
	 * @param login
	 * @param senha
	 * @return True se as informações conferem
	 * @throws CamposException
	 * @throws CampoSenhaException
	 */
	public boolean verificaCadastro(String login, String senha)
			throws UsuarioInexistenteException, CamposException {
		verificaCampos(login, Campos.LOGIN);
		verificaCampos(senha, Campos.SENHA);
		Usuario usuario = searchUser(login);
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				return true;
			}
			throw new CampoLoginException();
		}
		throw new UsuarioInexistenteException();
	}
	
	/** Retorna ID_User dado seu login
	 * @param login
	 * @return ID do usuário que tem esse login
	 * @throws CamposException 
	 * @throws LoginInexistenteException 
	 */
	public ID getID_User(String login) throws CamposException, LoginInexistenteException {
		Usuario usuario = searchUser(login);
		if (usuario != null) {
			return usuario.getID();
		}
		//TODO para passar nos testes
		throw new LoginInexistenteException();
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
	public String getAtributo(String login, String atributo) 
			throws CamposException, AtributoException, UsuarioInexistenteException {
			verificaCampos(login, Campos.LOGIN);
			Usuario usuario = searchUser(login);
			if(usuario!=null){
				if (atributo == null || atributo.trim().isEmpty()) {
					throw new AtributoInvalidoException();
				}
				else if (atributo.equals("nome")) {
					return usuario.getNome();
				} else if (atributo.equals("email")) {
					return usuario.getEmail();
				} else{
					throw new AtributoInexistenteException();
				}
			}else{
				throw new UsuarioInexistenteException();
			}
	}

	/**
	 * @param idUser
	 * @return perfil musical, se não existir, retorna null
	 */
	public List<ID> getPerfilMusical(ID idUser) {
		if (usuarioCadastrados.containsKey(idUser)) {
			return usuarioCadastrados.get(idUser).getPerfilMusical();
		}
		// TODO que tal uma nova exceção?
		return null;
	}
	
	public void postarSom(ID idUser, ID IdSom) 
			throws CamposException {
		Usuario user = searchUser2(idUser); 
		user.addSom(IdSom);
	}

	// ------------------------- US02 ---------------------------

	/**
	 * Retorna o tamanho da lista de seguidores de um deterinado User
	 * 
	 * @param login
	 * @return
	 */
	public int getNumeroDeSeguidores(ID ID_UserRecebido) {
		Usuario userEncontrado = searchUser2(ID_UserRecebido);
		return userEncontrado.getNumeroDeSeguidores();
	}	
	
	public void seguirUsuario(ID ID_QuemSegue, ID ID_QuemEhSeguido)
			throws CamposException, LoginException {
		// TODO problema aqui
//		verificaId(ID_QuemSegue);
//		verificaId(ID_QuemEhSeguido);
		if(ID_QuemSegue.equals(ID_QuemEhSeguido)){
			// login exception??? Fiz besteira?? kkkkkkkk
			throw new CampoLoginException();
		}
		if(!usuarioCadastrados.containsKey(ID_QuemEhSeguido)){
			throw new LoginInexistenteException();
		}
		Usuario usuario1 = usuarioCadastrados.get(ID_QuemSegue);
		Usuario usuario2 = usuarioCadastrados.get(ID_QuemEhSeguido);
		//user 1 passa a seguir user 2
		usuario1.addSeguindo(usuario2.getID());
		//User2 passa a ter user 1 como seguidor
		usuario2.addSeguidor(usuario1.getID());
	}
	
	/**
	 * Retorna a lista de ids users que são fontes de son do usuário passado seu ID
	 * 
	 * @param iD_User
	 * @return List<ID>
	 */
	public List<ID> getFontesDeSons(ID iD_User) {
		Usuario user = usuarioCadastrados.get(iD_User);
		return user.getFontesDeSons();
	}

	/**
	 * Retorna a lista de ids users que são seguidores do usuário passado seu ID
	 * 
	 * @param iD_User
	 * @return List<ID>
	 */
	public List<ID> getSeguidores(ID ID_User) {
		Usuario user = usuarioCadastrados.get(ID_User);
		return user.getSeguidores();
	}
	
	// ------------------------- US02 ---------------------------
	public List<ID> getVisaoDosSons(ID ID_User) {
		Usuario user = usuarioCadastrados.get(ID_User);
		List<ID> visaoDosSons = new ArrayList<ID>();
		List<ID> fonteDeSons = user.getFontesDeSons();
		for (ID id : fonteDeSons) {
			visaoDosSons.addAll(0, searchUser2(id).getPerfilMusical());
		}
		return visaoDosSons;
	}

	// ------------------------- US03 ---------------------------
	public List<ID> getSonsFavoritos(ID ID_User){
		Usuario user = usuarioCadastrados.get(ID_User);
		return user.getSonsFavoritos();
	}
	
	// ------------------------- US04 ---------------------------
	
	/**
	 * FeedExtra é composto pelos sons favoritos dos usuários que ele segue, ou
	 * seja, os sons favoritos de suas fonte de sons
	 * 
	 * @param ID_User
	 * @return List<ID> de sons
	 */
	public List<ID> getFeedExtra(ID ID_User) {
		// TODO precisa testar!
		Usuario user = usuarioCadastrados.get(ID_User);
		List<ID> fonteDeSons = user.getFontesDeSons();
		List<ID> feedExtra = new ArrayList<ID>();
		Iterator<ID> it = fonteDeSons.iterator();
		while (it.hasNext()) {
			ID id = (ID) it.next();
			feedExtra.addAll(0, searchUser2(id).getSonsFavoritos());
		}
		return feedExtra;
	}
	
	public void favoritarSom(ID ID_quemFavorita, ID somFavoritado){
		Usuario user = searchUser2(ID_quemFavorita);
		user.addSomFavorito(somFavoritado);
	}
	
	
	// ------------------------- AUXILIARES --------------------------------
	/**
	 * Verifica exceções de campos 
	 * 
	 * @param login
	 * @param email
	 * @param senha
	 * @param nome
	 * @throws CamposException
	 */
	private void verificaExcecoes(String login, String email, String senha, String nome) 
			throws CamposException {
		verificaCampos(login, Campos.LOGIN);
		verificaCampos(email, Campos.EMAIL);
		verificaCampos(senha, Campos.SENHA);
		verificaCampos(nome, Campos.NOME);
	}

	/**
	 * Verifica a existência de erros nos campos
	 * @param dado
	 * @param campo
	 * @throws CamposException
	 */
	private void verificaCampos(String dado, Campos campo)
			throws CamposException {
		if (dado == null || dado.trim().isEmpty()) {
			switch (campo) {
			case LOGIN:
				throw new CampoLoginException();
			case SENHA:
				throw new CampoSenhaException();
			case NOME:
				throw new CampoNomeException();
			case EMAIL:
				throw new CampoEmailException();
			}
		}
	}

	/**
	 * Procura um usuário e retorna dado seu login
	 * 
	 * @param login
	 * @return Usuário encontrado ou null
	 * @throws CamposException 
	 */
	private Usuario searchUser(String login) throws CamposException {
		verificaCampos(login, Campos.LOGIN);
		Iterator<Entry<ID, Usuario>> it = usuarioCadastrados.entrySet().iterator();
		while (it.hasNext()) {
			Entry<ID, Usuario> pairs = it.next();
			if (pairs.getValue().getLogin().equals(login)) {
				return pairs.getValue();
			}
		}
		return null;
	}
	

	/**
	 * Verifica se já existe um usuário com mesmo login e email, se não lançar
	 * exceção, então não existe
	 * 
	 * @param login
	 * @param email
	 * @throws CadastraException
	 */
	private void verificaExistancia(String login, String email) 
			throws CadastraException {
		Iterator<Entry<ID, Usuario>> it = usuarioCadastrados.entrySet().iterator();
		while (it.hasNext()) {
			Entry<ID, Usuario> pairs = it.next();
			if (pairs.getValue().getEmail().equals(email)) {
				throw new CadastraException("Já existe um usuário com este email");
			} else if (pairs.getValue().getLogin().equals(login)) {
				throw new CadastraException("Já existe um usuário com este login");
			}
		}
	}

	/**
	 * Retorn Objeto Usuario dado ID
	 * 
	 * @param ID_User
	 * @return Usuario
	 */
	private Usuario searchUser2(ID ID_User) {
		Iterator<Entry<ID, Usuario>> it = usuarioCadastrados.entrySet().iterator();
		while (it.hasNext()) {
			Entry<ID, Usuario> pairs = it.next();
			if (pairs.getKey().equals(ID_User)) {
				return pairs.getValue();
			}
		}
		return null;
	}


	


	// ---------------- US02 -----------------
//	private void verificaId(Object idUser) {
//		if (idUser == null) {
//			throw new Usuario;
//		}
//		if (idUser instanceof String) {
//			if (((String) idUser).trim().isEmpty()) {
//				throw new PostSomInvalidException();
//			}
//		}
//	}

}
