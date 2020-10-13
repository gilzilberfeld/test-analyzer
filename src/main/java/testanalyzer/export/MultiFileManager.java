package testanalyzer.export;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.TestContainer;
import testanalyzer.parsing.TestClassAdapter;

public class MultiFileManager {

	TestContainer testContainer;
	private int classCounter = 0;
	private int testCounter = 0;
	private String id;
	private ObjectMapper mapper;
	
	
	public MultiFileManager(TestContainer testContainer, String id) {
		this.testContainer = testContainer;
		this.id = id;
		mapper = new ObjectMapper();
	}
	
	public MultiFileManager(TestContainer testContainer) {
		this(testContainer, UUID.randomUUID().toString().replace("-", ""));
	}

	public RunFileInfo getRunInfoFile() throws Exception {
		return new RunFileInfo(id, testContainer.path, testContainer.getTestClassCount());
	}
	public String getRunFileName() {
		return "TA_" + id + "_Run.json";
	}

//	public String getRunFileContent() throws Exception {
//		RunFileInfo rfc = 
//		return rfc.toJson();
//	}


	public int getClassFileCount() {
		return this.testContainer.getTestClassCount();
	}

	public String getNextClassFileName() {
		classCounter++;
		String result = "TA_" + id + "_Class_" + Integer.toString(classCounter) + ".json";
		return result;
	}

	public String getNextClassFileContent() throws Exception {
		return mapper.writeValueAsString(testContainer.testClasses.get(classCounter));
	}

	public String getNextTestFileName() {
		testCounter++;
		String result = "TA_" + id + "_Test_" + Integer.toString(testCounter) + ".json";
		return result;
	}

	public String getNextTestFileContent() throws Exception, Exception {
		return mapper.writeValueAsString(testContainer.testClasses.get(0).getInfoForTest(0));
	}

	public ClassFileInfo getNextClassFile() throws Exception {
		TestClassAdapter testClass = testContainer.testClasses.get(classCounter);
		ClassFileInfo classFileInfo = new ClassFileInfo(id, classCounter, testClass);
		classCounter++;
		return classFileInfo;
	}

	public List<TestFileInfo> getNextTestFiles() throws Exception {
		List<TestFileInfo> testFiles = new ArrayList<TestFileInfo>();
		TestClassAdapter testClass = testContainer.testClasses.get(classCounter);
		testClass.getAll().testsInfo.forEach((test) -> {
			TestFileInfo testFileInfo;
			try {
				testFileInfo = new TestFileInfo(id, testCounter, test );
				testFiles.add(testFileInfo);
				testCounter++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return testFiles;
	}
	
}
