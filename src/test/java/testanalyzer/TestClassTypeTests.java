package testanalyzer;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.model.TestClassType;
import testanalyzer.parsing.TestClassAdapter;

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
	
	@Test
	void testClass_identifyFullPath() throws Exception {
		tests = TestLocator.loadTestClass("SpringBootTests");
		testClass().containsPath("src\\main\\java");
		testClass().containsPath("test-analyzer");
	}



	private void containsPath(String path) throws Exception {
		assertThat(tests.getPath(), containsString(path));
	}

	private TestClassTypeTests testClass() {
		return this;
	}

	private void isType(TestClassType type) throws Exception {
		assertThat(tests.getTestClassType(), is(type));
	}

}
