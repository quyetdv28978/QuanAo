package com.du1.controller.Controller.web;

import com.du1.model.entity.users;
import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userModel;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class homeController {

    @Autowired
HttpSession session;

    @Autowired
    private JpaUsers jpaUsers;

    @GetMapping("home")
    public String home(Model model, Principal principal){
//        String name = ((Authentication)principal).getName();
//        System.out.println(((Authentication)principal).getName() + " hmmmm");
//
//        if (name != null)   {
//            System.out.println("aaaaaaaaaaaaaaaaaaaaa");
//            users user = jpaUsers.findByTk(name);
//            session.setAttribute("userSS", user);
//            session.setMaxInactiveInterval(60*60);
////            if (users.getCheck() == true){
////                response.addCookie(new Cookie(user.getTk(), user.getMk()));
////            }
//if (user.getVaitro().getId() == 1){
//    return "redirect:/home";
//}
//else if (user.getVaitro().getId() == 2){
//    return "redirect:/quan-tri/san-pham";
//}
//        }
//        else{
//            model.addAttribute("user", new userModel());
//        }
//        return "admin/acccount/signin.html";
//

        System.out.println("sessinoUser" + session.getAttribute("userSS"));
        model.addAttribute("users", session.getAttribute("userSS"));
////        httpSession.setAttribute("userSS", );
        return "web/index.html";
    }
}
