package testanalyzer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestClassQuality implements Serializable {

	public List<TestQuality> testInfo = new ArrayList<TestQuality>();
	public int numberOfValidTests;
	public String testClassName;
	
	public TestQuality create() {
		TestQuality testData = new TestQuality();
		testInfo.add(testData);
		return testData;
	}
	
	public void incrementTests() {
		numberOfValidTests++;
	}

	public TestQuality get(int i) {
		return testInfo.get(i);
	}

	@JsonIgnore
	public boolean isEmpty() {
		return testInfo.isEmpty();
	}

}
