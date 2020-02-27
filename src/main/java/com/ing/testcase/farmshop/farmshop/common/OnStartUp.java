package com.ing.testcase.farmshop.farmshop.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.farmshop.entities.Flock;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.entities.Stock;
import com.ing.testcase.farmshop.farmshop.entities.Stockexist;
import com.ing.testcase.farmshop.farmshop.service.FarmShopService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

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
