package outrasClases;

import java.io.Serializable;

public class Rule1 extends Rule implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Som som1, Som som2) {
		// TODO compara pela data, mas ja eh o padrao
		return 0;
	}

}
