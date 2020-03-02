package com.ing.testcase.farmshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.common.CheckInputtypeException;
import com.ing.testcase.farmshop.common.OutOfStockException;
import com.ing.testcase.farmshop.entities.Customer;
import com.ing.testcase.farmshop.entities.Flocks;

import java.io.IOException;
import java.util.List;

public interface FarmShopService {
    public Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException;
    public void getStock()  ;
    public boolean stockUpdate_Customer(Customer customer, List<Customer> orderList) throws OutOfStockException, CheckInputtypeException;

    }
