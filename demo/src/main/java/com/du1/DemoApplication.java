package com.du1;

//import com.du1.services.serviceSer;
import com.du1.model.entity.Chitiethoadon;
import com.du1.model.entity.Hoadon;
import com.du1.model.entity.SanPham;
import com.du1.respon.jpaHoadon;
import com.du1.respon.jpaSanPham;
import com.du1.respon.jpaChitiethoadon;
import com.du1.respon.jpaThongKe;
import com.du1.services.SanPhamSer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws ParseException {
      ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
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
