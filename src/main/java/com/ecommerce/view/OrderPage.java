package com.ecommerce.view;

import com.ecommerce.utils.StringUtils;

import java.util.Map;

import static com.ecommerce.utils.Utils.println;

public class OrderPage {
    public void printSuccess() {
        try {
            println("#######################################");
            println(StringUtils.PLACE_ORDER);
            println("#######################################");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrders(Map<String, String> files) {
        println("#######################################");
        println(StringUtils.ORDERS);
        println("#######################################");
        int id = 1;
        for (Map.Entry<String, String> entry : files.entrySet()) {
            println(id + ". Date = " + entry.getKey() + " OrderId = " + entry.getValue());
            id++;
        }
        println(StringUtils.BACK_OPTION);
    }

    public void printDesign() {
        println("#######################################");
    }

    public void printNoOrders() {
        try {
            println("#######################################");
            println(StringUtils.NO_ORDER);
            println("#######################################");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
