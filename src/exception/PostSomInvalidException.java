package exception;

public class PostSomInvalidException extends PostException {

	private static final long serialVersionUID = 1L;

	public PostSomInvalidException() {
		super("Som inválido");
	}
}
