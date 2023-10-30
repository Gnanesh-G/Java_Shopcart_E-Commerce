package com.ecommerce.controller;

import com.ecommerce.controller.impl.IHomeController;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtils;
import com.ecommerce.view.HomePage;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.CustomerUtils.setLoggedInUser;
import static com.ecommerce.utils.Utils.println;

public class HomeController implements IHomeController {

    private final HomePage homePage;
    private final AuthController authController;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final CartController cartController;
    private final OrderController orderController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        productController = new ProductController(this);
        categoryController = new CategoryController(this);
        cartController = new CartController(this);
        orderController=new OrderController(this);
    }

    @Override
    public void printList() {
        homePage.printList();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                categoryController.printMenu();
            } else if (choice == 2) {
                productController.showProducts(0);
            } else if (choice == 3) {
                cartController.printCart();
            } else if (choice == 4) {
                orderController.viewOrders();
                printList();
            } else if (choice == 5) {
                setLoggedInUser(null);
                authController.authList();
            } else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printList();
    }
}
