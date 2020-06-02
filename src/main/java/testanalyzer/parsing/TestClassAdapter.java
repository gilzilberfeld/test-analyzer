package testanalyzer.parsing;

import java.io.Serializable;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.exceptions.NoTestsFound;

public class TestClassAdapter {

	private TestClassParser parser;

	public TestClassAdapter(TestClassParser parser) {
		this.parser = parser;
	}

	public int getCount() throws Exception {
		return parser.getNumberOfTests();
	}

	public TestInfo getInfoForTest(int i) throws Exception {
		TestClassInfo qualityList = parser.getTestQualityData();
		if (qualityList.isEmpty())
			return new TestInfo();
		return qualityList.get(i);
	}

	public String getClassName() throws Exception {
		return parser.getTestClassName();
	}

	public TestClassInfo getAll() throws Exception {
		return parser.getTestQualityData();
	}

}
