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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.Ranges;

public class CepTest {

	@Test
	public void testFromString() {
		Cep cep = Cep.fromString("87030-020");
		assertNotNull(cep);
		assertEquals("87030020", cep.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromInvalidString() {
		Cep.fromString("8703002");
	}

	@Test
	public void testFormatToAlternate() {
		Cep cep = Cep.fromString("87030-020");
		assertEquals("87030020", String.format("%#s", cep));
	}

	@Test
	public void testFormatTo() {
		Cep cep = Cep.fromString("87030-020");
		assertEquals("87030-020", String.format("%s", cep));
	}

	@Test
	public void testCompareTo() {
		Cep inicio = Cep.fromString("80000000");
		Cep termino = Cep.fromString("87999999");
		assertTrue(inicio.compareTo(termino) < 0);
		assertTrue(termino.compareTo(inicio) > 0);
		assertTrue(inicio.compareTo(inicio) == 0);

		assertTrue(Ranges.closed(inicio, termino).contains(Cep.fromString("87030020")));
		assertFalse(Ranges.closed(inicio, termino).contains(Cep.fromString("88000000")));
		assertFalse(Ranges.closed(inicio, termino).contains(Cep.fromString("79999999")));
	}

}