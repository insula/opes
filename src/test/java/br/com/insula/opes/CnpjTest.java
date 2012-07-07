/*
    This file is part of Opes.

    Opes is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Opes is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Opes.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.insula.opes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CnpjTest {

	@Test
	public void testFromStringValid() {
		String[] validCnpjs = { "06.305.901/0001-78", "06305901000178", "23.144.170/0001-45", "23144170000145",
				"46.868.328/0001-25", "42.767.194/0001-03", "58.647.246/0001-30", "37.961.612/0001-50",
				"43.256.675/0001-09", "24.152.237/0001-56", "60.871.888/0001-60", "66.845.982/0001-20",
				"06.074.614/0001-02", "06074614000102", "86.222.998/0001-94", "74.345.224/0001-71",
				"13.544.868/0001-02", "23.325.412/0001-05", "21.319.627/0001-80", "36.747.518/0001-30" };
		for (String cnpj : validCnpjs) {
			assertNotNull(Cnpj.fromString(cnpj));
		}
	}

	@Test
	public void testFromStringInvalid() {
		String[] validCnpjs = { "06.305.901/0001-77", "06305901000179", "23.144.170/0001-35", "23144170000155",
				"46.868.328/0001-26", "42.767.194/0001-13", "58.647.246/0001-31", "37.961.612/0001-51",
				"43.256.675/0001-08", "24.152.237/0001-57", "60.871.888/0001-61", "66.845.982/0001-21",
				"06.074.614/0001-12", "06074614000103", "86.222.998/0001-93", "74.345.224/0001-72",
				"13.544.868/0001-03", "23.325.412/0001-02", "21.319.627/0001-82", "36.747.518/0001-41" };
		for (String cnpj : validCnpjs) {
			try {
				Cnpj.fromString(cnpj);
			}
			catch (IllegalArgumentException ex) {
				assertNotNull(ex);
			}
		}
	}

	@Test
	public void testRepetidos() {
		String[] repetidos = { "00000000000000", "11111111111111", "22222222222222", "33333333333333",
				"44444444444444", "55555555555555", "66666666666666", "77777777777777", "88888888888888",
				"99999999999999" };
		for (String cnpj : repetidos) {
			try {
				Cnpj.fromString(cnpj);
				fail();
			}
			catch (IllegalArgumentException ex) {
				assertNotNull(ex);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith10Digits() {
		Cnpj.fromString("0123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith12Digits() {
		Cnpj.fromString("012345678901");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith15Digits() {
		Cnpj.fromString("012345678901234");
	}

	@Test
	public void testToString() {
		assertEquals("06305901000178", Cnpj.fromString("06305901000178").toString());
	}

	@Test
	public void testFormatTo() {
		assertEquals("06.305.901/0001-78", String.format("%s", Cnpj.fromString("06305901000178")));
	}

	@Test
	public void testFormatToAlternate() {
		assertEquals("006305901000178", String.format("%#15s", Cnpj.fromString("06305901000178")));
	}

	@Test
	public void testEquals() {
		assertEquals(CpfCnpj.fromString("06305901000178"), CpfCnpj.fromString("06.305.901/0001-78"));
	}

}
