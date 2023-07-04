package com.du1;

import com.du1.model.entity.users;
import com.du1.respon.jpaVaiTro;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Autowired
//    serviceSer userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    jpaVaiTro vaitro;

//    implements CommandLineRunner

//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        users user = new users();
//        user.setTk("admin");
//        user.setMk(passwordEncoder.encode("quyet"));
//        user.setVaitro(vaitro.findById(2).get());
//        user.setEmail("quyet@gamil.com");
//        userRepository.add(user);
//        System.out.println(user);
//    }
}
