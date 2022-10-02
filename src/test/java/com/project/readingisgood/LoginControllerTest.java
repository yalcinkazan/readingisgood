package com.project.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.readingisgood.controller.CustomerController;
import com.project.readingisgood.controller.LoginController;
import com.project.readingisgood.dto.request.CustomerRegisterRequest;
import com.project.readingisgood.dto.request.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
	
	@Autowired
	private LoginController loginController;

	private MockMvc mockMvc;

	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	private static final String WRONG_USERNAME = "user_w";
	private static final String WRONG_PASSWORD = "password_w";

	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.loginController).build();
	}
	
	@Test
	public void login_success_test() throws Exception {

		LoginRequest loginController = LoginRequest.builder().username(USERNAME).password(PASSWORD).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(loginController);
		
		MvcResult mvcResult = this.mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void login_user_not_found_test() throws Exception {

		LoginRequest loginController = LoginRequest.builder().username(WRONG_USERNAME).password(PASSWORD).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(loginController);

		MvcResult mvcResult = this.mockMvc.perform(post("/login")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals("User Not Found", mvcResult.getResponse().getErrorMessage());
	}

	@Test
	public void login_wrong_password_test() throws Exception {

		LoginRequest loginController = LoginRequest.builder().username(USERNAME).password(WRONG_PASSWORD).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(loginController);

		MvcResult mvcResult = this.mockMvc.perform(post("/login")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus());
		assertEquals("Wrong password", mvcResult.getResponse().getErrorMessage());
	}


}
