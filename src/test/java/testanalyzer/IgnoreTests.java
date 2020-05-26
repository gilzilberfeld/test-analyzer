package testanalyzer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.model.Tests;

public class IgnoreTests {

	private Tests tests;


	@Test
	void zero_on_ignored_tests() throws Exception {
		tests = TestLocator.loadTestClass("SingleIgnoredTest");
		countShouldBe(0);
	}
	

	@Test
	void zero_on_ignored_tests_junit4() throws Exception {
		tests = TestLocator.loadTestClass("SingleIgnoredJunit4Test");
		countShouldBe(0);
 	}

	private void countShouldBe(int expected) throws Exception {
		assertThat(tests.getCount(), is(expected));
	}

}
