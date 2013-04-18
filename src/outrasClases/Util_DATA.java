package outrasClases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Util_DATA {

	private Util_DATA() {
	}
	
	public static String getCurrentData() {
		DateFormat formatacao = DateFormat.getDateInstance(DateFormat.MEDIUM);
		SimpleDateFormat converte = new SimpleDateFormat("dd/MM/yyy");
		converte.setLenient(false);
		return formatacao.format(new Date());
	}
}
