package com.project.OOP;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
	
	 @Autowired
	 private MockMvc mockMvc;

	@Test
	public void test1() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/metadata").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.type").exists());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/metadata").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.type").isNotEmpty());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/metadata").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.type").isString());
	}
	@Test
	void test2() throws Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders.get("/ranking").param("field", "Top10").accept(MediaType.APPLICATION_JSON_VALUE))
         .andDo(print()).andExpect(status().isOk())
         .andExpect(jsonPath("$.Top10").isNotEmpty());
	}
	@Test
	void test3() throws Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders.get("/stats").accept(MediaType.APPLICATION_JSON_VALUE))
         .andDo(print()).andExpect(status().isOk())
         .andExpect(jsonPath("$.Media_follower").value(4264.25));
	}

}
