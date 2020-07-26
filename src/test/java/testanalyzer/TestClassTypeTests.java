package testanalyzer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.TestClassType;

class TestClassTypeTests {

	private TestClassAdapter tests;

	@Test
	void testClass_withoutTestClassAnnotation_isUnitTestClass() throws Exception {
		tests = TestLocator.loadTestClass("SingleTestWithAssert");
		testClass().isType(TestClassType.Unit);
	}

	@Test
	void testClass_withRunWithSpringAnnotation_isSpringTestClass() throws Exception {
		tests = TestLocator.loadTestClass("SpringRunnerTest");
		testClass().isType(TestClassType.Spring);
	}
	
	@Test
	void testClass_withRunWithSpringJUnit4Annotation_isSpringTestClass() throws Exception {
		tests = TestLocator.loadTestClass("SpringRunnerJUnit4Test");
		testClass().isType(TestClassType.Spring);
	}

	@Test
	void testClass_withSpringBootTestClassAnnotation_IsSpringBootTest() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testClass().isType(TestClassType.SpringBoot);
	}


	private TestClassTypeTests testClass() {
		return this;
	}

	private void isType(TestClassType type) throws Exception {
		assertThat(tests.getTestClassType(), is(type));
	}

}
