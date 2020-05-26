package testanalyzer.unittests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import testanalyzer.TestClassLocator;
import testanalyzer.Tests;
import testanalyzer.parsing.exceptions.NoTestsFound;

class NameTests {
	private Tests tests;

	@Test
	void not_test_class_when_empty_class() throws Exception {
		tests = TestClassLocator.loadTestClass("EmptyClass");
		assertThrows(NoTestsFound.class, () -> tests.getClassName());

	}

	@Test
	void not_test_class_when_not_a_test_class() throws Exception {
		tests = TestClassLocator.loadTestClass("NonTestClass");
		assertThrows(NoTestsFound.class, () -> tests.getClassName());
	}

	@Test
	void not_test_class_when_interface() throws Exception {
		tests = TestClassLocator.loadTestClass("SomeInterface");
		assertThrows(NoTestsFound.class, () -> tests.getClassName());
	}

	@Test
	void class_name_when_test_class() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTest");
		assertThat(tests.getClassName(), is("SingleTest"));
	}

	@Test
	void single_test_name_identified() throws Exception {
		tests = TestClassLocator.loadTestClass("SingleTest");
		assertThat(tests.qualityDataFor(0).testName, is("test1"));
	}

	@Test
	void multiple_test_names_identified() throws Exception {
		tests = TestClassLocator.loadTestClass("ComboTest");
		assertThat(tests.qualityDataFor(0).testName, is("test1"));
		assertThat(tests.qualityDataFor(1).testName, is("test2"));
	}

}
