package outrasClases;

import java.util.Date;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public abstract class Util_ID {

	/**
	 * @return Returns the number of milliseconds since January 1, 1970, 00:00:00 multiplicado por um random
	 * OU o IdSession de JSF
	 */
	protected static String generateId() {
		String idGerado = getCurrentID_SessionJSF();
		if(idGerado == null){
			idGerado = String.valueOf(new Date().getTime() * new Random().nextLong());
		}
		return idGerado;
	}
	
	public static String getCurrentID_SessionJSF(){
		if(FacesContext.getCurrentInstance() != null){
			FacesContext context = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	        return session.getId();
		}
		return null;
	}
	
	//TODO gambiarra mesmo
	protected static String generateIdManual() {
		return String.valueOf(new Date().getTime() * new Random().nextLong());
	}
}
