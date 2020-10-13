package testanalyzer.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.parsing.TestClassAdapter;

public class ClassFileInfo {

	public ClassFileInfo(String id, int classCounter, TestClassAdapter testClass) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		this.content = mapper.writeValueAsString(testClass);
		this.name = "TA_" + id + "_Class_" + Integer.toString(classCounter) + ".json";

	}
	public String name;
	public String content;
}
