package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class InternalAssertsTests {

	@Test
	void testAfterInternalMethod_returns1() throws Exception {
		TestClassAdapter tests = TestLocator.loadTestClass("TestsWithInternalAssert");
		assertThat(tests.getInfoForTest(0).assertCount, is(1));
	}

	// twoTestsWithDifferentAsserts_returns1
	// twoTestsWithCommonAssert_returns1
	// singleTestWithAssert_returns2
	// singleTestWithInternal2Asserts_returns2
}
