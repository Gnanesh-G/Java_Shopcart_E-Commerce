package com.ecommerce.controller;

import com.ecommerce.controller.impl.ICategoryController;
import com.ecommerce.models.Category;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtils;
import com.ecommerce.view.CategoryPage;

import java.util.ArrayList;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.LoadUtils.getCategories;
import static com.ecommerce.utils.Utils.println;

public class CategoryController implements ICategoryController {

    private final CategoryPage categoryPage;
    private final ProductController productController;
    private final HomeController homeController;

    public CategoryController(HomeController homeController) {
        categoryPage = new CategoryPage();
        productController = new ProductController(homeController);
        this.homeController = homeController;
    }

    @Override
    public void printMenu() {
        ArrayList<Category> categories = getCategories();
        categoryPage.printMenu(categories);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);

            if (choice == 99) {
                homeController.printList();
            } else {
                int validCategoryId = 0;

                for (Category category : categories) {
                    if (category.getId() == choice) {
                        validCategoryId = category.getId();
                        break;
                    }
                }

                if (validCategoryId != 0) {
                    productController.showProducts(validCategoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printMenu();
    }
}
