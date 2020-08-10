package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class SpringMockMvcTests {

	private TestClassAdapter tests;

	@Test
	void zero_when_perform_and_no_expects() throws Exception {
		tests = TestLocator.loadTestClass("SpringTestWithoutExpect");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(0));
	}

	@Test
	void one_when_perform_with_single_expect() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(1));
	}

	@Test
	void one_when_perform_with_two_expect() throws Exception {
		tests = TestLocator.loadTestClass("SpringTestWithTwoExpects");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(2));
	}

	@Test
	void multiple_tests_with_multiple_performs() throws Exception {
		tests = TestLocator.loadTestClass("MultipleTestsWithMultipleExpects");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(1));
		assertThat(tests.getInfoForTest(1).totalAssertCount, is(0));
		assertThat(tests.getInfoForTest(2).totalAssertCount, is(2));
		assertThat(tests.getInfoForTest(3).totalAssertCount, is(4));
		assertThat(tests.getInfoForTest(4).totalAssertCount, is(3));
	}

}
