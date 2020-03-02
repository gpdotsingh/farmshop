package com.ing.testcase.farmshop.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Customer {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customer;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Order order;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orderDescription;

}
