package com.project.readingisgood;

import com.project.readingisgood.controller.StatisticsController;
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
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsControllerTest {
	
	@Autowired
	private StatisticsController statisticsController;

	private MockMvc mockMvc;

	@Before
	public void init() {
		this.mockMvc = standaloneSetup(this.statisticsController).build();
	}
	
	@Test
	public void get_monthly_statistics_test() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/statistics/monthly")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

}
