package testanalyzer.unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.TestClassLocator;
import testanalyzer.Tests;

class ExceptionIdentificationTests {
	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private Tests tests;

	@BeforeAll
	public static void setup() {
		TestClassLocator.Root = ROOT_PATH;
	}

	@Test
	void one_when_contains_expected_annotation() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleTestWithExpectedJUnit4");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

	@Test
	void zero_when_ignored_and_contains_expected_annotation() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("SingleIgnoredTestWithExpected");
		assertThat(tests.qualityDataFor(0).assertCount, is(0));
	}
	
	@Test
	void one_when_contains_expected_rule_junit4() throws FileNotFoundException {
		tests = TestClassLocator.loadTestClass("ExceptionRuleJunit4Test");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

}
