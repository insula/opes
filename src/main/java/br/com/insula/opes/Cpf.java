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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.FormattableFlags.ALTERNATE;
import static java.util.FormattableFlags.LEFT_JUSTIFY;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;

import com.google.common.base.Objects;

public class Cpf implements Serializable, Formattable {

	private static final long serialVersionUID = 1L;

	private final String numero;

	private Cpf(String numero) {
		this.numero = numero;
	}

	public static Cpf fromString(String s) {
		checkNotNull(s);
		String digits = s.replaceAll("\\D", "");
		checkArgument(digits.matches("\\d{11}"));
		checkArgument(isValid(digits));
		return new Cpf(digits);
	}

	static boolean isValid(String digits) {
		char[] numbers = digits.toCharArray();
		int soma = 0;

		for (int i = 0; i < 9; i++) {
			soma += Character.digit(numbers[i], 10) * (10 - i);
		}

		int resto = soma % 11;
		if (resto < 2) {
			if (Character.digit(numbers[9], 10) != 0) {
				return false;
			}
		}
		else {
			if (Character.digit(numbers[9], 10) != 11 - resto) {
				return false;
			}
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.digit(numbers[i], 10) * (11 - i);
		}

		resto = soma % 11;
		if (resto < 2) {
			if (Character.digit(numbers[10], 10) != 0) {
				return false;
			}
		}
		else {
			if (Character.digit(numbers[10], 10) != 11 - resto) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Cpf) {
			Cpf other = (Cpf) obj;
			return Objects.equal(this.numero, other.numero);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.numero);
	}

	@Override
	public String toString() {
		return this.numero;
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		StringBuilder sb = new StringBuilder();
		boolean alternate = (flags & ALTERNATE) == ALTERNATE;
		if (alternate) {
			sb.append(numero);
		}
		else {
			sb.append(String.format("%s.%s.%s-%s", numero.substring(0, 3), numero.substring(3, 6),
					numero.substring(6, 9), numero.substring(9)));
		}
		int length = sb.length();
		if (length < width) {
			for (int i = 0; i < width - length; i++) {
				if (alternate) {
					sb.insert(0, '0');
				}
				else {
					boolean leftJustified = (flags & LEFT_JUSTIFY) == LEFT_JUSTIFY;
					if (leftJustified) {
						sb.append(' ');
					}
					else {
						sb.insert(0, ' ');
					}
				}
			}
		}
		formatter.format(sb.toString());
	}

}