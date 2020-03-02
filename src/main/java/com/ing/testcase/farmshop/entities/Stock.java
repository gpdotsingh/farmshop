package com.ing.testcase.farmshop.entities;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Stock {
    private int milk;
    private int wools;
}
