package testanalyzer.examples.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootTests{

	@Autowired
	private MockMvc mockMvc;
			
	@Test
	public void test1() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	
	@Test
	public void test2() throws Exception {
	}

	@Test
	public void test3() throws Exception {
		RestTemplate template = new RestTemplate();
		template.getForObject("http://localhost", String.class);
	}

	@Test
	public void test4() throws Exception {
		RestTemplate template = new RestTemplate();
		template.getForObject("http://localhost", String.class);
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

}
