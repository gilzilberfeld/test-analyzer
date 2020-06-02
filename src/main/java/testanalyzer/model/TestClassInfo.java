package testanalyzer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestClassInfo implements Serializable {

	public static final String NoName = "";
	public List<TestInfo> testsInfo = new ArrayList<TestInfo>();
	public int numberOfValidTests;
	public String testClassName;
	
	public TestInfo create() {
		TestInfo testInfo = new TestInfo();
		testsInfo.add(testInfo);
		return testInfo;
	}
	
	public void incrementTests() {
		numberOfValidTests++;
	}

	public TestInfo get(int i) {
		return testsInfo.get(i);
	}

	@JsonIgnore
	public boolean isEmpty() {
		return testsInfo.isEmpty();
	}

}
