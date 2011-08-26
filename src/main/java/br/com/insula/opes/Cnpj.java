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

import java.io.Serializable;

import br.com.insula.opes.util.Assert;

public class Cnpj implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int[] multiplicadoresPrimeiroDigito = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static final int[] multiplicadoresSegundoDigito = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private final String numero;

	private Cnpj(String numero) {
		this.numero = numero;
	}

	public static Cnpj fromString(String s) {
		Assert.notNull(s);
		String digits = s.replaceAll("\\D", "");
		Assert.matches("\\d{14}", digits);
		Assert.isTrue(isValid(digits));
		return new Cnpj(digits);
	}

	static boolean isValid(String digits) {
		char[] numbers = digits.toCharArray();
		int soma = 0;

		for (int i = 0; i < multiplicadoresPrimeiroDigito.length; i++) {
			soma += Character.digit(numbers[i], 10) * multiplicadoresPrimeiroDigito[i];
		}

		int resto = soma % 11;
		if (resto < 2) {
			if (Character.digit(numbers[12], 10) != 0) {
				return false;
			}
		}
		else {
			if (Character.digit(numbers[12], 10) != 11 - resto) {
				return false;
			}
		}

		soma = 0;
		for (int i = 0; i < multiplicadoresSegundoDigito.length; i++) {
			soma += Character.digit(numbers[i], 10) * multiplicadoresSegundoDigito[i];
		}

		resto = soma % 11;
		if (resto < 2) {
			if (Character.digit(numbers[13], 10) != 0) {
				return false;
			}
		}
		else {
			if (Character.digit(numbers[13], 10) != 11 - resto) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s.%s.%s/%s-%s", numero.substring(0, 2), numero.substring(2, 5), numero.substring(5, 8),
				numero.substring(8, 12), numero.substring(12));
	}

	public String getNumero() {
		return numero;
	}

}