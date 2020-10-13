package testanalyzer.export;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.TestContainer;
import testanalyzer.parsing.TestClassAdapter;

public class MultiFileManager {

	TestContainer testContainer;
	private int classCounter = -1;
	private int testCounter = 0;
	private String id;
	
	
	public MultiFileManager(TestContainer testContainer, String id) {
		this.testContainer = testContainer;
		this.id = id;
	}
	
	public MultiFileManager(TestContainer testContainer) {
		this(testContainer, UUID.randomUUID().toString().replace("-", ""));
	}

	public RunFileInfo getRunInfoFile() throws Exception {
		return new RunFileInfo(id, testContainer.path, testContainer.getTestClassCount());
	}
	
	public ClassFileInfo getNextClassFile() throws Exception {
		classCounter++;
		TestClassAdapter testClass = testContainer.testClasses.get(classCounter);
		ClassFileInfo classFileInfo = new ClassFileInfo(id, classCounter, testClass);
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
