package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.TestClassType;
import testanalyzer.parsing.TestType;

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
		tests = TestLocator.loadTestClass("SpringBootTestWithSingleExpect");
		testNumber(0).isType(TestType.API);
	}
	

	@Test
	void springBootTestClass_noPerform_isSpringTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTestWithSingleExpect");
		testNumber(1).isType(TestType.Spring);
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

	/*
	
	Class			|	Calls			|	Type
	None				None				Unit	v
						RestTemplate		API	
	
	Spring			|	None			|	Spring	v
					|	RestTemplate	| 	API		v
	
	SpringBootTest	|	None			|	Spring
					|	RestTemplate	| 	API
					|	Perform			|	API 	v
					| 	Both			|	API
	 */

	/*
	
	
	
	
	@Test
	void test_withTestClassAnnotation_andExchange_IsAPITest() {
		fail("Not yet implemented");
	}

	
	@Test
	void test_withTestClassAnnotation_noPerformOrExhange_IsSpringTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_withTestClassAnnotation_andPerformInCalledMethod_IsAPITest() {
		fail("Not yet implemented");
	}

	@Test
	void test_withTestClassAnnotation_andExchangeInCalledMethod_IsAPITest() {
		fail("Not yet implemented");
	}

	@Test
	void test_withTestClassAnnotation_noPerformOrExhangeInCalledMethod_IsSpringTest() {
		fail("Not yet implemented");
	}
	
	/ unit inside Spring test class
	\combinations
*/

	private void isType(TestType type) throws Exception {
		assertThat(tests.getInfoForTest(testNumber).type, is (type));
	}
	
	private TestTypeTests testNumber(int i) {
		this.testNumber = i;
		return this;
	}

}
