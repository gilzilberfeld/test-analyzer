package testanalyzer.unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import testanalyzer.TestClassLocator;
import testanalyzer.Tests;
import testanalyzer.parsing.exceptions.NoTestsFound;

class TestIdentificationTests {

	private Tests tests;

	@Test
	void zero_on_empty_class() throws Exception {
		tests = TestClassLocator.loadTestClass("EmptyClass");
		countShouldBe(0);
	}

	@Test
	void zero_on_non_test_class() throws Exception {
		tests = TestClassLocator.loadTestClass("NonTestClass");
		countShouldBe(0);
	}

	@Test
	void one_on_test_class_with_one_test() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTest");
		countShouldBe(1);
	}

	@Test
	void one_on_test_class_with_one_test_and_one_method() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTestOneMethod");
		countShouldBe(1);
	}

	@Test
	void two_on_test_class_with_two_tests_and_three_methods_and_ignored_tests() throws Exception {
		tests = TestClassLocator.loadTestClass("ComboTest");
		countShouldBe(2);
	}

	private void countShouldBe(int expected) throws Exception {
		assertThat(tests.getCount(), is(expected));
	}

		
	@Test
	void exception_when_accessing_data_when_no_tests() throws Exception
	{
		tests = TestClassLocator.loadTestClass("SingleIgnoredTestWithExpected");
		assertThrows(NoTestsFound.class, ()->tests.qualityDataFor(0));
	}


}
