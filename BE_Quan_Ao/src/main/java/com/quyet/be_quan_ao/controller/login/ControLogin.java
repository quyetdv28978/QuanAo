package com.quyet.be_quan_ao.controller.login;

import com.quyet.be_quan_ao.Sercurity.JwtTokenProvider;
import com.quyet.be_quan_ao.model.authen.userDetail;
import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.service.SerLogin.SerLoginIF;
import org.springframework.security.authentication.AuthenticationManager;
import com.quyet.be_quan_ao.model.viewModel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/")
public class ControLogin {

    @Autowired
    private SerLoginIF login;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    //    Lay toan bo user trong db
    @GetMapping("user")
    public List<User> Users() {
        return login.getAll();
    }

    //    Api check login
    //    Neu khong tim thay tra ve loi
    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserView user) {
        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getAcc(),
                        user.getPass()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtTokenProvider.generateToken((userDetail) authentication.getPrincipal(), null);
        return ResponseEntity.ok().body(jwt);
    }

    @PostMapping("jwt")
    public ResponseEntity jwt() {
        userDetail userDetail = (userDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jwt = jwtTokenProvider.generateToken(userDetail, userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
        return ResponseEntity.ok().body(UserView.builder().acc(userDetail.getUsername()).jwt(jwt).vaitro(userDetail.getAuthorities().stream().findFirst().get().toString()).build());
    }
}
