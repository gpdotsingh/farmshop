package com.ing.testcase.farmshop.farmshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ing.testcase.farmshop.farmshop.entities.Flocks;

import java.io.IOException;

public interface FarmshopService {
    public Flocks extracted() throws JsonProcessingException, JsonMappingException, IOException;
}
