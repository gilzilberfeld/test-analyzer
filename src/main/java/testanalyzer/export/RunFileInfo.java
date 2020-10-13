package testanalyzer.export;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RunFileInfo {

	public RunFileInfo(String id, String path, int testClassCount) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		this.path = path;
		this.testClassCount = testClassCount;
		this.name = "TA_" + id + "_Run.json";
		this.content = mapper.writeValueAsString(this);
	}
	
	public String path;
	public int testClassCount;
	@JsonIgnore
	public String name;
	@JsonIgnore
	public String content;
	
	
}
