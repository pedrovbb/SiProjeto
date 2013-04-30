package exception;

public class RegraDeComposicaoInexistente extends Exception {

	private static final long serialVersionUID = 1L;

	public RegraDeComposicaoInexistente() {
		super("Regra de composição inexistente");
	}
}
