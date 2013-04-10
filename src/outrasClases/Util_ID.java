package outrasClases;

import java.util.Date;
import java.util.Random;

public abstract class Util_ID {

	/**
	 * @return Returns the number of milliseconds since January 1, 1970,
	 *         00:00:00 multiplicado por um random
	 */
	public static long generateId() {
		return new Date().getTime() * new Random().nextLong();
	}

}
