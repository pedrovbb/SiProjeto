package outrasClases;

import java.io.Serializable;
import java.util.Comparator;

public abstract class Rule  implements Comparator<Som>, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public abstract int compare(Som som1, Som som2);

}
