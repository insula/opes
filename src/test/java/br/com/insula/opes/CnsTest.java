package br.com.insula.opes;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CnsTest {

	@Test
	public void testFromString() {
		assertNotNull(Cns.fromString("898000000043208"));
		assertNotNull(Cns.fromString("190129759240018"));
		assertNotNull(Cns.fromString("898001038954985"));
		assertNotNull(Cns.fromString("100113736920003"));
		assertNotNull(Cns.fromString("898003701430618"));
		assertNotNull(Cns.fromString("898003701418022"));
		assertNotNull(Cns.fromString("898000752885083"));
		assertNotNull(Cns.fromString("898003708105771"));
		assertNotNull(Cns.fromString("201597135300005"));
		assertNotNull(Cns.fromString("801434364369875"));
		assertNotNull(Cns.fromString("207015780540004"));
		assertNotNull(Cns.fromString("209845719350003"));
		assertNotNull(Cns.fromString("209868015390000"));
		assertNotNull(Cns.fromString("801434363411304"));
		assertNotNull(Cns.fromString("706805267673923"));
		assertNotNull(Cns.fromString("700805904091181"));
		assertNotNull(Cns.fromString("210226354000008"));
		assertNotNull(Cns.fromString("203478579780008"));
		assertNotNull(Cns.fromString("206139627900002"));
		assertNotNull(Cns.fromString("170121420980005"));
		assertNotNull(Cns.fromString("209845737170003"));
		assertNotNull(Cns.fromString("102274884040008"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringDefinitivoInvalido() {
		assertNotNull(Cns.fromString("190129759240017"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringProvisorioInvalido() {
		assertNotNull(Cns.fromString("190129759240015"));
	}

}