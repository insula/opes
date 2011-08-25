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

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CpfCnpjTest {

	@Test
	public void testFromStringValid() {
		String[] validCpfCnpjs = { "005.333.839-19", "00533383919", "030.405.039-36", "03040503936", "046.428.359-03",
				"04642835903", "023.750.169-47", "02375016947", "855.826.525-90", "669.712.265-00",
				"06.305.901/0001-78", "06305901000178", "23.144.170/0001-45", "23144170000145", "46.868.328/0001-25",
				"42.767.194/0001-03", "58.647.246/0001-30", "37.961.612/0001-50", "43.256.675/0001-09",
				"24.152.237/0001-56", "60.871.888/0001-60", "66.845.982/0001-20", "06.074.614/0001-02",
				"06074614000102" };
		for (String cpfCnpj: validCpfCnpjs) {
			assertNotNull(CpfCnpj.fromString(cpfCnpj));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith10Digits() {
		CpfCnpj.fromString("0123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith12Digits() {
		CpfCnpj.fromString("012345678901");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWith15Digits() {
		CpfCnpj.fromString("012345678901234");
	}

}
