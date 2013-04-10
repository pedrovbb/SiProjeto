package exception;

public class SessaoInvalidaException extends SessaoException {

	private static final long serialVersionUID = 1L;

	public SessaoInvalidaException() {
		super("Sessão inválida");
	}
}
