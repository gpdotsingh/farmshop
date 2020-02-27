package com.ing.testcase.farmshop.farmshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.testcase.farmshop.farmshop.common.Constants;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.service.FarmShopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FarmshopApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;
	@Autowired
	FarmShopService farmShopService;
	@Autowired
	Flocks flocks;
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getFlock() throws Exception {
		MvcResult flock=mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.FLOCK_END_POINT)).andDo(print()).andExpect(status().isOk()).andReturn();
		String jsonData=flock.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		Flocks flocksByRequest = objectMapper.readValue(jsonData, Flocks.class);
		flocks.setFlock(farmShopService.extracted().getFlock());
		assertEquals(flocksByRequest,flocks);
	}

	@Test
	public void getOrder() throws Exception {
		mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.ORDER_END_POINT));
	}

	@Test
	public void postOrder() throws Exception {
		mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.FLOCK_END_POINT)).andExpect(status().isOk());
	}

	@Test
	public void outOfStock() throws Exception {
		mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.FLOCK_END_POINT)).andExpect(status().isOk());
	}

	@Test
	public void getStock() throws Exception {
		mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.FLOCK_END_POINT)).andExpect(status().isOk());
	}
}
