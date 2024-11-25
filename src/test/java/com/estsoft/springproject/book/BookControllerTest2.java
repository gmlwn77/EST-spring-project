package com.estsoft.springproject.book;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class BookControllerTest2 {
	@InjectMocks
	BookController bookController;

	@Mock
	BookService bookService;
	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	public void testFindAll() throws Exception {
		// given
		Book book1 = new Book("first", "name", "author");
		Book book2 = new Book("second", "name2", "author2");

		List<Book> bookList = List.of(book1, book2);
		Mockito.when(bookService.findAll()).thenReturn(bookList);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books"));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(model().attributeExists("bookList"))
			.andExpect(model().attribute("bookList", hasSize(2)));
	}
}