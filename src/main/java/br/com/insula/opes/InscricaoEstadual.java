package br.com.insula.opes;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.insula.opes.util.Assert;

public class InscricaoEstadual implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String value;

	private InscricaoEstadual(String value) {
		this.value = value;
	}

	public static InscricaoEstadual fromString(String s) {
		Assert.hasText(s);
		String upperCase = s.toUpperCase();
		if ("ISENTO".equals(upperCase)) {
			return new InscricaoEstadual(upperCase);
		}
		else {
			String digits = s.replaceAll("\\D", "");
			Assert.isTrue(digits.matches("\\d{2,14}"));
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
			return new EqualsBuilder().append(this.value, other.value).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.value).toHashCode();
	}

}