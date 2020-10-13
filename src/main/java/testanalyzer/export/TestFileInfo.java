package testanalyzer.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.model.TestInfo;

public class TestFileInfo {

	public TestFileInfo(String id, int testCounter, TestInfo testInfo) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		this.name =	"TA_" + id + "_Test_" + Integer.toString(testCounter) + ".json";
		this.content = mapper.writeValueAsString(testInfo); 
	}
	
	public String name;
	public String content;

}
