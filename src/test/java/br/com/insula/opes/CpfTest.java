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

public class CpfTest {

	@Test
	public void testFromStringValid() {
		String[] validCpfs = { "005.333.839-19", "00533383919", "030.405.039-36", "03040503936", "046.428.359-03",
				"04642835903", "023.750.169-47", "02375016947", "855.826.525-90", "669.712.265-00", "01646227212",
				"96183390259", "63118670282", "57212970263", "85272175204", "84250739287", "74390651234",
				"93582803287", "84569190200", "51914794249", "67681530215", "51918102287", "59925272220",
				"72178507204", "85542520200", "98089242200", "66100313200", "51405300230", "13187110703" };
		for (String cpf : validCpfs) {
			assertNotNull(Cpf.fromString(cpf));
		}
	}

	@Test
	public void testRepetidos() {
		String[] repetidos = { "00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
				"55555555555", "66666666666", "77777777777", "88888888888", "99999999999" };
		for (String cpf : repetidos) {
			try {
				Cpf.fromString(cpf);
				fail();
			}
			catch (IllegalArgumentException ex) {
				assertNotNull(ex);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith10Digits() {
		Cpf.fromString("0123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith12Digits() {
		Cpf.fromString("012345678901");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith15Digits() {
		Cpf.fromString("012345678901234");
	}

	@Test
	public void testToString() {
		assertEquals("00533383919", Cpf.fromString("00533383919").toString());
		assertEquals("03040503936", Cpf.fromString("03040503936").toString());
	}

	@Test
	public void testFormatTo() {
		assertEquals("005.333.839-19", String.format("%s", Cpf.fromString("00533383919")));
		assertEquals("030.405.039-36", String.format("%s", Cpf.fromString("03040503936")));
	}

	@Test
	public void testFormatToAlternate() {
		assertEquals("000000533383919", String.format("%#15s", Cpf.fromString("00533383919")));
	}

	@Test
	public void testEquals() {
		assertEquals(CpfCnpj.fromString("00533383919"), CpfCnpj.fromString("00533383919"));
	}

}