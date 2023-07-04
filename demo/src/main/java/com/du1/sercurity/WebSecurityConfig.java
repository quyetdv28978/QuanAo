package com.du1.sercurity;

import com.du1.model.entity.users;
import com.du1.respon.JpaUsers;
import com.du1.services.SpringSecuritySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public SpringSecuritySer springSecuritySer;
    @Autowired
    public JpaUsers jpa;


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(springSecuritySer) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers
                        ("/home", "/product", "/shoping-cart", "/signin","/signinv2", "/signup","/api/**").permitAll()
                .antMatchers("/quan-tri/san-pham","/quan-tri/them-san-pham",
                        "/quan-tri/sua-san-pham","/danh-muc","/danh-muc/them-danh-muc",
                        "/danh-muc/sua-danh-muc").hasRole("ADMIN")
             // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                .and()

                .formLogin()
                .loginProcessingUrl("/signinv2")
                .loginPage("/signin")
                .usernameParameter("tk")
                .passwordParameter("mk")
                .defaultSuccessUrl("/signinv2")
                // Cho phép người dùng xác thực bằng form login
                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                .and()
                .logout() // Cho phép logout
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/angular/**", "/bootstrap/**", "/css/**","/fonts/**"
        ,"/imagePath/**", "/images/**","/js/**","/vendor/**"
        );
    }
}
