package com.du1.controller.Controller.admin;

import com.du1.controller.Controller.web.usersController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controllerDetail {

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam(name = "id")Integer id){
        System.out.println(id);
        model.addAttribute("lasao", id);
        model.addAttribute("users", usersController.jwtUser);
        return "/web/detatil.html";
    }
}
