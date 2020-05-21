package testanalyzer.examples;

import org.junit.Test;

public class SingleTestWithExpectedJUnit4 {

	@Test(expected=NullPointerException.class)
	public void test1() {
		
	}
}
