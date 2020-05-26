package testanalyzer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.helpers.TestLocator;
import testanalyzer.parsing.TestClassAdapter;

class SerializationTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private static final String TEST_COUNT = ":2,";

	@Test
	void export_for_class_contains_test_info() throws Exception {
		TestClassAdapter tests = TestLocator.loadTestClass("ComboTest");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(tests.getAll());

		assertThat(json, containsString("test1"));
		assertThat(json, containsString("test2"));
		assertThat(json, containsString("ComboTest"));
		assertThat(json, containsString(TEST_COUNT));
	}

	@Test
	public void export_for_path_contains_all_test_info() throws Exception {
		int count;
		TestContainer testContainer = TestContainer.LoadFrom(TESTS_PATH);
		String json = testContainer.toJson();
		
		assertThat(json, containsString(TESTS_PATH));
		
		count = ( json.split("test1", -1).length ) - 1;	
		assertThat(count, is(4));
		count = ( json.split("test2", -1).length ) - 1;
		assertThat(count, is(2));
		
		assertThat(json, containsString("SingleTestWithAssert"));
		assertThat(json, containsString("SingleTestWithTwoAsserts"));
		assertThat(json, containsString("TwoTestWithOneAssert"));
		assertThat(json, containsString("TwoTestWithOneAssertAndMethods"));

	}

}
