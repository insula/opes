package br.com.insula.opes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import com.google.common.base.Objects;

public class InscricaoEstadual implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String value;

	private InscricaoEstadual(String value) {
		this.value = value;
	}

	public static InscricaoEstadual fromString(String s) {
		checkNotNull(s);
		String upperCase = s.toUpperCase();
		if ("ISENTO".equals(upperCase)) {
			return new InscricaoEstadual(upperCase);
		}
		else {
			String digits = s.replaceAll("\\D", "");
			checkArgument(digits.matches("\\d{2,14}"));
			return new InscricaoEstadual(digits);
		}
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof InscricaoEstadual) {
			InscricaoEstadual other = (InscricaoEstadual) obj;
			return Objects.equal(this.value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.value);
	}

}