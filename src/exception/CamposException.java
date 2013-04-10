package exception;

public class CamposException extends Exception {

	/**
	 * Classe destinada aos erro dos campos
	 */
	private static final long serialVersionUID = 1L;

	public CamposException() {
		super("Erro ao realizar operação, verifique os campos e tente novamente");
	}
	
	public CamposException(String msgErro){
		super(msgErro);
	}
}
