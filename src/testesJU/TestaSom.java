package testesJU;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import outrasClases.ID;
import outrasClases.Som;

public class TestaSom {
	
	Som som, som2;
	ID id, id2;
	String link, link2, data, data2;
	
	
	
	@Before public void criarSom(){
		id = new ID();
		id2 = new ID();
		link = "http://musica";
		link2 = "http://musica";
		data = "17/05/2000";
		data2 = "17/04/2013";
		
		som = new Som(id ,link , data);
		som2 = new Som(id2, link2, data2);
	}


	@Test
	public void testSom() {
		
		Assert.assertEquals(som.getDataCriacao(), "17/05/2000");
		Assert.assertEquals(som.getLink(), "http://musica");
		Assert.assertEquals(som.getID_sessao(), id);
		Assert.assertEquals(som2.getDataCriacao(), "17/04/2013");
		Assert.assertTrue(som.equals(som));
		Assert.assertFalse(som.equals(som2));
		
		String [] list = {id.toString(), link, data};
			
		Assert.assertArrayEquals(som.toStringPersonalizado(), list);
	
		
	}

}
