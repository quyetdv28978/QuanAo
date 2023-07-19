package com.du1.controller.Controller.web;

import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("users", usersController.userDetail);
        return "web/shoping-cart.html";
    }
}
