//package com.du1.controller.APi.web;
//
//import com.du1.model.entity.users;
//import com.du1.model.viewModel.userDetail;
//import com.du1.model.viewModel.userModel;
//import com.du1.respon.jpaVaiTro;
////import com.du1.sercurity.JwtTokenProvider;
//import com.du1.services.serviceSer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("api/")
//@CrossOrigin("*")
//public class controllerUsers {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public serviceSer jpa2;
//
//    @Autowired
//    public jpaVaiTro vaiTro;
//
//    @Autowired
//    private serviceSer serUser;
//
////    @Autowired
////    public PasswordEncoder passwordEncoder;
//
//    @PostMapping("users2")
//    public int getAll(@RequestBody users users) {
//        users.setVaitro(vaiTro.getById(2));
//        return jpa2.add(users);
//    }
//
//    @PostMapping("loginJWT")
//    public ResponseEntity authenticateUser(@RequestBody userModel loginRequest) {
//        String vaitro = serUser.finbyTK(loginRequest.getTk()).getVaitro().getTenchucvu();
//        // Xác thực thông tin người dùng Request lên
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getTk(),
//                        loginRequest.getMk()
//                )
//        );
//
//        // Nếu không xảy ra exception tức là thông tin hợp lệ
//        // Set thông tin authentication vào Security Context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // Trả về jwt cho người dùng.
//        userDetail userDetail = (com.du1.model.viewModel.userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String jwt = jwtTokenProvider.generateToken(userDetail, userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
//        return ResponseEntity.ok().body(userModel.builder().tk(loginRequest.getTk()).jwt(jwt).role(userDetail.getAuthorities()).build());
//    }
//
//    @PostMapping("loginJWT2")
//    public ResponseEntity authenticateUser2(@RequestBody userModel loginRequest) {
//        return ResponseEntity.ok().body(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    }
//
//}
