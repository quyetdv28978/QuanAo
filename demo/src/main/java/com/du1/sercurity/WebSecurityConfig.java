package com.du1.sercurity;

import com.du1.Exception.RestAuthenticationEntryPoint;
import com.du1.model.entity.users;
import com.du1.respon.JpaUsers;
import com.du1.services.SpringSecuritySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public SpringSecuritySer springSecuritySer;
    @Autowired
    public JpaUsers jpa;

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public jwtAuthenticationFilter jwtAuthenticationFilter() {
        return new jwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

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
        http.csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/signin").permitAll();

        http.cors().and()
                .authorizeRequests()
                .antMatchers
                        ("/home", "/product", "/shoping-cart", "/signin", "/signinv2", "/signup", "/api/**", "/loginJWT")
                .permitAll()
                .antMatchers("/quan-tri/san-pham", "/quan-tri/them-san-pham",
                        "/quan-tri/sua-san-pham", "/danh-muc", "/danh-muc/them-danh-muc",
                        "/danh-muc/sua-danh-muc").hasRole("ADMIN")
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/signinv2")
//                .loginPage("/signin")
//                .usernameParameter("tk")
//                .passwordParameter("mk")
//                .defaultSuccessUrl("/signinv2")
//                // Cho phép người dùng xác thực bằng form login
//                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//                .and()
//                .logout().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(restServicesEntryPoint()).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/angular/**", "/bootstrap/**", "/css/**", "/fonts/**"
                , "/imagePath/**", "/images/**", "/js/**", "/vendor/**"
        );
    }
}
