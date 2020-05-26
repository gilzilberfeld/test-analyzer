package testanalyzer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.helpers.TestClassLocator;

class SerializationTests {

	private static final String TEST_COUNT = ":2,";

	@Test
	void export_for_class_contains_test_info() throws Exception {
		Tests tests = TestClassLocator.loadTestClass("ComboTest");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(tests.getAll());
		
		assertThat(json, containsString("test1"));
		assertThat(json, containsString("test2"));	
		assertThat(json, containsString("ComboTest"));	
		assertThat(json, containsString(TEST_COUNT));
			
	}
	

}
