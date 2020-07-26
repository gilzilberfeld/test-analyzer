package testanalyzer.examples.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class SingleTestWithRestTemplate {

	@Test
	public void test1() {
		RestTemplate template = new RestTemplate();
		template.getForObject("http://localhost", String.class);
	}
}
