package gerenciadores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exception.AtributoException;
import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.PostDateException;
import exception.PostException;
import exception.PostSomInexistenteException;
import exception.PostSomInvalidException;

import outrasClases.ID;
import outrasClases.Rule;
import outrasClases.Som;

public class GerenciadorSons implements Serializable {

	private static final long serialVersionUID = 1L;
	// <idSom | objSom >
	private Map<ID, Som> sonsCadastrados;
	
	public GerenciadorSons() {
		sonsCadastrados = new HashMap<ID, Som>();
	}

	/**
	 * Cria Som e retorna seu ID
	 * 
	 * @param idSessao
	 * @param link
	 * @param dataCriacao
	 * @return ID do som criado
	 * @throws PostException
	 */
	public ID criarSom(ID idSessao, String link, String dataCriacao)
			throws PostException {
		verificaLink(link);
		try {
			verificaData(dataCriacao);	
		} catch (Exception e) {
			throw new PostDateException();
		}
		Som som = new Som(idSessao, link, dataCriacao);
		sonsCadastrados.put(som.getID_Som(), som);
		return som.getID_Som();
	}
	
	/**
	 * Se não lançar exceção, retorna a data de criação do som no formato String
	 * 
	 * @param idSom
	 * @param atributo
	 * @return data de criação do som (String)
	 * @throws AtributoException
	 * @throws PostException
	 */
	public String getAtributoSom(String idSom, String atributo)
			throws AtributoException, PostException {
		verificaId(idSom);
		ID ID_Som = new ID(idSom);
		verificaAtributo(atributo);
		// tentei o get de Map, mas nao funcionou
		Som som = search(ID_Som);
		//TODO tratar caso retorn null
		if (atributo.equals("dataCriacao")) {
			return som.getDataCriacao();
		}
		throw new AtributoInexistenteException();
	}
	
	// TODO apenas para testes
	public int getNumeroDeSons() {
		return sonsCadastrados.size();
	}
	
	/**
	 * Retorna ID do som caso sejá válida e exista
	 * 
	 * @param idString
	 * @return ID id_Som
	 * @throws PostSomInvalidException
	 * @throws PostSomInexistenteException
	 */
	public ID getID_Som(String idString) throws PostSomInvalidException,
			PostSomInexistenteException {
		verificaId(idString);
		ID ID_Som = new ID(idString);
		if (search(ID_Som) != null) {
			return ID_Som;
		}
		throw new PostSomInexistenteException();
	}
	
	public List<Som> getSons(List<ID> sonsParaPegar){
		List<Som> sons = new ArrayList<Som>();
		Iterator<ID> it = sonsParaPegar.iterator();
		while (it.hasNext()) {
			ID id = (ID) it.next();
			sons.add(search(id));
		}
		return sons;
	}
	
	public List<ID> getMainFeed(List<ID> sonsDasFontes, Rule rule) {
		List<Som> feed = getSons(sonsDasFontes);
		Collections.sort(feed, rule);
		return getIdSons(feed);
	}
	

	public void acrescentaFavoritos(ID idUser, ID idSom) {
		search(idSom).incrementaFavorito(idUser);
		//sonsCadastrados.get(idSom).incrementaFavorito();
	}
	
	//----------------------- AUXILIARES -----------------------
	
	/**
	 * Verifica se o link é válido
	 * 
	 * @param link
	 * @throws PostSomInvalidException
	 */
	private void verificaLink(String link) throws PostSomInvalidException {
		if (link == null || link.trim().isEmpty()) {
			throw new PostSomInvalidException();
		}
	}
	
	/**
	 * Verifica se a data é válida
	 * 
	 * @param data
	 * @throws PostException
	 */
	@SuppressWarnings("deprecation")
	private void verificaData(String data) 
			throws PostException {
		if (data == null || data.trim().isEmpty()) {
			throw new PostDateException();
		}
		SimpleDateFormat converte = new SimpleDateFormat("dd/MM/yyy");
		converte.setLenient(false);
		Date dataRecebida;
		Date dataAtual = new Date();
		try {
			// se passar por aqui, eh do tipo dd/mm/yyyy
			dataRecebida = converte.parse(data);
			dataRecebida.setHours(dataAtual.getHours());
			dataRecebida.setMinutes(dataAtual.getMinutes());
			dataRecebida.setSeconds(dataAtual.getSeconds()+1);
		} catch (ParseException e) {
			throw new PostDateException();
		} catch (Exception e) {
			throw new PostException("Erro desconhecido ao receber data");
		}
		if (dataRecebida.before(dataAtual)) {
			throw new PostDateException();
		}
	}

	/**
	 * Verifica se ID_Som é válido
	 * 
	 * @param idSom
	 * @throws PostSomInvalidException
	 */
	protected void verificaId(Object idSom) throws PostSomInvalidException {
		// TODO falta melhorar, olhar o verifica de gerenciadorSessao
		if (idSom == null) {
			throw new PostSomInvalidException();
		}
		if (idSom instanceof String) {
			if (((String) idSom).trim().isEmpty()) {
				throw new PostSomInvalidException();
			}
		}
	}
	
	/**
	 * Verifica se o atributo é válido
	 * 
	 * @param atributo
	 * @throws AtributoInvalidoException
	 */
	private void verificaAtributo(String atributo)
			throws AtributoInvalidoException {
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new AtributoInvalidoException();
		}
	}

	/**
	 * Procura e retorna o Som dado idSom ou null caso não encontre
	 * @param idSom
	 * @return Retorna objeto Som ou null
	 */
	private Som search(ID idSom) {
		Iterator<Entry<ID, Som>> it = sonsCadastrados.entrySet().iterator();
		while (it.hasNext()) {
			Entry<ID, Som> pairs = it.next();
			if (pairs.getKey().equals(idSom)) {
				return pairs.getValue();
			}
		}
		return null;
	}
	
	private List<ID> getIdSons(List<Som> sonsParaPegar){
		List<ID> sons = new ArrayList<ID>();
		Iterator<Som> it = sonsParaPegar.iterator();
		while (it.hasNext()) {
			Som id = (Som) it.next();
			sons.add(id.getID_Som());
		}
		return sons;
	}
}
