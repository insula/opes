package br.com.insula.opes.hibernate.usertype;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

public class RangeUserTypeTest {

	private RangeUserType rangeUserType = new RangeUserType();

	@Test
	public void testCreateRange() {
		assertEquals(Ranges.downTo(3, BoundType.OPEN), rangeUserType.createRange("(3‥+∞)"));
		assertEquals(Ranges.downTo(2, BoundType.CLOSED), rangeUserType.createRange("[2‥+∞)"));
		assertEquals(Ranges.openClosed(3, 17), rangeUserType.createRange("(3‥17]"));
		assertEquals(Ranges.open(3, 72), rangeUserType.createRange("(3‥72)"));
		assertEquals(Ranges.closedOpen(2, 4), rangeUserType.createRange("[2‥4)"));
		assertEquals(Ranges.closed(2, 4), rangeUserType.createRange("[2‥4]"));
		assertEquals(Ranges.upTo(5, BoundType.CLOSED), rangeUserType.createRange("(-∞‥5]"));
		assertEquals(Ranges.upTo(5, BoundType.OPEN), rangeUserType.createRange("(-∞‥5)"));
	}
	
	@Test
	public void testCreateRangeFromString() {
		Range<Integer> range = Ranges.downTo(3, BoundType.OPEN);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.downTo(2, BoundType.CLOSED);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.openClosed(3, 17);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.open(3, 72);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.closedOpen(2, 4);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.closed(2, 4);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.upTo(5, BoundType.CLOSED);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
		range = Ranges.upTo(5, BoundType.OPEN);
		assertEquals(range, rangeUserType.createRange(rangeUserType.createString(range)));
	}

}