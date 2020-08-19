package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class AssertOnStatusTests {

	private TestClassAdapter tests;
	private int testNumber;
	private int expected;

	@BeforeEach
	public void setup() throws Exception {
		tests = TestLocator.loadTestClass("StatusAssertTests");
	}

	@Test
	void noAssertOnStatus_zero() throws Exception {
		testNumber(0).has(0).assertsOnStatus();
	}

	@Test
	void assertEqualsOnVariableNamedStatus_one() throws Exception {
		testNumber(1).has(1).assertsOnStatus();
		testNumber(2).has(1).assertsOnStatus();
	}

	@Test
	void assertEqualsOnRestTemplateResponseEntityStatus_one() throws Exception {
		testNumber(3).has(1).assertsOnStatus();
	}

	/*
	
	@Test
	void OneAssertTruesOnStatus_RestTemplate() {
	}
	
	@Test
	void OneAssertOnStatus_mockMvc() {
	}
	
	@Test
	void SerializationTest() {
	}
	
	*/
	private AssertOnStatusTests testNumber(int tn) {
		this.testNumber = tn;
		return this;
	}

	private AssertOnStatusTests has(int expected) {
		this.expected = expected;
		return this;
	}

	private void assertsOnStatus() throws Exception {
		assertThat(tests.getInfoForTest(this.testNumber)
				.assertStatusCount, 
				is (this.expected));
	}

}
