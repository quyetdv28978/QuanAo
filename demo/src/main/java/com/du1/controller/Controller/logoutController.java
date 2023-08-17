package com.du1.controller.Controller;

import com.du1.controller.Controller.web.usersController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logoutController {
    @GetMapping("/signout")
    public String logout(){
        usersController.userDetail = null;
        usersController.jwtUser = null;
        return "redirect:/home";
    }
}
