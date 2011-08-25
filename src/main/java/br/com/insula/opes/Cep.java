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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.insula.opes.util.Assert;

public class Cep implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String numero;

	private Cep(String numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Cep) {
			Cep other = (Cep) obj;
			return new EqualsBuilder().append(this.numero, other.numero).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.numero).toHashCode();
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", numero.substring(0, 5), numero.substring(5));
	}

	public static Cep fromString(String s) {
		Assert.notNull(s);
		String digits = s.replaceAll("\\D", "");
		Assert.matches("\\d{8}", digits);

		return new Cep(digits);
	}

}
