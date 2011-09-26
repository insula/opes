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

import static org.junit.Assert.*;

import org.junit.Test;

public class AgenciaTest {

	@Test
	public void testToString() {
		Agencia agencia = Agencia.fromString("3522");
		assertEquals("3522", agencia.toString());
	}

	@Test
	public void testFromString() {
		Agencia agencia = Agencia.fromString("0975-X");
		assertEquals("975X", agencia.toString());
	}

	@Test
	public void testFromStringWithManyLeadingZeroes() {
		Agencia agencia = Agencia.fromString("00035122");
		assertEquals("35122", agencia.toString());
	}

	@Test
	public void testFormatToAlternate() {
		Agencia agencia = Agencia.fromString("352-2");
		assertEquals("003522", String.format("%#6s", agencia));
	}

	@Test
	public void testFormatTo() {
		Agencia agencia = Agencia.fromString("352-2");
		assertEquals("352-2", String.format("%s", agencia));
		assertEquals("  352-2", String.format("%7s", agencia));
		assertEquals("352-2   ", String.format("%-8s", agencia));
	}

}