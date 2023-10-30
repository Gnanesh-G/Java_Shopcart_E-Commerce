package com.ecommerce.view;

import com.ecommerce.utils.StringUtils;

import static com.ecommerce.utils.Utils.println;


public class WelcomePage {
    public void welcome() {
        try {
            println("#######################################");
            println(StringUtils.WELCOME_MESSAGE);
            println("#######################################");

            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
