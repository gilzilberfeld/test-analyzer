package testanalyzer.examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ComboTest {

	@Test
	public void test1() {
		
	}
	
	public void publicMethod() {
		
	}
	
	@Disabled
	@Test
	public void ignoredTest() {
		
	}

	private void privateMethod() {
		
	}

	@Test
	public void test2() {
		
	}
	
	protected void protectedMethod() {
		
	}

	
}
