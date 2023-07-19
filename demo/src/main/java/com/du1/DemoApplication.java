package com.du1;

//import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
      ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);

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
