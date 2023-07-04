package com.du1.controller.APi.admin;

import com.du1.model.entity.users;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testAPI{

    @Autowired
    private serviceSer ser;

    @GetMapping("api/test")
    public void test(){
        ser.add(users.builder().id(1).tk("test").mk("mk").email("a").build());
    }
}
