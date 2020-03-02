package com.ing.testcase.farmshop.entities;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Stockexist {
    private boolean isStockExist;
}
