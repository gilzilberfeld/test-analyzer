package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestClassLocator;

class AssertIdentificationTests{

	private Tests tests;

	@Test
	void zero_when_contains_no_asserts() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTest");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
	}

	@Test
	void two_when_contains_two_asserts() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTestWithTwoAsserts");
		assertThat(tests.qualityDataFor(0).assertCount, is(2));
	}

	@Test
	void one_when_contains_a_single_assert_with_two_tests() throws Exception {
		tests = TestClassLocator.loadTestClass("TwoTestWithOneAssert");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
		assertThat(tests.qualityDataFor(1).assertCount, is(1));
	}

	
	@Test
	void correct_count_on_combo() throws Exception {
		tests = TestClassLocator.loadTestClass("TwoTestWithOneAssertAndMethods");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
		assertThat(tests.qualityDataFor(1).assertCount, is(1));
	}
	

	/*

	
	@Test
	void test_only_assertnotnull() {}

	
	@Test
	void test_status_and_only_assertnotnull() {}

 */
}