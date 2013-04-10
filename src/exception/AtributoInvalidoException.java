package exception;

public class AtributoInvalidoException extends AtributoException{

	private static final long serialVersionUID = 1L;

	public AtributoInvalidoException() {
		super("Atributo inválido");
	}
}
