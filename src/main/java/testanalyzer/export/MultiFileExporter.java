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
		ClassFileInfo cfi = new ClassFileInfo();
		cfi.name ="TA_" + id + "_Class_" + Integer.toString(i) + ".json";
		return cfi.name;
	}

	public String getClassFileContentFor(int i) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(testContainer.testClasses.get(i));
	}

}
