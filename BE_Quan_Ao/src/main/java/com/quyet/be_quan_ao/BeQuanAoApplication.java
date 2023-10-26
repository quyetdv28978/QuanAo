package com.quyet.be_quan_ao;

import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.respon.login.ResUser;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class BeQuanAoApplication{

    public static void main(String[] args) {
        SpringApplication.run(BeQuanAoApplication.class, args);
    }
//
//    @Autowired
//    ResUser resUser;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User u = resUser.findById(1).get();
//        u.setPass(passwordEncoder.encode("1"));
//        resUser.save(u);
//    }
}
