package br.com.insula.opes.hibernate.usertype;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.joda.time.DateMidnight;

import br.com.insula.opes.hibernate.usertype.ImmutableUserType;

public class DateMidnightUserType extends ImmutableUserType {

	private static final long serialVersionUID = 1L;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		Date value = rs.getDate(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		else {
			return new DateMidnight(value);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.DATE);
		}
		else {
			DateMidnight dateMidnight = (DateMidnight) value;
			st.setDate(index, new Date(dateMidnight.toDate().getTime()));
		}
	}

	@Override
	public Class<?> returnedClass() {
		return DateMidnight.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.DATE };
	}

}