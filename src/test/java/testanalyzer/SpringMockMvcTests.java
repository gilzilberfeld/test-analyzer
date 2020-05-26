package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.model.Tests;

class SpringMockMvcTests {

	private Tests tests;

	@Test
	void zero_when_perform_and_no_expects() throws Exception {
		tests = TestLocator.loadTestClass("SpringTestWithoutExpect");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
	}

	@Test
	void one_when_perform_with_single_expect() throws Exception {
		tests = TestLocator.loadTestClass("SpringTestWithSingleExpect");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

	@Test
	void one_when_perform_with_two_expect() throws Exception {
		tests = TestLocator.loadTestClass("SpringTestWithTwoExpects");
		assertThat(tests.qualityDataFor(0).assertCount, is(2));
	}

	@Test
	void multiple_tests_with_multiple_performs() throws Exception {
		tests = TestLocator.loadTestClass("MultipleTestsWithMultipleExpects");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
		assertThat(tests.qualityDataFor(1).assertCount, is(0));
		assertThat(tests.qualityDataFor(2).assertCount, is(2));
		assertThat(tests.qualityDataFor(3).assertCount, is(4));
		assertThat(tests.qualityDataFor(4).assertCount, is(3));
	}

}
