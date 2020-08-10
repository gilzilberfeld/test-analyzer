package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class ExceptionIdentificationTests {
	private TestClassAdapter tests;

	
	@Test
	void one_when_contains_expected_annotation() throws Exception {
		tests = TestLocator.loadTestClass("SingleTestWithExpectedJUnit4");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(1));
	}

	
	@Test
	void one_when_contains_expected_rule_junit4() throws Exception {
		tests = TestLocator.loadTestClass("ExceptionRuleJunit4Test");
		assertThat(tests.getInfoForTest(0).totalAssertCount, is(1));
	}

}
