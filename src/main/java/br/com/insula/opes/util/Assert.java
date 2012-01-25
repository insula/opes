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
package br.com.insula.opes.util;

public class Assert {

	private Assert() {
	}

	public static void notNull(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Argumento n\u00e3o pode ser nulo.");
		}
	}

	public static void hasText(String value) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("String n\u00e3o pode ser vazia.");
		}
	}

	public static void matches(String pattern, Object obj) {
		notNull(pattern);
		notNull(obj);
		if (!obj.toString().matches(pattern)) {
			throw new IllegalArgumentException("Objeto n\u00e3o confere com a express\u00e3o regular apresentada.");
		}
	}

	public static void isTrue(boolean condition) {
		if (!condition) {
			throw new IllegalArgumentException("Esta condi\u00e7\u00e3o deve ser verdadeira.");
		}
	}

}
