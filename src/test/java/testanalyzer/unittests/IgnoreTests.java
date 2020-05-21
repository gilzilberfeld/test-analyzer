package testanalyzer.unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.TestClassLocator;
import testanalyzer.Tests;

public class IgnoreTests {

	private Tests tests;

	@BeforeAll
	public static void setup() {
	}

	@Test
	void zero_on_ignored_tests() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleIgnoredTest");
		countShouldBe(0);
	}
	

	@Test
	void zero_on_ignored_tests_junit4() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleIgnoredJunit4Test");
		countShouldBe(0);
 	}

	private void countShouldBe(int expected) {
		assertThat(tests.getCount(), is(expected));
	}

}
