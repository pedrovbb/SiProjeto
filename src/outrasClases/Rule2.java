package outrasClases;

import java.io.Serializable;

public class Rule2 extends Rule implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Som som1, Som som2) {
		if (som1.getQuantidadeFavoritos() > som2.getQuantidadeFavoritos()) {
			return -1;
		}
		if (som1.getQuantidadeFavoritos() < som2.getQuantidadeFavoritos()) {
			return 1;
		}
		return 0;
	}
}
