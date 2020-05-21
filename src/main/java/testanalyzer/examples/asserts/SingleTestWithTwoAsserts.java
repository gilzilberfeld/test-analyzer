package testanalyzer.examples.asserts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingleTestWithTwoAsserts {

	@Test
	public void test1() {
		assertEquals(1,1);
		assertTrue(true);
	}
}
