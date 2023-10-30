package com.ecommerce.controller;

import com.ecommerce.controller.impl.ICartController;
import com.ecommerce.models.*;
import com.ecommerce.models.Product;
import com.ecommerce.models.User;
import com.ecommerce.utils.AppException;
import com.ecommerce.utils.StringUtils;
import com.ecommerce.view.CartPage;

import java.util.ArrayList;

import static com.ecommerce.utils.AppInput.enterInt;
import static com.ecommerce.utils.LoadUtils.getProducts;
import static com.ecommerce.utils.CustomerUtils.getLoggedInUser;
import static com.ecommerce.utils.CustomerUtils.setLoggedInUser;
import static com.ecommerce.utils.Utils.println;

public class CartController implements ICartController {

    private final HomeController homeController;
    private final OrderController orderController;
    private final CartPage cartPage;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController = new OrderController(homeController);
        cartPage = new CartPage();
    }

    @Override
    public void addToCart(int productId) {
        User loggedInUser = getLoggedInUser();
        ArrayList<Product> products = getProducts();

        Product userProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                userProduct = product;
                break;
            }
        }

        if (loggedInUser.getUserCart() != null) {
            Cart cart = loggedInUser.getUserCart();

            boolean isFound = false;
            for (CartProduct cartProduct : cart.getCartProducts()) {
                if (cartProduct.getProduct().getId() == productId) {
                    cartProduct.setCount(cartProduct.getCount() + 1);
                    isFound = true;
                }
            }

            if (!isFound) {
                cart.getCartProducts().add(new CartProduct(userProduct, 1));
            }

            loggedInUser.setUserCart(cart);
        } else {
            Cart cart = new Cart();
            ArrayList<CartProduct> cartProducts = new ArrayList<>();
            cartProducts.add(new CartProduct(userProduct, 1));
            cart.setCartProducts(cartProducts);
            loggedInUser.setUserCart(cart);
        }
        setLoggedInUser(loggedInUser);
    }

    private void checkout() {
        orderController.checkout();
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printCart();
    }

    @Override
    public void printCart() {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser.getUserCart() == null) {
            cartPage.printEmptyCart();
            homeController.printList();
        } else {
            ArrayList<CartProduct> cartProducts = loggedInUser.getUserCart().getCartProducts();
            cartPage.printCart(cartProducts);

            cartPage.printCheckout();
            cartPage.printBack();

            try {
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 11) {
                    checkout();
                } else if (choice == 22) {
                    homeController.printList();
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            } catch (AppException appException) {
                invalidChoice(appException);
            }

        }
    }
}
