package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class AssertIdentificationTests{

	private TestClassAdapter tests;

	@Test
	void zero_when_contains_no_asserts() throws Exception {
		tests = TestLocator.loadTestClass("SingleTest");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(0));
	}

	@Test
	void two_when_contains_two_asserts() throws Exception {
		tests = TestLocator.loadTestClass("SingleTestWithTwoAsserts");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(2));
	}

	@Test
	void one_when_contains_a_single_assert_with_two_tests() throws Exception {
		tests = TestLocator.loadTestClass("TwoTestWithOneAssert");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(0));
		assertThat(tests.getInfoForTest(1).totalAssertCount, is(1));
	}

	
	@Test
	void correct_count_on_combo() throws Exception {
		tests = TestLocator.loadTestClass("TwoTestWithOneAssertAndMethods");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(0));
		assertThat(tests.getInfoForTest(1).totalAssertCount, is(1));
	}
	


	/*

	
	@Test
	void test_only_assertnotnull() {}

	
	@Test
	void test_status_and_only_assertnotnull() {}

 */
}