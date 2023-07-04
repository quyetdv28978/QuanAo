package com.du1.controller.Controller.web;

import com.du1.model.entity.users;
import com.du1.model.viewModel.userModel;
import com.du1.respon.JpaUsers;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class usersController {
    @Autowired
    public serviceSer jpa2;

    @Autowired
    public HttpSession session;

    @Autowired
    private JpaUsers jpaUsers;

    @GetMapping("/signup")

    public String signUp(Model model){return "admin/acccount/signup.html";
    }

    @GetMapping("/signin")
    public String signIn(Model model,
                         HttpServletResponse response, HttpServletRequest resquest
    , Principal principal
    ){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Day la authentication: " + authentication.getPrincipal());

//        System.out.println(model.getAttribute("user"));
        System.out.println("la sao la sao");
        System.out.println(((users)(Authentication)principal) + " hmmmm");

        boolean check = true;
        if ( session.getAttribute("userSS") != null){
            for (Cookie a:resquest.getCookies()
                 ) {
                if (jpa2.finbyTK(a.getName()) != null){
                    System.out.println("check");
                    model.addAttribute("user", new userModel(a.getName(), a.getValue(), true));
                    check = false;
                    break;
                }
            }
            if (check == true){
                model.addAttribute("user", new userModel());
            }
        }
        else {
            model.addAttribute("user", new userModel());
        }
        return "admin/acccount/signin.html";
    }


    @GetMapping("signinv2")
    public String home(Model model, Principal principal){
        String name = ((Authentication)principal).getName();
        System.out.println(((Authentication)principal).getName() + " hmmmm");

        if (name != null)   {
            System.out.println("aaaaaaaaaaaaaaaaaaaaa");
            users user = jpaUsers.findByTk(name);
            session.setAttribute("userSS", user);
            session.setMaxInactiveInterval(60*60);
//            if (users.getCheck() == true){
//                response.addCookie(new Cookie(user.getTk(), user.getMk()));
//            }
            if (user.getVaitro().getId() == 1){
                return "redirect:/home";
            }
            else if (user.getVaitro().getId() == 2){
                return "redirect:/quan-tri/san-pham";
            }
        }
        else{
            model.addAttribute("user", new userModel());
        }
        return "admin/acccount/signin.html";

//        model.addAttribute("users", session.getAttribute("userSS"));
//        httpSession.setAttribute("userSS", );
//        return "web/index.html";
    }


}
