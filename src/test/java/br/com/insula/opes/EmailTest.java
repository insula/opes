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

public class EmailTest {

	@Test
	public void testFromString() {
		assertNotNull(Email.fromString("yanaga@insula.com.br"));
		assertNotNull(Email.fromString("teste.domain@insula.com.br"));
		assertNotNull(Email.fromString("user123_321@insula.com.br"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutUser() {
		Email.fromString("@insula.com.br");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutDomain() {
		Email.fromString("user@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringWithoutAt() {
		Email.fromString("user.teste");
	}

	@Test
	public void testToString() {
		assertEquals("yanaga@insula.com.br", Email.fromString("yanaga@insula.com.br").toString());
	}

}
