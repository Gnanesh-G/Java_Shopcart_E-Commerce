package com.ecommerce.view;

import com.ecommerce.models.Category;
import com.ecommerce.utils.StringUtils;

import java.util.ArrayList;

import static com.ecommerce.utils.Utils.println;

public class CategoryPage {
    public void printMenu(ArrayList<Category> categories) {
        println(StringUtils.CATEGORY_MENU);
        for (Category category : categories) {
            println(category.getId() + ". " + category.getCategoryName());
        }
        println(StringUtils.BACK_OPTION);
    }
}
