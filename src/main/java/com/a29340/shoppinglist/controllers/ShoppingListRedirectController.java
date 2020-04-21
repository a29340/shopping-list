package com.a29340.shoppinglist.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingListRedirectController implements ErrorController {

  public static final String ERROR_PATH = "/error";

  @RequestMapping(ERROR_PATH)
  String redirect(){
    return "forward:index.html";
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}
