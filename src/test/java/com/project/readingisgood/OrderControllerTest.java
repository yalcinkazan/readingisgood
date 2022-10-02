package com.project.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.readingisgood.controller.BookController;
import com.project.readingisgood.controller.OrderController;
import com.project.readingisgood.dto.request.BookCreationRequest;
import com.project.readingisgood.dto.request.OrderCreationRequest;
import com.project.readingisgood.dto.request.OrderDateRangeRequest;
import com.project.readingisgood.dto.request.OrderDetailRequest;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {
	
	@Autowired
	private OrderController orderController;

	private MockMvc mockMvc;

	private static final Long ORDER_ID = 1l;
	private static final Integer PAGE_NO = 0;
	private static final Integer PAGE_SIZE = 5;

	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.orderController).build();
	}
	
	@Test
	public void create_order_test() throws Exception {

		ArrayList<OrderDetailRequest> orderDetailRequestList = new ArrayList<>();
		orderDetailRequestList.add(OrderDetailRequest.builder().book(10l).quantity(1).build());

		OrderCreationRequest orderCreationRequest = OrderCreationRequest.builder().customer(10l).orderContent(orderDetailRequestList).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(orderCreationRequest);
		
		MvcResult mvcResult = this.mockMvc.perform(post("/v1/order")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Book Not Found", mvcResult.getResponse().getErrorMessage());
	}

	@Test
	public void create_order_quantity_error_test() throws Exception {

		ArrayList<OrderDetailRequest> orderDetailRequestList = new ArrayList<>();
		orderDetailRequestList.add(OrderDetailRequest.builder().book(1l).quantity(-1).build());

		OrderCreationRequest orderCreationRequest = OrderCreationRequest.builder().customer(1l).orderContent(orderDetailRequestList).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(orderCreationRequest);

		MvcResult mvcResult = this.mockMvc.perform(post("/v1/order")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
		assertEquals("Quantity have to be more than 0", mvcResult.getResponse().getErrorMessage());
	}

	@Test
	public void get_order_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/order/" + ORDER_ID)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("Order Not Found", mvcResult.getResponse().getErrorMessage());

	}

	@Test
	public void get_order_range_test() throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2022-09-01");
		Date endDate = formatter.parse("2022-10-01");

		OrderDateRangeRequest orderDateRangeRequest = OrderDateRangeRequest.builder().startDate(startDate).endDate(endDate).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(orderDateRangeRequest);

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/order/" + PAGE_NO + "/" + PAGE_SIZE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}


}
