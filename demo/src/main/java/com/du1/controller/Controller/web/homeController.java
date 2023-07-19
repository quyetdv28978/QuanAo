package com.du1.controller.Controller.web;

import com.du1.model.entity.users;
import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userModel;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class homeController {

    @Autowired
    private JpaUsers jpaUsers;

    @GetMapping("home")
    public String home(Model model, Principal principal){
        model.addAttribute("users", usersController.userDetail);
        return "web/index.html";
    }
}
