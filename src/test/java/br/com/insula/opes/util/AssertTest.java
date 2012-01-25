package br.com.insula.opes.util;

import org.junit.Test;

public class AssertTest {

	@Test
	public void testNotNull() {
		Assert.notNull(new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNotNullWithNull() {
		Assert.notNull(null);
	}

	@Test
	public void testHasText() {
		Assert.hasText("abc");
		Assert.hasText(" abc ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHasTextWithNull() {
		Assert.hasText(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHasTextWithBlank() {
		Assert.hasText("   ");
	}

	@Test
	public void testMatches() {
		Assert.matches("\\d+", "123");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatchesWithWrongInput() {
		Assert.matches("\\d+", "abc");
	}

	@Test
	public void testIsTrue() {
		Assert.isTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsTrueWithFalse() {
		Assert.isTrue(false);
	}

}
