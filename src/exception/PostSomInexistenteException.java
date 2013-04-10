package exception;

public class PostSomInexistenteException extends PostException{

	private static final long serialVersionUID = 1L;

	public PostSomInexistenteException() {
		super("Som inexistente");
	}
}
