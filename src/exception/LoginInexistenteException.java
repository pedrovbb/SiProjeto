package exception;

public class LoginInexistenteException extends LoginException{

	private static final long serialVersionUID = 1L;
	
	public LoginInexistenteException() {
		super("Login inexistente");
	}
}