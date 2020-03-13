package com.rezdy.lunchapi;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class LunchApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getIndex() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("To get lunch recipes, make a GET request" +
						" to /lunch instead")));
	}

	@Test
	public void getRecipes() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/lunch").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"title\":\"Ham and Cheese Toastie\"," +
						"\"ingredients\":[\"Ham\",\"Cheese\",\"Bread\",\"Butter\"]},{\"title\":\"Hotdog\"," +
						"\"ingredients\":[\"Hotdog Bun\",\"Sausage\",\"Ketchup\",\"Mustard\"]}]")));
	}

}
