package testanalyzer.examples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingleTestWithAssert {

	@Test
	public void test1() {
		assertEquals(1,1);
	}
}
