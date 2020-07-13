package testanalyzer.examples.asserts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestsWithInternalAssert {

	
	@Test
	void test_1() {
		internalCall();
	}

	private void internalCall() {
		assertEquals(1,1);
	}

	@Test
	void test_2() {
		internalCall();
	}

	@Test
	public void test_3() {
		internalCall2();
	}
	
	public void internalCall2() {
		assertTrue(true);
		assertTrue(true);
	}
	
	@Test
	public void test_4() {
		assertEquals(6,6);
		internalCall2();
	}
	
	@Test
	public void test_5() {
		internalCall();
		internalCall2();
	}
	
}
