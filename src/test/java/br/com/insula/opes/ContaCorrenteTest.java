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

public class ContaCorrenteTest {

	@Test
	public void testToString() {
		ContaCorrente contaCorrente = ContaCorrente.fromString("3522");
		assertEquals("3522", contaCorrente.toString());
	}

	@Test
	public void testFromString() {
		ContaCorrente contaCorrente = ContaCorrente.fromString("0975-X");
		assertEquals("0975X", contaCorrente.toString());
	}

	@Test
	public void testFormatToAlternate() {
		ContaCorrente contaCorrente = ContaCorrente.fromString("352-2");
		assertEquals("003522", String.format("%#6s", contaCorrente));
	}

	@Test
	public void testFormatTo() {
		ContaCorrente contaCorrente = ContaCorrente.fromString("352-2");
		assertEquals("352-2", String.format("%s", contaCorrente));
		assertEquals("  352-2", String.format("%7s", contaCorrente));
		assertEquals("352-2   ", String.format("%-8s", contaCorrente));
	}

}