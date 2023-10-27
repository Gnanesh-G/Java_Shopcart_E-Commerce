package com.ecommerce.controller;

import com.ecommerce.controller.Impl.IAppController;
import com.ecommerce.view.WelcomePage;

public class AppController implements IAppController {
  private final WelcomePage welcomePage;
  private final AuthController authController;

  public AppController() {
    welcomePage = new WelcomePage();
    authController = new AuthController(this);
  }

  @Override
  public void init() {
    welcomePage.welcomeMessage();
    welcomePage.authMenu();
    welcomePage.authComment();

    authController.authMenu();
  }
  public void printAuthMenu() {
    welcomePage.printAuthMenu();
  }

}