package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.model.TestClassType;
import testanalyzer.model.TestType;
import testanalyzer.parsing.TestClassAdapter;

class TestTypeTests {

	private TestClassAdapter tests;
	private int testNumber;
	@Test
	void noTestClassAnnotation_noRestTemplate_isUnitTest() throws Exception {
		tests = TestLocator.loadTestClass("SingleTestWithAssert");
		testNumber(0).isType(TestType.Unit);
	}
	
	@Test
	void noTestClassAnnotation_withRestTemplate_isApiTest() throws Exception {
		tests = TestLocator.loadTestClass("SingleTestWithRestTemplate");
		testNumber(0).isType(TestType.API);
	}
	
	@Test
	void springBootTestClass_withPerform_isAPITest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testNumber(0).isType(TestType.API);
	}
	
	

	@Test
	void springBootTestClass_noPerform_isSpringTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testNumber(1).isType(TestType.Spring);
	}

	@Test
	void springBootTestClass_withRestTemplate_isApiTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testNumber(2).isType(TestType.API);
	}
	
	@Test
	void springBootTestClass_withPerformAndRestTemplate_isAPITest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testNumber(3).isType(TestType.API);
	}
	@Test
	void springRunnerClass_noRestTemplateCall_isSpringTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringRunnerTest");
		testNumber(0).isType(TestType.Spring);
	}

	@Test
	void springRunnerClass_withRestTemplateCall_isApiTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringRunnerTest");
		testNumber(1).isType(TestType.API);
		testNumber(2).isType(TestType.API);
		testNumber(3).isType(TestType.API);
		testNumber(4).isType(TestType.API);
		testNumber(5).isType(TestType.API);
	}

	private void isType(TestType type) throws Exception {
		assertThat(tests.getInfoForTest(testNumber).type, is (type));
	}
	
	private TestTypeTests testNumber(int i) {
		this.testNumber = i;
		return this;
	}

}
