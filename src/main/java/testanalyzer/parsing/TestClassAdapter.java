package testanalyzer.parsing;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;

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
			return emptyTestInfo();
		return qualityList.get(i);
	}

	private TestInfo emptyTestInfo() {
		return new TestInfo();
	}

	public String getClassName() throws Exception {
		return parser.getTestClassName();
	}

	public TestClassInfo getAll() throws Exception {
		return parser.getTestQualityData();
	}

}
