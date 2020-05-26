package testanalyzer;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.TestClassParser;

public class TestContainer implements Serializable{

	public List<TestClassAdapter> testClasses = new ArrayList<>();
	public String path;
	

	public static TestContainer LoadFrom(String path) throws Exception {
		Collection<File> files = FileUtils.listFiles(
				new File(path),
				new String[]{"java"}, 
				true);
        
		TestContainer container = new TestContainer(path);
		files.forEach(file->{
			try {
				container.add(getTestsFrom(file));
			} catch (Exception e) {
				System.out.println(file.getName() + " was not parsed.");
			}
		});
        
		return container;
	}

	private static TestClassAdapter getTestsFrom(File file) throws Exception{
		TestClassParser classParser = new TestClassParser(file.getCanonicalPath());
		TestClassAdapter adapter = new TestClassAdapter(classParser);
		return adapter;
	}


	public String toJson() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}

	public TestContainer(String path) {
		this.path = path;
	}

	private void add(TestClassAdapter tests) {
		testClasses.add(tests);
	}
	public int size() {
		return testClasses.size();
	}

}
