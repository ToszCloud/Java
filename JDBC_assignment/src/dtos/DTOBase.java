package dtos;


public abstract class DTOBase {

	private int id;

	protected DTOBase() {}

	protected DTOBase(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean hasExistingId() {
		return getId() > 0;
	}
}