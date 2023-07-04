package com.du1.controller.Controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class shoppingCartController {

    @Autowired
    HttpSession httpSession;

    @GetMapping("shoping-cart")
    public String cart(Model model){
        System.out.println(httpSession.getAttribute("userSS"));
        model.addAttribute("users",httpSession.getAttribute("userSS"));
        return "web/shoping-cart.html";
    }
}
