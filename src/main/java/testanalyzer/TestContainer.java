package testanalyzer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.model.Tests;
import testanalyzer.parsing.TestClassParser;

public class TestContainer implements Serializable{

	public List<Tests> testClasses = new ArrayList<>();
	public String path;
	
	public TestContainer(String path) {
		this.path = path;
	}

	public static TestContainer LoadFrom(String path) throws Exception {
		Collection<File> files = FileUtils.listFiles(
				new File(path),
				new String[]{"java"}, 
				true);
        TestContainer container = new TestContainer(path);
		files.forEach(file->{
			TestClassParser classParser;
			try {
				classParser = new TestClassParser(file.getCanonicalPath());
				container.add(new Tests(classParser));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
		return container;
	}

	private void add(Tests tests) {
		testClasses.add(tests);
	}

	public String toJson() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);

	}

	public int size() {
		return testClasses.size();
	}

}
