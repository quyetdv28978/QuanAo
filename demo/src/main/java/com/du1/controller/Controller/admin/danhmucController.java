package com.du1.controller.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/danh-muc")
public class danhmucController {
    @GetMapping()
    public String danhmuc(Model model){
        return "admin/category/DanhMuc.html";
    }

    @GetMapping("/them-danh-muc")
    public String addDanhMuc(Model model){
        return "admin/category/themDanhMuc.html";
    }

    @GetMapping("/sua-danh-muc")
    public String UDDanhMuc(Model model,@RequestParam Integer id){
        System.out.println("ID trong server dm: "+ id);
        model.addAttribute("id", id);
        return "admin/category/SuaDanhMuc.html";
    }
}
