package com.ing.testcase.farmshop.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.entities.Stock;
import com.ing.testcase.farmshop.service.FarmShopService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class OnStartUp implements InitializingBean {

    @Autowired
    Stock stock;
    @Autowired
    FarmShopService farmShopService;
    @Autowired
    Flocks flocks;

    @PostConstruct
    public void init() throws JsonMappingException, JsonProcessingException, IOException {
        flocks.setFlock(farmShopService.extracted().getFlock());
        farmShopService.getStock();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
