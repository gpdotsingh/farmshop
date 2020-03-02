package com.ing.testcase.farmshop.common;

public class CheckInputtypeException extends RuntimeException{
    public CheckInputtypeException(int milk,int wool) {
        super(String.format("Left milk in stock is: %d and Left wool in stock is: %d . Shopkeeper notified", milk,wool));
    }
}
