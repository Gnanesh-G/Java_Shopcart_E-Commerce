package com.ecommerce.controller;

import com.ecommerce.controller.Impl.IAuthController;
import com.ecommerce.view.LoginPage;
import com.ecommerce.view.RegisterPage;

public class AuthController implements IAuthController {
    private final AppController appController;
    private final HomeController homeController;

    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    @Override
    public void authMenu() {


    }

    @Override
    public void login() {

    }

    @Override
    public void register() {

    }
}
