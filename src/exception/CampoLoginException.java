package exception;

public class CampoLoginException extends CamposException {

	/**
	 * Erro no campo login (null ou vazio)
	 */
	private static final long serialVersionUID = 1L;

	public CampoLoginException() {
		super("Login inválido");
	}
}
