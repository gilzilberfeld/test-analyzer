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

	@JsonIgnore
	public String getClassName() throws Exception {
		return parser.getTestClassName();
	}

	public TestClassInfo getAll() throws Exception {
		return parser.getTestClassInfo();
	}

	public TestClassType getTestClassType() throws Exception {
		return parser.getTestClassInfo().type;
	}

}
