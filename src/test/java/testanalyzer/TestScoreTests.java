package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.TestType;

class TestScoreTests {

	private TestClassAdapter tests;
	private int testNumber;
	private int expected;

	@BeforeEach
	public void setup() throws Exception {
		tests = TestLocator.loadTestClass("NotNullAssertTests");
	
	}
	
	@Test
	void withoutAssertNulls_assertNullCountIsZero() throws Exception {
		testNumber(0).has(0).notNullAsserts();
	}

	@Test
	void withOneAssertNulls_assertNullCountIsOne() throws Exception {
		testNumber(1).has(1).notNullAsserts();
	}


	@Test
	void withTwoDifferentAssertNulls_assertNullCountIsTwo() throws Exception {
		testNumber(2).has(2).notNullAsserts();
	}

	private TestScoreTests testNumber(int tn) {
		this.testNumber = tn;
		return this;
	}

	private TestScoreTests has(int expected) {
		this.expected = expected;
		return this;
	}

	private void notNullAsserts() throws Exception {
		assertThat(tests.getInfoForTest(this.testNumber).assertNotNullCount, 
				is (this.expected));
		
	}

}
