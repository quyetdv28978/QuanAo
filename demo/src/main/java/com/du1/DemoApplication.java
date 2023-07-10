package com.du1;

//import com.du1.services.serviceSer;
import com.du1.testConfi.configuration1;
import com.du1.testConfi.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.du1.testConfi")
public class DemoApplication {
//    @Autowired
//    @Qualifier("quyet2")
//    private static student studen;

    public static void main(String[] args) {
      ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
      student s = context.getBean("quyet2",student.class);
//        System.out.println(s.getStu());
        student a = context.getBean("quye1",student.class);
        System.out.println(s);
        System.out.println(a);
//        student a = context.getBean(student.class);
//        System.out.println(studen);
//        System.out.println(@Qualifier("quyet2") a);
//        student a = context.getBean(configuration1.class);
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
