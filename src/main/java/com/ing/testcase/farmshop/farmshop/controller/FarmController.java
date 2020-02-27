package com.ing.testcase.farmshop.farmshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.farmshop.entities.Customer;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.entities.Stock;
import com.ing.testcase.farmshop.farmshop.entities.Stockexist;
import com.ing.testcase.farmshop.farmshop.service.FarmShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/farmshop",produces = MediaType.APPLICATION_JSON_VALUE)

public class FarmController {

    @Autowired
    Stockexist stockexist;
    @Autowired
    FarmShopService farmshopService;
    Stock stock = new Stock() ;

    List<Customer> orderlist = new ArrayList<Customer>();

    @GetMapping( value = "/flocks")
    public ResponseEntity<Flocks> flock() throws JsonMappingException, JsonProcessingException, IOException {
        Flocks flocks = farmshopService.extracted();
        return new ResponseEntity<Flocks>(flocks, HttpStatus.OK);
    }


    @GetMapping( value = "/stock")
    public ResponseEntity<Stock> stock() throws JsonMappingException, JsonProcessingException, IOException {
        stockexist = farmshopService.getStock(stock,stockexist);
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Customer> order(@RequestBody Customer customer) throws JsonMappingException, JsonProcessingException, IOException {

        if (farmshopService.stockUpdate_Customer(customer, customer.getOrder().getMilk(), customer.getOrder().getWool(),stock,orderlist))
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);

        customer.setOrderDescription("Order Place For Total Milk is  " + customer.getOrder().getMilk() + " and for  total wool is  " + customer.getOrder().getWool());

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Customer>> fetchOrder() throws JsonMappingException, JsonProcessingException, IOException {
        return new ResponseEntity<List<Customer>>(orderlist, HttpStatus.OK);
    }

}
