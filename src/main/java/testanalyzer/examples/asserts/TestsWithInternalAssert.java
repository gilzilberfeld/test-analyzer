package testanalyzer.examples.asserts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestsWithInternalAssert {

	
	private void internalCall() {
		assertEquals(1,1);
	}

	@Test
	void test_1() {
		internalCall();
	}

}
