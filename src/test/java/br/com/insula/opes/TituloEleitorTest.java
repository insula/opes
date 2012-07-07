package br.com.insula.opes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TituloEleitorTest {

	@Test
	public void testFromString() {
		String[] titulos = { "0023950650604", "0017790950663", "0017618900680", "0065396460647", "0037249760620",
				"0072152690698", "0059002580612", "0016099951988", "0033071790698", "0058808170655", "0193359240132",
				"0018061160680", "0018060830680", "0104799580671", "0018164410620", "0027717750655", "0047053730698",
				"0027717940612", "0088229910620", "0071066850639" };
		for (String titulo : titulos) {
			assertNotNull(TituloEleitor.fromString(titulo));
		}
	}

	@Test
	public void testFromInvalidString() {
		String[] titulos = { "0023950652904", "0017790950661", "0017618900682", "00653964606473", "00372497606201",
				"0072152690697", "0059002580614", "0016099951984", "0033071790692", "0058808170654", "0193359240137",
				"0018061160681", "0018060830682", "0104799580673", "0018164410622", "0027717750653", "0047053730693",
				"0027717940613", "0088229910621", "0071066850635" };
		for (String titulo : titulos) {
			try {
				TituloEleitor.fromString(titulo);
				fail();
			}
			catch (IllegalArgumentException ex) {
				assertNotNull(ex);
			}
		}
	}

	@Test
	public void testToString() {
		assertEquals("23950650604", TituloEleitor.fromString("0023950650604").toString());
		assertEquals("239506506-04", String.format("%s", TituloEleitor.fromString("0023950650604")));
	}

}
