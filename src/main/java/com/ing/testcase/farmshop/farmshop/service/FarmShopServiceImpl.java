package com.ing.testcase.farmshop.farmshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ing.testcase.farmshop.farmshop.FarmshopApplication;
import com.ing.testcase.farmshop.farmshop.common.AppConfig;
import com.ing.testcase.farmshop.farmshop.common.OutOfStockException;
import com.ing.testcase.farmshop.farmshop.entities.Customer;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.entities.Stock;
import com.ing.testcase.farmshop.farmshop.entities.Stockexist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmShopServiceImpl implements FarmShopService {

    @Autowired
    AppConfig appConfig;

    public Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new XmlMapper();
        ClassLoader classLoader = new FarmshopApplication().getClass().getClassLoader();
        File fXmlFile = new File(classLoader.getResource(appConfig.getFileName()).getFile());
        // Reads from XML and converts to POJO
        Flocks flocks = objectMapper.readValue(fXmlFile,Flocks.class);
        return flocks;
    }

    public Stockexist getStock(Stock stock, Stockexist stockexist) throws IOException {
        if (!stockexist.isStockExist())
        {
            Flocks flocks = extracted();
            stockexist.setStockExist(true);
            flocks.getFlock().stream().map(flock -> {
                if (flock.getWool() != 0)
                    stock.setWools(stock.getWools()+flock.getWool());
                if (flock.getSex().trim().equalsIgnoreCase("m")) {
                    if (flock.getType().equalsIgnoreCase("sheep")) {
                        stock.setMilk(stock.getMilk() + 30);
                    }
                    if (flock.getType().equalsIgnoreCase("goat")) {
                        stock.setMilk(stock.getMilk() + 50);
                    }
                }
                return flocks;
            }).collect(Collectors.toList());
        }
        return stockexist;
    }

    public boolean stockUpdate_Customer(Customer customer, Integer milkPlaced, Integer woolPlaced, Stock stock, List<Customer>  orderlist) {
        orderlist.add(customer);
        if (milkPlaced > stock.getMilk() || woolPlaced > stock.getWools()) {
            customer.setOrder(null);
            customer.setOrderDescription("Out Of Stock");
            throw new OutOfStockException( stock.getMilk(), stock.getWools());
//            return true;
        }
        stock.setMilk(stock.getMilk() - milkPlaced);
        stock.setWools(stock.getWools() - woolPlaced);
        return false;
    }
}
