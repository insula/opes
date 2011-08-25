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

public class CpfTest {

	@Test
	public void testFromStringValid() {
		String[] validCpfs = { "005.333.839-19", "00533383919", "030.405.039-36", "03040503936", "046.428.359-03",
				"04642835903", "023.750.169-47", "02375016947", "855.826.525-90", "669.712.265-00", };
		for (String cpf : validCpfs) {
			assertNotNull(Cpf.fromString(cpf));
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

}
