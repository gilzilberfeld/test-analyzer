package testanalyzer;

import java.util.List;

import testanalyzer.parsing.TestResolver;

public class Tests {

	private TestResolver resolver;
	
	
	public Tests(TestResolver resolver) {
		this.resolver = resolver;
	}

	public int getCount() {
		if (resolver.isNotTestClass())
			return 0;
		return resolver.getNumberOfTests();
	}

	public Quality qualityDataFor(int i) {
		List<Quality> qualityList = resolver.getTestQualityData();
		return qualityList.get(i);
	}

	
}
