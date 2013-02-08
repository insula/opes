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

public class TituloEleitor implements Serializable, Formattable, Comparable<TituloEleitor> {

	private static final long serialVersionUID = 1L;

	private final String numero;

	private TituloEleitor(String numero) {
		this.numero = numero;
	}

	public static TituloEleitor fromString(String s) {
		checkNotNull(s);
		String digits = s.replaceAll("\\D", "").replaceAll("^0+", "");
		checkArgument(digits.matches("\\d{1,9}([0,1][0-9]|2[0-8])\\d{2}"));
		checkArgument(!digits.matches("(\\d)\\1+"));
		checkArgument(isValid(digits));
		return new TituloEleitor(digits);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Cpf) {
			TituloEleitor other = (TituloEleitor) obj;
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
		return numero;
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		StringBuilder sb = new StringBuilder();
		boolean alternate = (flags & ALTERNATE) == ALTERNATE;
		if (alternate) {
			sb.append(numero);
		}
		else {
			int length = numero.length();
			sb.append(String.format("%s-%s", numero.substring(0, length - 2), numero.substring(length - 2)));
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
	public int compareTo(TituloEleitor o) {
		return this.numero.compareTo(o.numero);
	}

	static boolean isValid(String digits) {
		String padded = String.format("%013d", Long.parseLong(digits));
		return primeiraValidacao(padded) && segundaValidacao(padded);
	}

	static boolean segundaValidacao(String padded) {
		if (segundoDigito(padded) == 0) {
			return (segundaSoma(padded) + segundoDigito(padded)) % 11 < 2;
		}
		else {
			return (segundaSoma(padded) + segundoDigito(padded)) % 11 == 0;
		}
	}

	static boolean primeiraValidacao(String padded) {
		if (primeiroDigito(padded) == 0) {
			return (primeiraSoma(padded) + primeiroDigito(padded)) % 11 < 2;
		}
		else {
			return (primeiraSoma(padded) + primeiroDigito(padded)) % 11 == 0;
		}
	}

	static int segundaSoma(String padded) {
		return somaPonderada(padded.substring(9, 12));
	}

	static int segundoDigito(String padded) {
		return Integer.parseInt(padded.substring(12, 13));
	}

	static int primeiraSoma(String padded) {
		return somaPonderada(padded.substring(0, 9));
	}

	static int primeiroDigito(String padded) {
		return Integer.parseInt(padded.substring(11, 12));
	}

	static int somaPonderada(String s) {
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[cs.length - i - 1], 10) * (i % 8 + 2);
		}
		return soma;
	}

}