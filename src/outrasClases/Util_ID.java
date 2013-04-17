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
	public static String generateId() {
		if(FacesContext.getCurrentInstance() != null){
			System.out.println("Foi pego ID de JSF");
			FacesContext context = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	        System.out.println("sessão nula? " + session == null);
	        return session.getId();
		}
		return String.valueOf(new Date().getTime() * new Random().nextLong());
	}
}
