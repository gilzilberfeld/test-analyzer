package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestClassLocator;
import testanalyzer.model.Tests;

class ExceptionIdentificationTests {
	private Tests tests;

	
	@Test
	void one_when_contains_expected_annotation() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTestWithExpectedJUnit4");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

	
	@Test
	void one_when_contains_expected_rule_junit4() throws Exception {
		tests = TestClassLocator.loadTestClass("ExceptionRuleJunit4Test");
		assertThat(tests.qualityDataFor(0).assertCount, is(1));
	}

}
