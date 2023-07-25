package com.du1.controller.Controller.admin;

import com.du1.controller.Controller.web.usersController;
import com.du1.model.entity.SanPham;
import com.du1.respon.jpaSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/quan-tri")
public class quantriController {
    @Autowired
private jpaSanPham jpaSanPham;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/trang-chu")
    public String quanTri(Model model){
        if (usersController.userDetail != null &&usersController.userDetail.getAuthorities().stream().toList().get(0).equals("ADMIN")) {

            return "admin/TrangChu.html";
        }
        return "admin/error.html";

    }

    @GetMapping("/san-pham")
    public String sanPham(Model model){
        if (usersController.userDetail != null && usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ADMIN")) {
            model.addAttribute("listSP", jpaSanPham.findAll());
            return "admin/product/SanPham.html";
        }
        return "admin/error.html";
    }

    @GetMapping("/them-san-pham")
    public String addsanPham(Model model){
        System.out.println(usersController.userDetail.getAuthorities());
        System.out.println(usersController.userDetail.getAuthorities().stream().toList().get(0).toString() + " tren");
        if (usersController.userDetail != null && usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ADMIN")) {
            model.addAttribute("sanpham", new SanPham());
            return "admin/product/themSanPham.html";
        }
            return "admin/error.html";

        }

    @GetMapping("/sua-san-pham")
    public String UDsanPham(Model model, @RequestParam Integer id){
            if (usersController.userDetail != null && usersController.userDetail.getAuthorities().stream().toList().get(0).toString().equals("ADMIN")) {
                model.addAttribute("id", id);
                return "admin/product/SuaSanPham.html";
            }
            return "admin/error.html";

        }
}
