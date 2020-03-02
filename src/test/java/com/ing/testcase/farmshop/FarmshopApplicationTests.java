package com.ing.testcase.farmshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.testcase.farmshop.entities.*;
import com.ing.testcase.farmshop.common.Constants;
import com.ing.testcase.farmshop.common.OutOfStockException;
import com.ing.testcase.farmshop.service.FarmShopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
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
	@Autowired
    Stock stock;
	@Autowired
    Stockexist stockexist;

	Order order;
	Customer customer;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		order = new Order();customer = new Customer();
		order.setMilk(20);
		order.setWool(10);
		customer.setCustomer("Gp Ltd");
		customer.setOrder(order);
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
	public void postOrder() throws Exception {

		String url = Constants.CONTROLLER_END_POINT+Constants.ORDER_END_POINT;
		farmShopService.extracted();
		MvcResult custResult=mockMvc.perform( MockMvcRequestBuilders
				.post(url)
				.content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		String jsonData=custResult.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		Customer custByReq = objectMapper.readValue(jsonData, Customer.class);
		assertEquals(custByReq.getOrder(),customer.getOrder());
		assertEquals(custByReq.getCustomer(),customer.getCustomer());
	}


	@Test
	public void outOfStock() throws Exception {
		String url = Constants.CONTROLLER_END_POINT+Constants.ORDER_END_POINT;
		order.setWool(200);
		order.setMilk(200);
		MvcResult custResult=mockMvc.perform( MockMvcRequestBuilders
				.post(url)
				.content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String jsonData=custResult.getResponse().getContentAsString();
		assertEquals(OutOfStockException.class,custResult.getResolvedException().getClass());
	}

	@Test
	public void getStock() throws Exception {
		MvcResult stck=mockMvc.perform(get(Constants.CONTROLLER_END_POINT+Constants.STOCK_END_POINT)).andExpect(status().isOk()).andReturn();
		String jsonData=stck.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		Stock stckByReq = objectMapper.readValue(jsonData, Stock.class);
		assertEquals(stckByReq,stock);
	}

	@Test
	public void postStock() throws Exception {
		stockexist.setStockExist(false);
		stock.setMilk(0);
		stock.setWools(0);
		String url = Constants.CONTROLLER_END_POINT+Constants.STOCK_END_POINT;
		MvcResult stck=mockMvc.perform( MockMvcRequestBuilders
				.post(url)
				.content(asJsonString(customer))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String jsonData=stck.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		Stock stckByReq = objectMapper.readValue(jsonData, Stock.class);
		assertEquals(stckByReq,stock);
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
