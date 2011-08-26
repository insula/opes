package br.com.insula.opes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CepTest {

	@Test
	public void testFromString() {
		Cep cep = Cep.fromString("87030-020");
		assertNotNull(cep);
		assertEquals("87030-020", cep.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromInvalidString() {
		Cep cep = Cep.fromString("8703002");
		assertNotNull(cep);
		assertEquals("87030-020", cep.toString());
	}

}
