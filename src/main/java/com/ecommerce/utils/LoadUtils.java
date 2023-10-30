package com.ecommerce.utils;

import com.ecommerce.models.Category;
import com.ecommerce.models.Product;

import java.util.ArrayList;

public class LoadUtils {

    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public static void load() {
        Category watchCategory = new Category(1, "Watch");
        categories.add(watchCategory);
        Category mobileCategory = new Category(2, "Mobile");
        categories.add(mobileCategory);
        Category shoeCategory = new Category(3, "Shoe");
        categories.add(shoeCategory);

        Product watchProduct = new Product(1, "Watch", "Watch", 1500, 20, watchCategory);
        Product smartWatchProduct = new Product(2, "SmartWatch", "SmartWatch", 2000, 20, watchCategory);
        products.add(watchProduct);
        products.add(smartWatchProduct);

        Product lavaPhonehoneProduct = new Product(3, "Lava Note 10", "Lava Note 10", 1000, 10, mobileCategory);
        Product redmiPhoneProduct = new Product(4, "Redmi Note 11", "Redmi Note 11", 15000,10, mobileCategory);
        products.add(lavaPhonehoneProduct);
        products.add(redmiPhoneProduct);

        Product shoeProduct = new Product(5, "Shoe", "Shoe", 1500, 20, shoeCategory);
        Product formalShoeProduct = new Product(6, "Formal Shoe", "Formal Shoe", 2000, 20, shoeCategory);
        products.add(shoeProduct);
        products.add(formalShoeProduct);

    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
}
