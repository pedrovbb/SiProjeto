package exception;

public class SessaoInexistenteException extends SessaoException {

	private static final long serialVersionUID = 1L;

	public SessaoInexistenteException() {
		super("Sessão inexistente");
	}
}
