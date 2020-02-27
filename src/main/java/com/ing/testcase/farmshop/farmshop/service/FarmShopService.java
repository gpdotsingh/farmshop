package com.ing.testcase.farmshop.farmshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.farmshop.entities.Customer;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.entities.Stock;
import com.ing.testcase.farmshop.farmshop.entities.Stockexist;

import java.io.IOException;
import java.util.List;

public interface FarmShopService {
    public Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException;
    public Stockexist getStock(Stock stock, Stockexist stockexist) throws IOException ;
    public boolean stockUpdate_Customer(Customer customer, Integer milkPlaced, Integer woolPlaced, Stock stock, List<Customer> orderList);

    }
