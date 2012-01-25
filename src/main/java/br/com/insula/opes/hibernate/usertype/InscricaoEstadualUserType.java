package br.com.insula.opes.hibernate.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import br.com.insula.opes.InscricaoEstadual;

public class InscricaoEstadualUserType extends ImmutableUserType {

	private static final long serialVersionUID = 1L;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		else {
			return InscricaoEstadual.fromString(value);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		}
		else {
			InscricaoEstadual inscricaoEstadual = (InscricaoEstadual) value;
			st.setString(index, inscricaoEstadual.toString());
		}
	}

	@Override
	public Class<?> returnedClass() {
		return InscricaoEstadual.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

}