package testanalyzer.unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.TestsLoader;

class ParsingCountTests {

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";

	@BeforeAll
	public static void setup() {
		TestsLoader.Root = ROOT_PATH;
	}
	
	@Test
	void zero_on_empty_class() throws Exception {
		var tests = TestsLoader.loadClass("EmptyClass");
		assertThat(tests.getCount(), is(0));
	}
	
	
	@Test
	void zero_on_non_test_class() throws FileNotFoundException {
		var tests = TestsLoader.loadClass("NonTestClass");
		assertThat(tests.getCount(), is(0));
	}
	
	
	@Test
	void one_on_test_class_with_one_test() throws FileNotFoundException {
		var tests = TestsLoader.loadClass("SingleTest");
		assertThat(tests.getCount(), is(1));
	}

	@Test
	void one_on_test_class_with_one_test_and_one_method() throws FileNotFoundException {
		var tests = TestsLoader.loadClass("SingleTestOneMethod");
		assertThat(tests.getCount(), is(1));
	}
	
	/*
	@Test
	void zero_on_ignored_tests() {
		assertThat(true, is(false));
	}
	
	@Test
	void two_on_test_class_with_two_tests_and_three_methods() {
		assertThat(true, is(false));
	}
	
	*/

}
