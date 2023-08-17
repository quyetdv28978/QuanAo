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
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class usersController {
    public static userDetail userDetail;
    public static String jwtUser;

    @Autowired
    public serviceSer jpa2;

    @Autowired
    private serviceSer serUser;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletResponse req;

    @Autowired
    HttpServletRequest reqe;

    @GetMapping("/signup")

    public String signUp(Model model) {
        return "admin/acccount/signup.html";
    }

    @GetMapping("/signin")
    public String signIn(Model model
    ) {
        model.addAttribute("user", new userModel());

        if (session.getAttribute("errorLogin") != null) {
            model.addAttribute("errorLogin2", 1);
            session.removeAttribute("errorLogin");
        }
//        if (reqe.getCookies() != null) {
//            Optional a = Arrays.stream(reqe.getCookies())
//                    .filter(i -> !i.getName().equals("JSESSIONID"))
//                    .map(i -> {
//                        if (i.getName().equals("account")) {
//                            return i.getValue();
//                        }
//                        return null;
//                    }).findFirst();
//            if (a.isPresent()) {
//                userModel u = userModel.builder().tk(a.get().toString().substring(a.get().toString().indexOf(".") + 1, a.get().toString().length()))
//                        .mk(a.get().toString().substring(0, a.get().toString().indexOf(".")))
//                        .build();
//                model.addAttribute("user", u);
//            }
//        }
        return "admin/acccount/signin.html";
    }

    @PostMapping("/signin")
    public String signIN(Model model, @ModelAttribute userModel loginRequest) {
        users user = serUser.finbyTK(loginRequest.getTk());
        if (user != null) {
            {
                if (loginRequest.getCheck() != null) {
                    req.addCookie(new Cookie("account", loginRequest.getMk() + "." + loginRequest.getTk()));
                }
                String vaitro = user.getVaitro().getTenchucvu();
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
                jwtUser = jwtTokenProvider.generateToken(userDetail, userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
                if (vaitro.equalsIgnoreCase("chu")) {
                    return "redirect:/trang-chu";
                }
                return "redirect:/home";
            }
        } else {
            session.setAttribute("errorLogin", 0);
            return "redirect:/signin";
        }
    }
}
