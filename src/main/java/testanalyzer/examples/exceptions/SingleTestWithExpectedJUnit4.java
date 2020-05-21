package testanalyzer.examples.exceptions;

import org.junit.Test;

public class SingleTestWithExpectedJUnit4 {

	@Test(expected=NullPointerException.class)
	public void test1() {
		
	}
}
