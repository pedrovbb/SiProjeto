package exception;

public class CadastraException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastraException() {
		super("Falha ao cadastrar novo usuario, verifique os dados e tente novamente");
	}

	public CadastraException(String msgErro) {
		super(msgErro);
	}
}
