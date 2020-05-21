package testanalyzer.examples.asserts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoTestWithOneAssertAndMethods {

	@Test
	public void test1() {
	}
	
	@Test
	public void test2() {
		assertEquals(1,1);
	}
	
	public void publicMethod() {
	}
	
	public void privateMethod() {
	}
}
