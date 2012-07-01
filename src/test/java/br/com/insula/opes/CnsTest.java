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
import static org.junit.Assert.fail;

import org.junit.Test;

public class CnsTest {

	@Test
	public void testFromString() {
		assertNotNull(Cns.fromString("898000000043208"));
		assertNotNull(Cns.fromString("190129759240018"));
		assertNotNull(Cns.fromString("898001038954985"));
		assertNotNull(Cns.fromString("100113736920003"));
		assertNotNull(Cns.fromString("898003701430618"));
		assertNotNull(Cns.fromString("898003701418022"));
		assertNotNull(Cns.fromString("898000752885083"));
		assertNotNull(Cns.fromString("898003708105771"));
		assertNotNull(Cns.fromString("201597135300005"));
		assertNotNull(Cns.fromString("801434364369875"));
		assertNotNull(Cns.fromString("207015780540004"));
		assertNotNull(Cns.fromString("209845719350003"));
		assertNotNull(Cns.fromString("209868015390000"));
		assertNotNull(Cns.fromString("801434363411304"));
		assertNotNull(Cns.fromString("706805267673923"));
		assertNotNull(Cns.fromString("700805904091181"));
		assertNotNull(Cns.fromString("210226354000008"));
		assertNotNull(Cns.fromString("203478579780008"));
		assertNotNull(Cns.fromString("206139627900002"));
		assertNotNull(Cns.fromString("170121420980005"));
		assertNotNull(Cns.fromString("209845737170003"));
		assertNotNull(Cns.fromString("102274884040008"));
		assertNotNull(Cns.fromString("102528846360018"));
		assertNotNull(Cns.fromString("125214400290018"));
		assertNotNull(Cns.fromString("123203468660018"));
		assertNotNull(Cns.fromString("170151402820018"));
		assertNotNull(Cns.fromString("108377694740018"));
		assertNotNull(Cns.fromString("122857340360018"));
	}

	@Test
	public void testIsRepetido() {
		String[] values = { "000000000000000", "111111111111111", "222222222222222", "333333333333333",
				"777777777777777", "888888888888888", "999999999999999" };
		for (String value : values) {
			try {
				Cns.fromString(value);
				fail();
			}
			catch (IllegalArgumentException ex) {
				assertNotNull(ex);
			}
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringDefinitivoInvalido() {
		assertNotNull(Cns.fromString("190129759240017"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromStringProvisorioInvalido() {
		assertNotNull(Cns.fromString("190129759240015"));
	}

}