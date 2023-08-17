package com.du1.controller.Controller.admin;

import com.du1.controller.Controller.web.usersController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardController {

    @GetMapping("/trang-chu")
    public String dashboard (Model model){
        if (usersController.userDetail != null
                && usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ROLE_ADMIN")) {
            model.addAttribute("users", usersController.jwtUser);
            return "/web/dashboard.html";
        }
        return "admin/error.html";
    }
}
