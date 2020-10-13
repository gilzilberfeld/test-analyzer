package testanalyzer.export;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.TestContainer;

public class MultiFileExporter {

	TestContainer testContainer;
	private String id;
	
	public MultiFileExporter(TestContainer testContainer, String id) {
		this.testContainer = testContainer;
		this.id = id;
	}
	
	public MultiFileExporter(TestContainer testContainer) {
		this(testContainer, UUID.randomUUID().toString());
	}

	public String getRunFileName() {
		return "TA_" + id + "_Run.json";
	}

	public String getRunFileContent() throws Exception {
		RunFileContent rfc = new RunFileContent();
		rfc.path = testContainer.path;
		rfc.testClassCount = testContainer.getTestClassCount();
		return rfc.toJson();
	}


	public int getClassFileCount() {
		return this.testContainer.getTestClassCount();
	}

	public String getClassFileNameFor(int i) {
		return "TA_" + id + "_Class_" + Integer.toString(i) + ".json";
	}

	public String getNextClassFileContent() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(testContainer.testClasses.get(0));
	}

	public String getTestFileNameFor(int i) {
		return "TA_" + id + "_Test_" + Integer.toString(i) + ".json";
	}

	public String getNextTestFileContent() throws Exception, Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(testContainer.testClasses.get(0).getInfoForTest(0));
	}

}
