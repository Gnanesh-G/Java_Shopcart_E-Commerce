package com.ecommerce.controller;

import com.ecommerce.controller.impl.IProductController;
import com.ecommerce.models.Product;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtils;
import com.ecommerce.view.ProductsPage;

import java.util.ArrayList;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.LoadUtils.getProducts;
import static com.ecommerce.utils.Utils.println;

public class ProductController implements IProductController {

    private int categoryId = 0;
    private final ProductsPage productsPage;
    private final CartController cartController;
    private final HomeController homeController;

    public ProductController(HomeController homeController) {
        productsPage = new ProductsPage();
        this.homeController = homeController;
        cartController = new CartController(homeController);
    }

    @Override
    public void showProducts(int categoryId) {
        this.categoryId = categoryId;
        ArrayList<Product> products = getProducts();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory().getId() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            products = categoryProducts;
        }

        productsPage.printProducts(products);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            int validProductId = 0;

            if (choice == 99) {
                homeController.printList();
            } else {
                for (Product product : products) {
                    if (product.getId() == choice) {
                        validProductId = product.getId();
                        break;
                    }
                }

                if (validProductId != 0) {
                    cartController.addToCart(validProductId);
                    productsPage.printSuccess();
                    showProducts(categoryId);
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
        showProducts(categoryId);
    }
}
