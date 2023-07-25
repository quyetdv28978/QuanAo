package com.du1.controller.Controller.web;

import com.du1.model.entity.users;
import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userModel;
import com.du1.respon.JpaUsers;
import com.du1.sercurity.JwtTokenProvider;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class usersController {
    public static userDetail userDetail;

    @Autowired
    public serviceSer jpa2;

    @Autowired
    private serviceSer serUser;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/signup")

    public String signUp(Model model){return "admin/acccount/signup.html";
    }

    @GetMapping("/signin")
    public String signIn(Model model
    ){
        model.addAttribute("user", new userModel());
        return "admin/acccount/signin.html";
    }

    @PostMapping("/signin")
    public String signIN(Model model,@ModelAttribute userModel loginRequest){
//        System.out.println(loginRequest.getTk());
        String vaitro = serUser.finbyTK(loginRequest.getTk()).getVaitro().getTenchucvu();
        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getTk(),
                        loginRequest.getMk()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        userDetail = (com.du1.model.viewModel.userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jwt = jwtTokenProvider.generateToken(userDetail, userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
        System.out.println(userDetail);
        System.out.println(" cua loginJWT");
        model.addAttribute("users", userModel.builder().tk(loginRequest.getTk()).jwt(jwt).role(userDetail.getAuthorities()).build());
        if (vaitro.equalsIgnoreCase("chu")){
            return "redirect:/quan-tri/san-pham";
        }
        return "redirect:/home";
    }
}
