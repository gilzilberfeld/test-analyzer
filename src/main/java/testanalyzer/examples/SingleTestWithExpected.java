package testanalyzer.examples;

import org.junit.Test;

public class SingleTestWithExpected {

	@Test(expected=NullPointerException.class)
	public void test1() {
		
	}
}
