package com.ecommerce.view;

import com.ecommerce.utils.StringUtils;

import static com.ecommerce.utils.Utils.println;

public class HomePage {
    public void printList() {
        println(StringUtils.WELCOME_MESSAGE);
        println(StringUtils.HOME_MENU);
    }
}
