package outrasClases;

import java.io.Serializable;

public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;
	private ID Id_DonoDaSessao, id_Sessao;

	public Sessao(ID usuario, ID sessao) {
		Id_DonoDaSessao = usuario;
		id_Sessao = sessao;
	}

	public ID getId_DonoDaSessao() {
		return Id_DonoDaSessao;
	}

	public ID getId_Sessao() {
		return id_Sessao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sessao) {
			return (((Sessao) obj).getId_Sessao().equals(id_Sessao))
					&& ((Sessao) obj).getId_DonoDaSessao().equals(Id_DonoDaSessao);
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO apenas para teste
		return "ID Dono: " + Id_DonoDaSessao + " ID Sessao: " + id_Sessao;
	}
}
