package testanalyzer.unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.Tests;
import testanalyzer.TestClassLocator;

class AssertIdentificationTests{

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private Tests tests;

	@BeforeAll
	public static void setup() {
		TestClassLocator.Root = ROOT_PATH;
	}

	@Test
	void one_when_contains_expected_annotation() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleTestWithExpected");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

	@Test
	void zero_when_ignored_and_contains_expected_annotation() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleIgnoredTestWithExpected");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
	}

	@Test
	void zero_when_contains_no_asserts() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleTest");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
	}

	@Test
	void two_when_contains_two_asserts() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleTestWithTwoAsserts");
		assertThat(tests.qualityDataFor(0).assertCount, is(2));
	}

	@Test
	void one_when_contains_a_single_assert_with_two_tests() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("TwoTestWithOneAssert");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
		assertThat(tests.qualityDataFor(1).assertCount, is(1));
	}

	
	@Test
	void correct_count_on_combo() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("TwoTestWithOneAssertAndMethods");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
		assertThat(tests.qualityDataFor(1).assertCount, is(1));
	}
	
	/*
	
	void one_when_contains_expected_rule() {
		// check example, could be an explicit assert there
	}




	@Test
	void two_when_contains_a_two_asserts() {
	}
	
	
	@Test
	void test_contains_perform_and_checks_status_only() {
	}

	@Test
	void test_contains_perform_and_does_not_check() {
	}
	
	@Test
	void test_contains_perform_and_checks_status_and_response() {
	}

 */
}