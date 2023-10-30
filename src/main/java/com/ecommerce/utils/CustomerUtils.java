package com.ecommerce.utils;

import com.ecommerce.models.User;

public class CustomerUtils {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        CustomerUtils.loggedInUser = loggedInUser;
    }
}
