package br.com.insula.opes;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailTest {

	@Test
	public void testFromString() {
		assertNotNull(Email.fromString("yanaga@insula.com.br"));
		assertNotNull(Email.fromString("teste.domain@insula.com.br"));
		assertNotNull(Email.fromString("user123_321@insula.com.br"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutUser() {
		Telefone.fromString("@insula.com.br");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutDomain() {
		Telefone.fromString("user@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutAt() {
		Telefone.fromString("user.teste");
	}

	@Test
	public void testToString() {
		assertEquals("yanaga@insula.com.br", Email.fromString("yanaga@insula.com.br").toString());
	}

}
