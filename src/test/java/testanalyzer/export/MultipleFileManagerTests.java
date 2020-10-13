package testanalyzer.export;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import testanalyzer.TestContainer;
import testanalyzer.export.MultiFileManager;

public class MultipleFileManagerTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private TestContainer testContainer;
	private MultiFileManager multiFileManager;
	private String content;
	
	@BeforeEach
	public void setup() throws Exception {
		testContainer = TestContainer.LoadFrom(TESTS_PATH);
		multiFileManager = new MultiFileManager(testContainer, "XXXX");
	}
	
	@Test
	public void hasCorrectRunFileName() throws Exception {
		assertThat(multiFileManager.getRunInfoFile().name, 
				is("TA_XXXX_Run.json"));
	}
	
	@Test
	public void runFileContains_path_and_classCount() throws Exception {
		runFileContentHas("path");
		runFileContentHas("testClassCount");
	}

	
	@Test
	public void hasCorrectClassFileName() throws Exception {
		assertThat(multiFileManager.getNextClassFile().name, 
				is("TA_XXXX_Class_0.json"));
		assertThat(multiFileManager.getNextClassFile().name, 
				is("TA_XXXX_Class_1.json"));
	}
	
	@Test
	public void hasCorrectClassFileContent() throws Exception {
		classFileContent().contains("testClassName");
		classFileContent().contains("testClassType");
	//	classFileContent().doesNotContain("all");
	}



	@Test
	public void hasCorrectTestFileName() throws Exception {
		multiFileManager.getNextClassFile();
		assertThat(multiFileManager.getNextTestFiles().get(0).name, 
				is("TA_XXXX_Test_0.json"));
		assertThat(multiFileManager.getNextTestFiles().get(0).name, 
				is("TA_XXXX_Test_1.json"));
	}

	@Test 
	void hasCorrectTestContent() throws Exception {
		multiFileManager.getNextClassFile();
		testFileContent().contains("testName");
		testFileContent().contains("testClassPath");
	}
	
	private MultipleFileManagerTests testFileContent() throws Exception {
		content = multiFileManager.getNextTestFiles().get(0).content;
		return this;
	}

	private MultipleFileManagerTests classFileContent() throws Exception {
		content =multiFileManager.getNextClassFile().content; 
		return this;
	}

	private void runFileContentHas(String param) throws Exception {
		assertThat(multiFileManager.getRunInfoFile().content, containsString(param));
	}
	private void contains(String param) {
		assertThat(content, containsString(param));
	}
	
	private void doesNotContain(String param) {
		assertThat(content, not(containsString(param)));
		
	}

}
