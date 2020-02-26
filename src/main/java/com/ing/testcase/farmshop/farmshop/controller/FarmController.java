package com.ing.testcase.farmshop.farmshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.testcase.farmshop.farmshop.entities.Customer;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.entities.Stock;
import com.ing.testcase.farmshop.farmshop.service.FarmshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/farmshop",produces = MediaType.APPLICATION_JSON_VALUE)

public class FarmController {

    @Autowired
    FarmshopService farmshopService;
    Stock stock = new Stock() ;
    int counter = 0;
    int milk = 0, wool = 0;
    Customer customer = new Customer();
    List<Customer> orderlist = new ArrayList<Customer>();

    @GetMapping( value = "/flocks")
    public ResponseEntity<Flocks> flock() throws JsonMappingException, JsonProcessingException, IOException {
        Flocks flocks = farmshopService.extracted();
        return new ResponseEntity<Flocks>(flocks, HttpStatus.OK);
    }


    @GetMapping( value = "/stock")
    public ResponseEntity<Stock> stock() throws JsonMappingException, JsonProcessingException, IOException {

        if (counter == 0) {
            Flocks flocks = farmshopService.extracted();
            flocks.getFlock().stream().map(flock -> {
                if (flock.getWool() != 0)
                    wool += flock.getWool();
                if (flock.getSex().trim().equalsIgnoreCase("m")) {
                    if (flock.getType().equalsIgnoreCase("sheep")) {
                        milk += 30;
                    }
                    if (flock.getType().equalsIgnoreCase("goat")) {
                        milk += 50;
                    }
                }
                return flock;}).collect(Collectors.toList());
            counter++;
        }
        stock.setMilk(milk);
        stock.setWools(wool);
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Customer> order(@RequestBody Customer customer) throws JsonMappingException, JsonProcessingException, IOException {

        Integer milkPlaced = 0, woolPlaced = 0;
        milkPlaced += customer.getOrder().getMilk();
        woolPlaced += customer.getOrder().getWool();

        if (milkPlaced > milk || woolPlaced > wool) {
            customer.setOrder(null);
            customer.setOrderDescription("Out Of Stock");
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }

        milk = milk - milkPlaced;
        wool = wool - woolPlaced;
        stock.setMilk(milk);
        stock.setWools(wool);
        customer.setOrderDescription("Order Place For Total Milk is  " + milkPlaced + " and for  total wool is  " + woolPlaced);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Customer>> fetchOrder() throws JsonMappingException, JsonProcessingException, IOException {
        return new ResponseEntity<List<Customer>>(orderlist, HttpStatus.OK);
    }

}
