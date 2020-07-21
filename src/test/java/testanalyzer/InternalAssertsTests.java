package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class InternalAssertsTests {

	private TestClassAdapter tests;
	int testNumber;
	String testName;
	int expectedAsserts;

	@BeforeEach
	public void setup() throws Exception {
		tests = TestLocator.loadTestClass("TestsWithInternalAssert");
	}
	
	@Test
	void testBeforeInternalMethod_returns1() throws Exception {
		testNumber(0).withName("test_1").has(1).asserts();
	}

	@Test
	void testAfterInternalMethod_returns1() throws Exception {
		testNumber(1).withName("test_2").has(1).asserts();
	}

	
	@Test
	public void testsWithDifferentInternalAsserts() throws Exception {
		testNumber(0).withName("test_1").has(1).asserts();
		testNumber(2).withName("test_3").has(2).asserts();
	}
	
	@Test
	public void singleTestWithAssert_incrementCount() throws Exception {
		testNumber(3).withName("test_4").has(3).asserts();
	}
	
	@Test
	public void singleTestWithTwoInternalAssert_incrementCount() throws Exception {
		testNumber(4).withName("test_5").has(3).asserts();
	}
	
	@Test
	public void junit4TestWithInternalAndException_incrementCount() throws Exception {
		tests = TestLocator.loadTestClass("InternalAssertsWithJUnit4Exception");
		testNumber(0).withName("test_1").has(3).asserts();
	}
	
	private InternalAssertsTests testNumber(int number) {
		testNumber = number;
		return this;
	}

	private InternalAssertsTests withName(String name) {
		testName = name;
		return this;
	}

	private InternalAssertsTests has(int expected) throws Exception {
		expectedAsserts = expected;
		return this;
	}
	
	private void asserts() throws Exception {
		assertAssertCountFor(testNumber, testName, expectedAsserts);
	}

	private void assertAssertCountFor(int testID, String testName, int assertCount) throws Exception {
		assertThat(tests.getInfoForTest(testID).testName, is(testName));
		assertThat(tests.getInfoForTest(testID).assertCount, is(assertCount));
	}
}
