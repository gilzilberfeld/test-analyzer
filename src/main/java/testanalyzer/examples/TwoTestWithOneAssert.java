package testanalyzer.examples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoTestWithOneAssert {

	@Test
	public void test1() {
	}
	
	@Test
	public void test2() {
		assertEquals(1,1);
	}
}
