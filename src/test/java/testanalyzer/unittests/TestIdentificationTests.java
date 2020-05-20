package testanalyzer.unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.Tests;
import testanalyzer.TestsLoader;

class TestIdentificationTests {

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private Tests tests;

	@BeforeAll
	public static void setup() {
		TestsLoader.Root = ROOT_PATH;
	}
	
	@Test
	void zero_on_empty_class() throws Exception {
		tests = TestsLoader.loadClass("EmptyClass");
		countShouldBe(0);
	}

	
	
	@Test
	void zero_on_non_test_class() throws FileNotFoundException {
		tests = TestsLoader.loadClass("NonTestClass");
		countShouldBe(0);
	}
	
	
	@Test
	void one_on_test_class_with_one_test() throws FileNotFoundException {
		tests = TestsLoader.loadClass("SingleTest");
		countShouldBe(1);
	}

	@Test
	void one_on_test_class_with_one_test_and_one_method() throws FileNotFoundException {
		tests = TestsLoader.loadClass("SingleTestOneMethod");
		countShouldBe(1);
	}
	
	
	@Test
	void zero_on_ignored_tests() throws FileNotFoundException {
		tests = TestsLoader.loadClass("SingleIgnoredTest");
		countShouldBe(0);
	}
	
	@Test
	void two_on_test_class_with_two_tests_and_three_methods_and_ignored_tests() throws FileNotFoundException {
		tests = TestsLoader.loadClass("ComboTest");
		countShouldBe(2);
	}
	
	private void countShouldBe(int expected) {
		assertThat(tests.getCount(), is(expected));
	}

	@Test
	void zero_on_ignored_tests_junit4() throws FileNotFoundException {
		var tests = TestsLoader.loadClass("SingleIgnoredJunit4Test");
		assertThat(tests.getCount(), is(0));
	}

	/*
	
Spring identification: Can do slices, how are "regular" API tests written in bank?
	
	*/

}
