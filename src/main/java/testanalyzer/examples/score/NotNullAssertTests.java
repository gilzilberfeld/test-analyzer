package testanalyzer.examples.score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
class NotNullAssertTests {
	
	Object obj1;
	Object obj2;
	
	@Test
	void test1() {
		Assertions.assertEquals(1,1);
	}

	@Test
	void test2() {
		Assertions.assertEquals(1,1);
		Assertions.assertNotNull(obj1);
	}

	@Test
	void test3() {
		Assertions.assertEquals(1,1);
		Assertions.assertNotNull(obj1);
		Assertions.assertNotNull(obj2);
	}
}
