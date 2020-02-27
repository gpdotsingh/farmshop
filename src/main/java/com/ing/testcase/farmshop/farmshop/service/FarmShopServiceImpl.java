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
    @Autowired
    Flocks flocks;
    @Autowired
    Stock stock;
    @Autowired
    Stockexist stockexist;

    /****
     *
     * @return
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new XmlMapper();
        ClassLoader classLoader = new FarmshopApplication().getClass().getClassLoader();
        File fXmlFile = new File(classLoader.getResource(appConfig.getFileName()).getFile());
        // Reads from XML and converts to POJO
        return objectMapper.readValue(fXmlFile,Flocks.class);
    }

    /***
     *
     */
    public void getStock()  {
        if (!stockexist.isStockExist())
        {
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
                return stock;
            }).collect(Collectors.toList());
        }
    }

    /***
     *
     * @param customer
     * @param orderlist
     * @return
     * @throws OutOfStockException
     */
    public boolean stockUpdate_Customer(Customer customer, List<Customer>  orderlist) throws OutOfStockException{
        orderlist.add(customer);
        int milkPlaced = customer.getOrder().getMilk();
        int woolPlaced =  customer.getOrder().getWool();
        if ( milkPlaced > stock.getMilk() || woolPlaced > stock.getWools()) {
            customer.setOrder(null);
            customer.setOrderDescription("Out Of Stock");
            throw new OutOfStockException( stock.getMilk(), stock.getWools());
        }
        stock.setMilk(stock.getMilk() - milkPlaced);
        stock.setWools(stock.getWools() - woolPlaced);
        return false;
    }


}
