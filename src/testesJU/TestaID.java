package testesJU;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import outrasClases.ID;
import outrasClases.Som;

public class TestaID {

	ID id1, id2;

	@Before
	public void criarID() {
		id1 = new ID();
		id2 = new ID();
	}

	@Test
	public void testID() {

		Assert.assertFalse(id1.equals(id2));
		Assert.assertTrue(id1.equals(id1));

	}

}
