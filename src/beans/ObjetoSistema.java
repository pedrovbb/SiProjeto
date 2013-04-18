package beans;

import principal.Sistema;

public abstract class ObjetoSistema {

	private static Sistema sistema;

	public ObjetoSistema() {
		sistema = new Sistema();
	}

	public static Sistema getSistema() {
		if (sistema == null)
			sistema = new Sistema();
		return sistema;
	}

	public static void setSistema(Sistema sistema) {
		ObjetoSistema.sistema = sistema;
	}
}
