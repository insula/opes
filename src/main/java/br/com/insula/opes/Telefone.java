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

public class Telefone implements Serializable, Formattable, Comparable<Telefone> {

	private static final long serialVersionUID = 1L;

	private final String numero;

	private Telefone(String numero) {
		this.numero = numero;
	}

	public static Telefone fromString(String s) {
		checkNotNull(s);
		String digits = s.replaceAll("\\D", "");
		checkArgument(digits.matches("1\\d{2}|1\\d{4}|0300\\d{8}|0800\\d{7,8}|\\d{8,13}"));
		return new Telefone(digits);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Telefone) {
			Telefone other = (Telefone) obj;
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

	public boolean isContemDdd() {
		if (numero.matches("0[3,8]\\d+")) {
			return false;
		}
		else {
			return numero.length() > 9;
		}
	}

	public boolean isContemDdi() {
		if (numero.matches("0[3,8]\\d+")) {
			return false;
		}
		else {
			return numero.length() > 11;
		}
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		StringBuilder sb = new StringBuilder();
		boolean alternate = (flags & ALTERNATE) == ALTERNATE;
		if (alternate) {
			sb.append(numero);
		}
		else {
			switch (numero.length()) {
			case 3:
				sb.append(numero);
				break;
			case 5:
				sb.append(numero);
				break;
			case 8:
				sb.append(String.format("%s-%s", numero.substring(0, 4), numero.substring(4)));
				break;
			case 9:
				sb.append(String.format("%s-%s", numero.substring(0, 5), numero.substring(5)));
				break;
			case 10:
				if (numero.matches("0800\\d+")) {
					sb.append(String.format("%s-%s-%s", numero.substring(0, 4), numero.substring(4, 6),
							numero.substring(6)));
				}
				else {
					sb.append(String.format("(%s) %s-%s", numero.substring(0, 2), numero.substring(2, 6),
							numero.substring(6)));
				}
				break;
			case 11:
				if (numero.matches("0[3,8]00\\d+")) {
					sb.append(String.format("%s-%s-%s", numero.substring(0, 4), numero.substring(4, 7),
							numero.substring(7)));
				}
				else {
					sb.append(String.format("(%s) %s-%s", numero.substring(0, 2), numero.substring(2, 7),
							numero.substring(7)));
				}
				break;
			case 12:
				sb.append(String.format("+%s (%s) %s-%s", numero.substring(0, 2), numero.substring(2, 4),
						numero.substring(4, 8), numero.substring(8)));
				break;
			case 13:
				sb.append(String.format("+%s (%s) %s-%s", numero.substring(0, 2), numero.substring(2, 4),
						numero.substring(4, 9), numero.substring(9)));
				break;
			}
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

	@Override
	public int compareTo(Telefone o) {
		return this.numero.compareTo(o.numero);
	}

}