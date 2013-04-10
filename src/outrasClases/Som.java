package outrasClases;

public class Som {

	private ID ID_sessao, ID_Som;
	private String link;
	private String dataCriacao;

	public Som(ID sessao, String link, String dataCriacao) {
		this.ID_sessao = sessao;
		this.link = link;
		this.dataCriacao = dataCriacao;
		this.ID_Som = new ID();
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

	public ID getID_Som() {
		return ID_Som;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}
}
