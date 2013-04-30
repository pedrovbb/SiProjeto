package outrasClases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login, senha, nome, email;
	private ID id;
	private Rule regraUsada; 
	private List<ID> perfilMusical, seguidores, fontesDeSons, sonsFavoritos;
	
	public Usuario(String login, String senha, String nome, String email) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.id = new ID("user");
		this.perfilMusical = new ArrayList<ID>();
		this.seguidores = new ArrayList<ID>();
		this.fontesDeSons = new ArrayList<ID>();
		this.sonsFavoritos = new ArrayList<ID>();
		this.setRegraUsada(new Rule1());
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ID getID() {
		return id;
	}
	
	public List<ID> getPerfilMusical() {
		return perfilMusical;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			boolean condicao1, condicao2, condicao3, condicao4, condicao5;
			condicao1 = ((Usuario) obj).getLogin().equals(this.getLogin());
			condicao2 = ((Usuario) obj).getNome().equals(this.getNome());
			condicao3 = ((Usuario) obj).getEmail().equals(this.getEmail());
			condicao4 = ((Usuario) obj).getSenha().equals(this.getSenha());
			condicao5 = ((Usuario) obj).getID().equals(this.getID());
			return (condicao1 && condicao2 && condicao3 && condicao4 && condicao5);
		}
		return false;
	}

	public void addSom(ID idSom) {
		perfilMusical.add(0, idSom);
	}
	
	// ------------------------ US02 -----------------------
	
	public int getNumeroDeSeguidores() {
		return seguidores.size();
	}
	
	public void addSeguindo(ID idUser) {
		fontesDeSons.add(idUser);		
	}
	/**
	 * add IdUser na lista de seguidor;
	 * @param idUser
	 */
	public void addSeguidor(ID idUser) {
		seguidores.add(idUser);
	}
	
	public List<ID> getFontesDeSons() {
		return fontesDeSons;
	}
	
	public List<ID> getSeguidores() {
		return seguidores;
	}
	
	
	// ------------------------ US03 -----------------------
	
	public List<ID> getSonsFavoritos() {
		return sonsFavoritos;
	}
	
	
	// ------------------------ US04 -----------------------
	public void addSomFavorito(ID ID_Som) {
		sonsFavoritos.add(0, ID_Som);
	}

	// ------------------------ US05 -----------------------
	public Rule getRegraUsada() {
		return regraUsada;
	}

	public void setRegraUsada(Rule regraUsada) {
		this.regraUsada = regraUsada;
	}
}
