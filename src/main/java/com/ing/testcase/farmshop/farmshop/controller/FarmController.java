package com.ing.testcase.farmshop.farmshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.farmshop.common.Constants;
import com.ing.testcase.farmshop.farmshop.common.OutOfStockException;
import com.ing.testcase.farmshop.farmshop.entities.*;
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
@RequestMapping(value = Constants.CONTROLLER_END_POINT,produces = MediaType.APPLICATION_JSON_VALUE)

public class FarmController {


    @Autowired
    FarmShopService farmshopService;
    @Autowired
    Stock stock;
    @Autowired
    Flocks flocks;

    List<Customer> orderlist = new ArrayList<Customer>();

    /***
     *
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws IOException
     */
    @GetMapping(Constants.FLOCK_END_POINT)
    public ResponseEntity<Flocks> flock() {
        return new ResponseEntity<Flocks>(flocks, HttpStatus.OK);
    }

    /****
     *
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws IOException
     */
    @PostMapping(  Constants.STOCK_END_POINT)
    public ResponseEntity<Stock> updatedeStock()  {
        farmshopService.getStock();
        return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
    }

    /***
     *
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws IOException
     */
    @GetMapping( Constants.STOCK_END_POINT)
    public ResponseEntity<Stock> stock() {
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    /***
     *
     * @param customer
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws IOException
     */
    @PostMapping(Constants.ORDER_END_POINT)
    public ResponseEntity<Customer> order(@RequestBody Customer customer) throws NullPointerException, Exception {

        farmshopService.stockUpdate_Customer(customer,orderlist);
        customer.setOrderDescription("Your order placed successfully for  Milk :: " + customer.getOrder().getMilk() + " & for Wool ::  " + customer.getOrder().getWool());
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    /****
     *
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     * @throws IOException
     */
    @GetMapping( Constants.ORDER_END_POINT)
    public ResponseEntity<List<Customer>> fetchOrder()  {
        return new ResponseEntity<List<Customer>>(orderlist, HttpStatus.OK);
    }

}
