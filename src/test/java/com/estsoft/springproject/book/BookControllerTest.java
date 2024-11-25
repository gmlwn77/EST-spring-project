package com.estsoft.springproject.book;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// get /books
	@Test
	@Disabled
	void showAll() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books"));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(view().name("bookManagement"))
			.andExpect(model().attribute("bookList", hasSize(2)));
	}
}