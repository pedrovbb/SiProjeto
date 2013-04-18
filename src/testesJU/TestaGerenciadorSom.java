package testesJU;

import static org.junit.Assert.*;
import exception.PostException;
import exception.PostSomInexistenteException;
import gerenciadores.GerenciadorSons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import outrasClases.ID;
import outrasClases.Sessao;
import outrasClases.Som;
import outrasClases.Usuario;

public class TestaGerenciadorSom {

	GerenciadorSons gs;

	ID id1, id2, id3;
	ID id_use1, id_use2, id_use3;
	ID id_ses1, id_ses2, id_ses3;
	Usuario use1, use2, use3;
	String login1, senha1, nome1, email1;
	String login2, senha2, nome2, email2;
	String login3, senha3, nome3, email3;

	Sessao ses1, ses2, ses3;

	Som som;
	ID id_som, id_qualquer;
	String link, data;

	@Before
	public void criarGerenciadorSom() {

		gs = new GerenciadorSons();

		id_qualquer = new ID();
		id_som = new ID();
		link = "http://musica";
		data = "13/08/1991";

		som = new Som(id_som, link, data);

		id1 = new ID();
		id2 = new ID();
		id3 = new ID();

		login1 = "Fab_Nob";
		senha1 = "1234";
		nome1 = "Fabricio Nobrega";
		email1 = "fabricion@gmail.com";
		use1 = new Usuario(login1, senha1, nome1, email1);
		id_use1 = use1.getID();
		ses1 = new Sessao(id_use1, id1);

		login2 = "Rafa";
		senha2 = "123";
		nome2 = "Rafael";
		email2 = "rafa.almeida@gmail.com";
		use2 = new Usuario(login2, senha2, nome2, email2);
		id_use2 = use2.getID();
		ses2 = new Sessao(id_use2, id2);

		login3 = "Rafael";
		senha3 = "123";
		nome3 = "Rafael";
		email3 = "rafa.almeida@gmail.com";
		use3 = new Usuario(login3, senha3, nome3, email3);
		id_use3 = use3.getID();
		ses3 = new Sessao(id_use3, id3);

		id_ses1 = ses1.getId_Sessao();
		id_ses2 = ses2.getId_Sessao();
		id_ses3 = ses3.getId_Sessao();

	}

	@Test
	public void testGerenciadorSom() throws PostException {

		gs.criarSom(id_ses1, "http://musica", "17/04/2013");

		try {
			gs.criarSom(id_ses1, "http://musica", "");
		} catch (Exception e) {
			Assert.assertEquals("Data de Criação inválida", e.getMessage());
		}

		try {
			gs.getID_Som(id_qualquer.toString());
		} catch (Exception e) {
			Assert.assertEquals("Som inexistente", e.getMessage());
		}

	}

}
