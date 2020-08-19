package testanalyzer.examples.asserts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class StatusAssertTests {

	@Test
	void test_sa1() {
	}

	@Test
	void test_sa2() {
		var status = 200;
		assertEquals(status, 200);
	}

	@Test
	void test_sa3() {
		var aStatus = 200;
		assertEquals(200, aStatus);
	}
	
	@Test
	void test_sa4() {
		HttpEntity<String> request = new HttpEntity<>("bar");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange("http://localhost", HttpMethod.POST, request ,String.class);
    	assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	void test_sa5() {
		HttpEntity<String> request = new HttpEntity<>("bar");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange("http://localhost", HttpMethod.POST, request ,String.class);
    	assertTrue(200 == result.getStatusCodeValue());
	}

	@Test
	void test_sa6() {
		var aStatus = 200;
    	assertTrue(200 ==aStatus);
	}
}
