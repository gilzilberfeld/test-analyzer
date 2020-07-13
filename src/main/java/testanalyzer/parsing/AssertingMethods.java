package testanalyzer.parsing;

import java.util.HashMap;
import java.util.Map;

public class AssertingMethods {

	Map<String, Integer> methods = new HashMap<String, Integer>();

	public void add(String methodName, int numberOfAsserts) {
		methods.put(methodName, numberOfAsserts);
	}

	public int getAssertCountFor(String calledMethod) {
		return methods.getOrDefault(calledMethod, 0);
	}

}
