package com.project.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.readingisgood.controller.BookController;
import com.project.readingisgood.controller.CustomerController;
import com.project.readingisgood.dto.request.BookCreationRequest;
import com.project.readingisgood.dto.request.CustomerRegisterRequest;
import com.project.readingisgood.repository.BookRepository;
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
public class CustomerControllerTest {
	
	@Autowired
	private CustomerController customerController;

	private MockMvc mockMvc;

	private static final Long CUSTOMER_ID = 1l;
	private static final String CUSTOMER_EMAIL = "test@test.com";
	private static final String CUSTOMER_NAME = "Test Name";
	private static final String CUSTOMER_SURNAME = "Test Surname";
	private static final Integer PAGE_NO = 0;
	private static final Integer PAGE_SIZE = 5;

	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.customerController).build();
	}
	
	@Test
	public void customer_register_test() throws Exception {

		CustomerRegisterRequest customerRegisterRequest = CustomerRegisterRequest.builder().email(CUSTOMER_EMAIL).name(CUSTOMER_NAME).surname(CUSTOMER_SURNAME).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(customerRegisterRequest);
		
		MvcResult mvcResult = this.mockMvc.perform(post("/v1/customer")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void get_all_orders_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/customer/" + CUSTOMER_ID + "/orders/" + PAGE_NO + "/" + PAGE_SIZE)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Customer Not Found", mvcResult.getResponse().getErrorMessage());

	}



	

}
