package br.com.insula.opes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TelefoneTest {

	@Test
	public void testFromString() {
		assertNotNull(Telefone.fromString("(44) 4052-9211"));
		assertNotNull(Telefone.fromString("4052-9211"));
		assertNotNull(Telefone.fromString("14052-9211"));
		assertNotNull(Telefone.fromString("+55 (44) 4052-9211"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithLessDigits() {
		Telefone.fromString("1234567");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithMoreDigits() {
		Telefone.fromString("12345678901234");
	}

	@Test
	public void testToString() {
		assertEquals("4052-9211", Telefone.fromString("40529211").toString());
		assertEquals("1405-29211", Telefone.fromString("140529211").toString());
		assertEquals("(44) 4052-9211", Telefone.fromString("4440529211").toString());
		assertEquals("(44) 4052-92110", Telefone.fromString("44405292110").toString());
		assertEquals("+55 (44) 4052-9211", Telefone.fromString("554440529211").toString());
		assertEquals("+55 (44) 4052-92110", Telefone.fromString("5544405292110").toString());
	}

}
