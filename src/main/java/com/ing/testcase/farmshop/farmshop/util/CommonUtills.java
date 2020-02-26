package com.ing.testcase.farmshop.farmshop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ing.testcase.farmshop.farmshop.FarmshopApplication;
import com.ing.testcase.farmshop.farmshop.common.AppConfig;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

public class CommonUtills {

    @Autowired
    AppConfig appConfig;

    public  Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new XmlMapper();
        ClassLoader classLoader = new FarmshopApplication().getClass().getClassLoader();
        File fXmlFile = new File(classLoader.getResource("Inputflock.xml").getFile());
        // Reads from XML and converts to POJO
        Flocks flocks = objectMapper.readValue(fXmlFile,Flocks.class);
        return flocks;
    }

}
