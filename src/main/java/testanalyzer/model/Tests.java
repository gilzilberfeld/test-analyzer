package testanalyzer.model;

import java.io.Serializable;

import testanalyzer.parsing.TestClassParser;
import testanalyzer.parsing.exceptions.NoTestsFound;

public class Tests implements Serializable{

	private TestClassParser parser;

	public Tests(TestClassParser parser) {
		this.parser = parser;
	}

	public int getCount() throws Exception {
		return parser.getNumberOfTests();
	}

	public TestQuality qualityDataFor(int i) throws Exception {
		TestClassQuality qualityList = parser.getTestQualityData();
		if (qualityList.isEmpty())
			throw new NoTestsFound();
		return qualityList.get(i);
	}

	public String getClassName() throws Exception {
		return parser.getTestClassName();
	}

	public TestClassQuality getAll() throws Exception {
		return parser.getTestQualityData();
	}

}
