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

public class CpfCnpj implements Serializable {

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
		} catch (IllegalArgumentException ex) {
		}

		try {
			Cnpj cnpj = Cnpj.fromString(s);
			return new CpfCnpj(cnpj);
		} catch (IllegalArgumentException ex) {
		}

		throw new IllegalArgumentException("CPF/CNPJ inv\u00e1lido.");
	}

	public String getNumero() {
		if (cpf != null) {
			return cpf.getNumero();
		} else {
			return cnpj.getNumero();
		}
	}

	public boolean isCpf() {
		return cpf != null;
	}

	public boolean isCnpj() {
		return cnpj != null;
	}

}