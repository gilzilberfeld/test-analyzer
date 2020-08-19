package testanalyzer.examples.asserts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatusAssertTests {

	@Test
	void test_sa1() {
	}

	@Test
	void test_sa2() {
		var status = 200;
		assertEquals(status, 200);
	}

}
