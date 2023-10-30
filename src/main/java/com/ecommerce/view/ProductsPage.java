package com.ecommerce.view;

import com.ecommerce.models.Product;
import com.ecommerce.utils.StringUtils;

import java.util.ArrayList;

import static com.ecommerce.utils.Utils.println;

public class ProductsPage {
    public void printProducts(ArrayList<Product> products) {
        println(StringUtils.PRODUCT_MENU);
        for (Product product : products) {
            println(product.getId() + ". " + product.getTitle() + " - ₹" + product.getPrice());
        }
        println(StringUtils.BACK_OPTION);
    }

    public void printSuccess() {
        println(StringUtils.CART_SUCCESS);
    }
}
