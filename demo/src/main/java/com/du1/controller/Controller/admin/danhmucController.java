package com.du1.controller.Controller.admin;

import com.du1.controller.Controller.web.usersController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/danh-muc")
public class danhmucController {
    @GetMapping()
//    @PreAuthorize("hasRole('ADMIN')")
    public String danhmuc(Model model) {
        if (usersController.userDetail != null &&
                usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ROLE_ADMIN")) {
            return "admin/category/DanhMuc.html";
        }
        return "admin/error.html";
    }

    @GetMapping("/them-danh-muc")
//    @PreAuthorize("hasRole('ADMIN')")
    public String addDanhMuc(Model model) {
        if (usersController.userDetail != null &&
                usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ROLE_ADMIN")) {
            return "admin/category/themDanhMuc.html";
        }
        return "admin/error.html";
    }

    @GetMapping("/sua-danh-muc")
//    @PreAuthorize("hasRole('ADMIN')")
    public String UDDanhMuc(Model model, @RequestParam Integer id) {
        if (usersController.userDetail != null &&
                usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ROLE_ADMIN")) {
            model.addAttribute("id", id);
            return "admin/category/SuaDanhMuc.html";
        }
        return "admin/error.html";
    }
}
