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
@TypeDefs({
	@TypeDef(name="cep", defaultForType = Cep.class, typeClass=CepUserType.class),
	@TypeDef(name="cnpj", defaultForType = Cnpj.class, typeClass=CnpjUserType.class),
	@TypeDef(name="cpf", defaultForType = Cpf.class, typeClass=CpfUserType.class),
	@TypeDef(name="cpfCnpj", defaultForType = CpfCnpj.class, typeClass=CpfCnpjUserType.class),
	@TypeDef(name="email", defaultForType = Email.class, typeClass=EmailUserType.class),
	@TypeDef(name="telefone", defaultForType = Telefone.class, typeClass=TelefoneUserType.class)
})
package br.com.insula.opes.hibernate.usertype;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.com.insula.opes.Cep;
import br.com.insula.opes.Cnpj;
import br.com.insula.opes.Cpf;
import br.com.insula.opes.CpfCnpj;
import br.com.insula.opes.Email;
import br.com.insula.opes.Telefone;

