package outrasClases;

import java.io.Serializable;

public class ID implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;

	public ID() {
		this.id = Util_ID.generateId();
	}

	public ID(String newID) {
		this.id = newID;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ID)
			return ((ID) obj).getId().equalsIgnoreCase(id);
		return false;
	}

	@Override
	public String toString() {
		return id.trim();
	}
}
