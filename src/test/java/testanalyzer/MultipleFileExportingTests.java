package testanalyzer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testanalyzer.export.MultiFileExporter;

public class MultipleFileExportingTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private TestContainer testContainer;
	private MultiFileExporter multiFileExporter;
	
	@BeforeEach
	public void setup() throws Exception {
		testContainer = TestContainer.LoadFrom(TESTS_PATH);
		multiFileExporter = new MultiFileExporter(testContainer, "XXXX");
	}
	
	@Test
	public void hasCorrectRunFileName() {
		assertThat(multiFileExporter.getRunFileName(), is("TA_XXXX_Run.json"));
	}
	
	@Test
	public void hasCorrectClassFileCount() {
		assertThat(multiFileExporter.getClassFileCount(), is(testContainer.getTestClassCount()));
	}
	
	@Test
	public void runFileContains_path_and_classCount() throws Exception {
		String contents = multiFileExporter.getRunFileContent();
		assertThat(contents, containsString("path"));
		assertThat(contents, containsString("testClassCount"));
	}
	
	@Test
	public void hasCorrectClassFileNames() {
		assertThat(multiFileExporter.getClassFileNameFor(0), 
				is("TA_XXXX_Class_0.json"));
	}
	
	@Test
	public void hasCorrectClassFileContent() throws Exception {
		String testClassContent = multiFileExporter.getClassFileContentFor(0);
		assertThat(testClassContent,
				containsString("testClassName"));
//		assertThat(testClassContent,
//				containsString("testCount"));
		assertThat(testClassContent,
				containsString("testClassType"));
	}
	
//	@Test
//	public void classFileContains_allParameters() throws Exception {
//		String contents = multiFileExporter.getRunFileContent();
//		assertThat(contents, containsString("path"));
//		assertThat(contents, containsString("testClassCount"));
//	}
}
