package br.com.insula.opes.hibernate.usertype;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

public class RangeUserType extends ImmutableUserType {

	private static final long serialVersionUID = 1L;

	private static final String REGEXP = "([\\[\\(]\\d+|\\(-∞)‥(\\d+[\\]\\)]|\\+∞\\))";

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		else {
			return createRange(value);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		}
		else {
			@SuppressWarnings("unchecked")
			Range<Integer> range = (Range<Integer>) value;
			st.setString(index, createString(range));
		}
	}

	Range<Integer> createRange(String value) {
		checkArgument(value.matches(REGEXP));
		String[] split = value.split("‥");
		return createLowerRange(split[0]).intersection(createUpperRange(split[1]));
	}

	private Range<Integer> createLowerRange(String value) {
		if (value.contains("∞")) {
			return Ranges.all();
		}
		else {
			int start = Integer.parseInt(value.replaceAll("[\\[\\(]", ""));
			if (value.startsWith("(")) {
				return Ranges.downTo(start, BoundType.OPEN);
			}
			else {
				return Ranges.downTo(start, BoundType.CLOSED);
			}
		}
	}

	private Range<Integer> createUpperRange(String value) {
		if (value.contains("∞")) {
			return Ranges.all();
		}
		else {
			int end = Integer.parseInt(value.replaceAll("[\\]\\)]", ""));
			if (value.endsWith(")")) {
				return Ranges.upTo(end, BoundType.OPEN);
			}
			else {
				return Ranges.upTo(end, BoundType.CLOSED);
			}
		}
	}

	String createString(Range<Integer> range) {
		return range.toString();
	}

	@Override
	public Class<?> returnedClass() {
		return Range.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

}