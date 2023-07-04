package com.du1.controller.Controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class logout {

    @Autowired
    private HttpSession htpHttpSession;

    @GetMapping("logout")
    public String logout(Model model){
        if (htpHttpSession.getAttribute("userSS") != null){
            htpHttpSession.removeAttribute("userSS");
        }
        return "redirect:/home";
    }
}
