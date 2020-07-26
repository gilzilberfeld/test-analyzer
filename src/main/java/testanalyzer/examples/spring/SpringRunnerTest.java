package testanalyzer.examples.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class SpringRunnerTest{
		
	@Test
	public void test1() {
	}
	
	@Test
	public void test2() throws Exception {
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>("dummy");
		template.exchange("http://localhost", 
						  HttpMethod.POST, 
						  request, 
						  String.class);
	}

	@Test
	public void test3() throws Exception {
		RestTemplate template = new RestTemplate();
		template.getForObject("http://localhost", String.class);
	}

	@Test
	public void test4() throws Exception {
		RestTemplate template = new RestTemplate();
		template.getForEntity("http://localhost", String.class);
	}

	@Test
	public void test5() throws Exception {
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>("dummy");
		template.postForObject("http://localhost",  request,  String.class);
	}

	@Test
	public void test6() throws Exception {
		RestTemplate template = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<>("dummy");
		template.patchForObject("http://localhost",  request,  String.class);
	}

}
