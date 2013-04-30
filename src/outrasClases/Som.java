package outrasClases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Som implements Serializable, Comparable<Som> {

	private static final long serialVersionUID = 1L;
	private ID ID_sessao, ID_Som;
	private String link;
	private String dataCriacao;
	private List<ID> quemFavoritou;

	public Som(ID sessao, String link, String dataCriacao) {
		this.ID_sessao = sessao;
		this.link = link;
		this.dataCriacao = dataCriacao;
		this.ID_Som = new ID("som");
		this.quemFavoritou = new ArrayList<ID>();
	}

	public ID getID_sessao() {
		return ID_sessao;
	}

	public String getLink() {
		return link;
	}

	/**
	 * @return Array String[idSessao, link, dataCriacao]
	 */
	public String[] toStringPersonalizado() {
		String[] toString = { String.valueOf(ID_sessao), link, dataCriacao };
		return toString;
	}

	@Override
	public String toString() {
		return link;
	}

	public ID getID_Som() {
		return ID_Som;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	@Override
	public int compareTo(Som o) {
		// TODO regra padrão - regra1
		return 0;
	}

	public void incrementaFavorito(ID idUser) {
		this.quemFavoritou.add(idUser);
	}

	public int getQuantidadeFavoritos() {
		return quemFavoritou.size();
	}
}
