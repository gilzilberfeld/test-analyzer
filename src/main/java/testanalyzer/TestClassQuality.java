package testanalyzer;

import java.util.ArrayList;
import java.util.List;

public class TestClassQuality {
	List<TestQuality> qualityData = new ArrayList<TestQuality>();
	public int numberOfValidTests;
	
	public TestQuality create() {
		TestQuality testData = new TestQuality();
		qualityData.add(testData);
		return testData;
	}
	
	public void incrementTests() {
		numberOfValidTests++;
	}

	public TestQuality get(int i) {
		return qualityData.get(i);
	}

	public boolean isEmpty() {
		return qualityData.isEmpty();
	}

	
}
