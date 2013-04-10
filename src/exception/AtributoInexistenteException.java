package exception;

public class AtributoInexistenteException extends AtributoException {

	private static final long serialVersionUID = 1L;

	public AtributoInexistenteException() {
		super("Atributo inexistente");
	}
}
