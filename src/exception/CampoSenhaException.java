package exception;

public class CampoSenhaException extends CamposException {
	/**
	 * Erro no campo senha (null ou vazio)
	 */
	private static final long serialVersionUID = 1L;

	public CampoSenhaException() {
		super("Senha inválida");
	}

	public CampoSenhaException(String msgErro) {
		super(msgErro);
	}
}
