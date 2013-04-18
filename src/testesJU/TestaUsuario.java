package testesJU;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import outrasClases.ID;
import outrasClases.Som;
import outrasClases.Usuario;

public class TestaUsuario {

	Usuario use1,use2, use3;
	String login1, senha1, nome1, email1; 
	String login2, senha2, nome2, email2;
	String login3, senha3, nome3, email3; 
	


	
	
	
	@Before public void criarUsuario(){
		
		
		
		login1 = "Fab_Nob";
		senha1 = "1234";
		nome1 = "Fabricio Nobrega";
		email1 = "fabricion@gmail.com";
		use1 = new Usuario(login1,senha1,nome1,email1);
		
		login2 = "Rafa";
		senha2 = "123";
		nome2 = "Rafael"; 
		email2 = "rafa.almeida@gmail.com";
		use2 = new Usuario(login2, senha2, nome2, email2);
		
		login3 = "Rafael";
		senha3 = "123";
		nome3 = "Rafael"; 
		email3 = "rafa.almeida@gmail.com";
		use3 = new Usuario(login3, senha3, nome3, email3);
		
	}

	@Test
	public void testUsuario() {
		
		
		
		Assert.assertEquals(use1.getEmail(), "fabricion@gmail.com");
		Assert.assertEquals(use1.getLogin(), "Fab_Nob");
		Assert.assertEquals(use1.getNome(), "Fabricio Nobrega");
		Assert.assertEquals(use1.getSenha(), "1234");
		Assert.assertFalse(use1.equals(use2));
		Assert.assertFalse(use1.equals(use3));
		Assert.assertTrue(use1.equals(use1));
		
		
	
		
	}


}
