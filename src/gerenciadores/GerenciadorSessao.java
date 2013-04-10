package gerenciadores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;

import outrasClases.ID;
import outrasClases.Sessao;

public class GerenciadorSessao {

	private List<Sessao> sessoesAbertas;

	public GerenciadorSessao() {
		sessoesAbertas = new ArrayList<Sessao>();
	}

	/**
	 * @param idSessaoRecebida IdSessao
	 * @return Id do usuário dono da sessão passada como parâmetro, ou null caso
	 *         não encontre;
	 * @throws SessaoInvalidaException 
	 * @throws SessaoInexistenteException 
	 */
	public ID getUsuario(Object idSessaoRecebida)
			throws SessaoInvalidaException, SessaoInexistenteException {

		verificaExcecoes(idSessaoRecebida);
		ID ID_Sessao;
		if (idSessaoRecebida instanceof String)
			ID_Sessao = new ID(Long.parseLong((String) idSessaoRecebida));
		else
			ID_Sessao = (ID) idSessaoRecebida;

		Sessao sessaoEncontrada = searchSessao(ID_Sessao);
		if (sessaoEncontrada != null) {
			return sessaoEncontrada.getId_DonoDaSessao();
		}
		// TODO testar se nunca chega aqui!
		return null;
	}

	/**
	 * Inicia uma nova sessão
	 * 
	 * @param id_User
	 * @return ID da sessão iniciada
	 */
	public ID abrirSessao(ID id_User) {
		Sessao novaSessao = new Sessao(id_User, new ID());
		sessoesAbertas.add(novaSessao);
		return novaSessao.getId_Sessao();
	}
	
	public void verificaID(ID idSessao)
			throws SessaoInvalidaException, SessaoInexistenteException {
		verificaExcecoes(idSessao);
	}
	
	/**
	 * Encerra sessão dado ID do usuário
	 * @param ID_User
	 */
	public void encerrarSessao(ID ID_User) {
		Sessao sessao = searchUser(ID_User);
		if (sessao != null) {
			sessoesAbertas.remove(sessao);
		}
	}

	// --------------- AUXILIARES -------------------
	private void verificaExcecoes(Object idSessaoRecebida)
			throws SessaoInvalidaException, SessaoInexistenteException {
		if (idSessaoRecebida == null) {
			throw new SessaoInvalidaException();
		}

		if (idSessaoRecebida instanceof String) {
			if(((String) idSessaoRecebida).trim().isEmpty()){
				throw new SessaoInvalidaException();
			}
			try {
				Long.parseLong((String) idSessaoRecebida);
			} catch (NumberFormatException e) {
				// é uma estring qualquer que não dá pra converter em long
				throw new SessaoInexistenteException();
			}
			
			if ((searchSessao(new ID(Long.parseLong((String) idSessaoRecebida)))) == null) {
				throw new SessaoInexistenteException();
			}
		}
		if (idSessaoRecebida instanceof ID) {
			if ((searchSessao((ID) (idSessaoRecebida))) == null) {
				throw new SessaoInexistenteException();
			}
		}
	}

	private Sessao searchSessao(ID idSessaoRecebida) {
		Iterator<Sessao> it = sessoesAbertas.iterator();
		while (it.hasNext()) {
			Sessao aux = (Sessao) it.next();
			if (aux.getId_Sessao().equals(idSessaoRecebida)) {
				return aux;
			}
		}
		return null;
	}
	
	private Sessao searchUser(ID idUserRecebido) {
		Iterator<Sessao> it = sessoesAbertas.iterator();
		while (it.hasNext()) {
			Sessao aux = (Sessao) it.next();
			if (aux.getId_DonoDaSessao().equals(idUserRecebido)) {
				return aux;
			}
		}
		return null;
	}
}
