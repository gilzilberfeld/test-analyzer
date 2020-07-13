package testanalyzer.examples.asserts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class InternalAssertsWithJUnit4Exception {

	@Test(expected = NullPointerException.class)
	void test_1() {
		assertEquals(1,1);
		internalCall();
	}

	private void internalCall() {
		assertEquals(2,2);
	}

}
