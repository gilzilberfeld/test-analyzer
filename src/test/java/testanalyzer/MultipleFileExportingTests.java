package testanalyzer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import testanalyzer.export.MultiFileExporter;

public class MultipleFileExportingTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private TestContainer testContainer;
	private MultiFileExporter multiFileExporter;
	private String content;
	
	@BeforeEach
	public void setup() throws Exception {
		testContainer = TestContainer.LoadFrom(TESTS_PATH);
		multiFileExporter = new MultiFileExporter(testContainer, "XXXX");
	}
	
	@Test
	public void hasCorrectRunFileName() {
		assertThat(multiFileExporter.getRunFileName(), 
				is("TA_XXXX_Run.json"));
	}
	
	@Test
	public void runFileContains_path_and_classCount() throws Exception {
		runFileContentHas("path");
		runFileContentHas("testClassCount");
	}

	
	@Test
	public void hasCorrectClassFileName() {
		assertThat(multiFileExporter.getClassFileNameFor(0), 
				is("TA_XXXX_Class_0.json"));
	}
	
	@Test
	public void hasCorrectClassFileContent() throws Exception {
		classFileContent().contains("testClassName");
		classFileContent().contains("testClassType");
	}


	@Test
	public void hasCorrectTestFileName() {
		assertThat(multiFileExporter.getTestFileNameFor(0), 
				is("TA_XXXX_Test_0.json"));
	}

	@Test 
	void hasCorrectTestContent() throws Exception {
		testFileContent().contains("testName");
		Assertions.assertFalse (content.contains(":null"));
		testFileContent().contains("testClassPath");
	}
	
	private MultipleFileExportingTests testFileContent() throws Exception {
		content = multiFileExporter.getNextTestFileContent();
		return this;
	}

	private MultipleFileExportingTests classFileContent() throws Exception {
		content =multiFileExporter.getNextClassFileContent(); 
		return this;
	}

	private void runFileContentHas(String param) throws Exception {
		assertThat(multiFileExporter.getRunFileContent(), containsString(param));
	}
	private void contains(String param) {
		assertThat(content, containsString(param));
	}
}
