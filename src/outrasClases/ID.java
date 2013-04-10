package outrasClases;

public class ID {

	private long id;

	public ID() {
		this.id = Util_ID.generateId();
	}

	public ID(long newID) {
		this.id = newID;
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ID)
			return ((ID) obj).getId() == id;
		return false;
	}

	@Override
	public String toString() {
		return id + "";
	}
}
