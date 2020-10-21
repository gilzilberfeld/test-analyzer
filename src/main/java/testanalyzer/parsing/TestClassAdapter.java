package testanalyzer.parsing;

import com.fasterxml.jackson.annotation.JsonIgnore;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestClassType;
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
		TestClassInfo qualityList = parser.getTestClassInfo();
		if (qualityList.isEmpty())
			return emptyTestInfo();
		return qualityList.get(i);
	}

	private TestInfo emptyTestInfo() {
		return new TestInfo();
	}

	public String getTestClassName() throws Exception {
		return parser.getTestClassName();
	}

	public TestClassInfo getAll() throws Exception {
		return parser.getTestClassInfo();
	}

	public TestClassType getTestClassType() throws Exception {
		return parser.getTestClassInfo().type;
	}

	public String getPath() throws Exception {
		return parser.getTestClassInfo().classPath;
	}

}
