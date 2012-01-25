package br.com.insula.opes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InscricaoEstadualTest {

	@Test
	public void testFromString() {
		assertNotNull(InscricaoEstadual.fromString("ISENTO"));
		assertEquals("ISENTO", InscricaoEstadual.fromString("ISENTO").toString());
		assertEquals("ISENTO", InscricaoEstadual.fromString("isento").toString());
		assertNotNull(InscricaoEstadual.fromString("9031525087"));
		assertEquals("9031525087", InscricaoEstadual.fromString("9031525087abc").toString());
		assertEquals("9031525087", InscricaoEstadual.fromString("90.315250-87").toString());
		assertEquals("90", InscricaoEstadual.fromString("90").toString());
		assertEquals("01234567890123", InscricaoEstadual.fromString("01234567890123").toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidFromString() {
		InscricaoEstadual.fromString("abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOverflowFromString() {
		InscricaoEstadual.fromString("012345678901234");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnderflowFromString() {
		InscricaoEstadual.fromString("1");
	}

}