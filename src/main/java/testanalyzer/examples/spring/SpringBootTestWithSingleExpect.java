package testanalyzer.examples.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootTestWithSingleExpect {

	@Autowired
	private MockMvc mockMvc;
			
	@Test
	public void test1() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	
	@Test
	public void test2() throws Exception {
	}

}
