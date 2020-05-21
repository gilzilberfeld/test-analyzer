package testanalyzer.examples.exceptions;

import org.junit.Ignore;
import org.junit.Test;

public class SingleIgnoredTestWithExpected {

	@Test(expected=NullPointerException.class)
	@Ignore
	public void test1() {
		
	}
}
