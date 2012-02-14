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

import org.junit.Test;

public class TelefoneTest {

	@Test
	public void testnewTelefone() {
		assertNotNull(Telefone.newTelefone("(44) 4052-9211"));
		assertNotNull(Telefone.newTelefone("+55 (44) 4052-9211"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testnewTelefoneComDddWithLessDigits() {
		Telefone.newTelefoneComDdd("4052-9211");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testnewTelefoneWithMoreDigits() {
		Telefone.newTelefone("12345678901234");
	}

	@Test
	public void testToString() {
		assertEquals("40529211", Telefone.newTelefone("40529211").toString());
		assertEquals("140529211", Telefone.newTelefone("140529211").toString());
		assertEquals("4440529211", Telefone.newTelefone("4440529211").toString());
		assertEquals("44405292110", Telefone.newTelefone("44405292110").toString());
		assertEquals("554440529211", Telefone.newTelefone("554440529211").toString());
		assertEquals("5544405292110", Telefone.newTelefone("5544405292110").toString());
	}

	@Test
	public void testFormatTo() {
		assertEquals("4052-9211", String.format("%s", Telefone.newTelefone("40529211")));
		assertEquals("1405-29211", String.format("%s", Telefone.newTelefone("140529211")));
		assertEquals("(44) 4052-9211", String.format("%s", Telefone.newTelefone("4440529211")));
		assertEquals("(44) 4052-92110", String.format("%s", Telefone.newTelefone("44405292110")));
		assertEquals("+55 (44) 4052-9211", String.format("%s", Telefone.newTelefone("554440529211")));
		assertEquals("+55 (44) 4052-92110", String.format("%s", Telefone.newTelefone("5544405292110")));
	}

}
