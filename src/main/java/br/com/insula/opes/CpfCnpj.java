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
import java.util.Formattable;
import java.util.Formatter;

import com.google.common.base.Objects;

public class CpfCnpj implements Serializable, Formattable {

	private static final long serialVersionUID = 1L;

	private final Cpf cpf;

	private final Cnpj cnpj;

	private CpfCnpj(Cpf cpf) {
		this.cpf = cpf;
		this.cnpj = null;
	}

	private CpfCnpj(Cnpj cnpj) {
		this.cpf = null;
		this.cnpj = cnpj;
	}

	public static CpfCnpj fromString(String s) {
		try {
			Cpf cpf = Cpf.fromString(s);
			return new CpfCnpj(cpf);
		}
		catch (IllegalArgumentException ex) {
		}

		try {
			Cnpj cnpj = Cnpj.fromString(s);
			return new CpfCnpj(cnpj);
		}
		catch (IllegalArgumentException ex) {
		}

		throw new IllegalArgumentException(String.format("CPF/CNPJ inv\u00e1lido: %s.", s));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof CpfCnpj) {
			CpfCnpj other = (CpfCnpj) obj;
			return Objects.equal(this.cpf, other.cpf) && Objects.equal(this.cnpj, other.cnpj);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.cpf, this.cnpj);
	}

	@Override
	public String toString() {
		if (cpf != null) {
			return cpf.toString();
		}
		else {
			return cnpj.toString();
		}
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		if (cpf != null) {
			cpf.formatTo(formatter, flags, width, precision);
		}
		else {
			cnpj.formatTo(formatter, flags, width, precision);
		}
	}

	public boolean isCpf() {
		return cpf != null;
	}

	public boolean isCnpj() {
		return cnpj != null;
	}

}