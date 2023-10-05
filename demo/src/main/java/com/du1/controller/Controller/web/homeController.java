package com.du1.controller.Controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class homeController {

//    @Autowired
//    private JpaUsers jpaUsers;

    @GetMapping("home")
    public String home(Model model, Principal principal){
//        model.addAttribute("users", usersController.jwtUser);
        return "web/index.html";
    }
}
