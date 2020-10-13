package testanalyzer.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RunFileContent {

	public String path;
	public int testClassCount;
	public String toJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

}
