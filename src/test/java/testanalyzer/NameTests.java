package testanalyzer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import testanalyzer.helpers.TestLocator;
import testanalyzer.model.TestClassInfo;
import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.exceptions.NoTestsFound;

class NameTests {
	private TestClassAdapter tests;

	@Test
	void not_test_class_when_empty_class() throws Exception {
		tests = TestLocator.loadTestClass("EmptyClass");
		assertThat(tests.getClassName(), is(TestClassInfo.NoName));

	}

	@Test
	void not_test_class_when_not_a_test_class() throws Exception {
		tests = TestLocator.loadTestClass("NonTestClass");
		assertThat(tests.getClassName(), is(TestClassInfo.NoName));
	}

	@Test
	void no_test_class_when_interface() throws Exception {
		tests = TestLocator.loadTestClass("SomeInterface");
		assertThat(tests.getClassName(), is(TestClassInfo.NoName));
	}

	@Test
	void class_name_when_test_class() throws Exception {
		tests = TestLocator.loadTestClass("SingleTest");
		assertThat(tests.getClassName(), is("SingleTest"));
	}

	@Test
	void single_test_name_identified() throws Exception {
		tests = TestLocator.loadTestClass("SingleTest");
		assertThat(tests.getInfoForTest(0).testName, is("test1"));
	}

	@Test
	void multiple_test_names_identified() throws Exception {
		tests = TestLocator.loadTestClass("ComboTest");
		assertThat(tests.getInfoForTest(0).testName, is("test1"));
		assertThat(tests.getInfoForTest(1).testName, is("test2"));
	}

}
