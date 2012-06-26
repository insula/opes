package br.com.insula.opes;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public class Cns implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final List<String> repetidos = ImmutableList.of("0{15}", "1{15}", "2{15}", "3{15}", "4{15}",
			"5{15}", "6{15}", "7{15}", "8{15}", "9{15}");

	private final String numero;

	private Cns(String numero) {
		this.numero = numero;
	}

	public static Cns fromString(String s) {
		checkNotNull(s);
		String digits = s.replaceAll("\\D", "");
		checkArgument(digits.matches("\\d{15}"));
		checkArgument(isValid(digits));
		return new Cns(digits);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Cpf) {
			Cns other = (Cns) obj;
			return Objects.equal(this.numero, other.numero);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.numero);
	}

	@Override
	public String toString() {
		return numero;
	}

	static boolean isValid(String s) {
		if (isRepetido(s)) {
			return false;
		}
		if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
			return somaPonderada(s) % 11 == 0;
		}
		return false;
	}

	static boolean isRepetido(String s) {
		for (String repetido : repetidos) {
			if (s.matches(repetido)) {
				return true;
			}
		}
		return false;
	}

	static int somaPonderada(String s) {
		checkArgument(s.length() <= 15);
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[i], 10) * (15 - i);
		}
		return soma;
	}

}