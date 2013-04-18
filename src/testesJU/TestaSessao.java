package testesJU;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import outrasClases.ID;
import outrasClases.Sessao;
import outrasClases.Som;
import outrasClases.Usuario;

public class TestaSessao {

	ID id1, id2, id3;
	ID id_use1, id_use2, id_use3;
	Usuario use1,use2, use3;
	String login1, senha1, nome1, email1; 
	String login2, senha2, nome2, email2;
	String login3, senha3, nome3, email3; 
	
	Sessao ses1, ses2, ses3;
	

	
	
	
	@Before public void criarSessao(){
		
		id1 = new ID();
		id2 = new ID();
		id3 = new ID();
	
		
		login1 = "Fab_Nob";
		senha1 = "1234";
		nome1 = "Fabricio Nobrega";
		email1 = "fabricion@gmail.com";
		use1 = new Usuario(login1,senha1,nome1,email1);
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
		
	}

	@Test
	public void testSessao() {
		
		Assert.assertEquals(id1, ses1.getId_Sessao());
		Assert.assertEquals(id_use1, ses1.getId_DonoDaSessao());
		Assert.assertEquals(id2, ses2.getId_Sessao());
		Assert.assertEquals(id_use3, ses3.getId_DonoDaSessao());
		Assert.assertFalse(ses1.equals(ses2));
		Assert.assertFalse(ses1.equals(ses3));
		Assert.assertFalse(ses2.equals(ses3));
		Assert.assertTrue(ses1.equals(ses1));
		Assert.assertEquals("ID Dono: " + id_use1 + " ID Sessao: "+ id1, ses1.toString());
		Assert.assertEquals("ID Dono: " + id_use2 + " ID Sessao: "+ id2, ses2.toString());
		
				
	
		
	}


}
