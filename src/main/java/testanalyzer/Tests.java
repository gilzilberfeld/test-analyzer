package testanalyzer;

import testanalyzer.parsing.exceptions.NoTestsFound;
import testanalyzer.parsing.TestResolver;

public class Tests {

	private TestResolver resolver;

	public Tests(TestResolver resolver) {
		this.resolver = resolver;
	}

	public int getCount() {
		return resolver.getNumberOfTests();
	}

	public TestQuality qualityDataFor(int i) throws NoTestsFound {
		TestClassQuality qualityList = resolver.getTestQualityData();
		if (qualityList.isEmpty())
			throw new NoTestsFound();
		return qualityList.get(i);
	}

	public String getClassName() throws Exception {
		return resolver.getTestClassName();
	}

}
