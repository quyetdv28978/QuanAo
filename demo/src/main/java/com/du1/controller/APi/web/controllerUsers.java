package com.du1.controller.APi.web;

import com.du1.model.entity.users;
import com.du1.respon.jpaVaiTro;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class controllerUsers {
    @Autowired
    public serviceSer jpa2;
    @Autowired
    public jpaVaiTro vaiTro;
//    @Autowired
//    public PasswordEncoder passwordEncoder;

@PostMapping("/api/users2")
    public int getAll(@RequestBody users users) {
    users.setVaitro(vaiTro.getById(2));
    return jpa2.add(users);
    }

    @GetMapping("/api/users1/{tk}")
    public users singnIn(@PathVariable String tk){
        System.out.println(jpa2.finbyTK(tk));
if (jpa2.finbyTK(tk) != null)   {
    return jpa2.finbyTK(tk);
}
else return null;

}

}
