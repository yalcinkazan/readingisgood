package com.project.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.readingisgood.controller.BookController;
import com.project.readingisgood.dto.request.BookCreationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {
	
	@Autowired
	private BookController bookController;

	private MockMvc mockMvc;

	private static final Long BOOK_ID = 44l;
	private static final String BOOK_NAME = "Test Book";
	private static final String AUTHOR_NAME = "Test Author";
	private static final BigDecimal PRICE = BigDecimal.ONE;
	private static final Integer STOCK_QUANTITY = 10;
	private static final Integer ZERO = 0;

	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.bookController).build();
	}
	
	@Test
	public void book_creation_test() throws Exception {

		BookCreationRequest bookCreationRequest = BookCreationRequest.builder().author(AUTHOR_NAME).name(BOOK_NAME).price(PRICE).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(bookCreationRequest);
		
		MvcResult mvcResult = this.mockMvc.perform(post("/v1/book")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void book_add_stock_book_not_found_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(put("/v1/book/" + BOOK_ID + "/stock/" + STOCK_QUANTITY)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Book Not Found", mvcResult.getResponse().getErrorMessage());

	}

	@Test
	public void book_add_stock_quantity_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(put("/v1/book/" + BOOK_ID + "/stock/" + ZERO)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
		assertEquals("Quantity have to be more than 0", mvcResult.getResponse().getErrorMessage());

	}

	@Test
	public void get_book_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/book/" + BOOK_ID)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);

	}
	

	

}
