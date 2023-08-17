package com.du1.controller.Controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class shoppingCartController {

    @GetMapping("shoping-cart")
    public String cart(Model model){
        model.addAttribute("users", usersController.userDetail);
        return "web/shoping-cart.html";
    }
}
