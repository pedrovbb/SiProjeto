package exception;

public class PostDateException extends PostException {

	private static final long serialVersionUID = 1L;

	public PostDateException() {
		super("Data de Criação inválida");
	}
}
