package com.quyet.be_quan_ao.Sercurity;

import com.quyet.be_quan_ao.model.authen.CustomOAuth2User;
import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.respon.login.ResUser;
import com.quyet.be_quan_ao.service.Authen.CustomOAuth2UserService;
import com.quyet.be_quan_ao.service.Authen.SpringSecuritySer;
import com.quyet.be_quan_ao.service.SerLogin.SerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
//         securedEnabled = true,
//         jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public SpringSecuritySer springSecuritySer;

    @Autowired
    public ResUser jpa;

//    @Bean
//    public RestAuthenticationEntryPoint restServicesEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }

    @Bean
    public jwtAuthenticationFilter jwtAuthenticationFilter() {
        return new jwtAuthenticationFilter();
    }

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cung cáp userservice cho spring security
    // cung cấp password encoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(springSecuritySer)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors().and()
                .authorizeRequests()
                .antMatchers
                        ("/oauth/**", "/v1/api/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.oauth2Login()
////                .loginPage("/login")
//                .userInfoEndpoint()
//                .userService(oauthUserService)
//                .and()
//                .successHandler((request, response, authentication) -> {
//                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//                    User user = jpa.findByAcc(oauthUser.getName());
//                    if (user == null) {
//                        jpa.save(user.builder()
//                                .email(oauthUser.getEmail())
//                                .acc(oauthUser.getName())
//                                .build()
//                        );
//                    }
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    // Trả về jwt cho người dùng.
//                    CustomOAuth2User userDetail = (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                    String jwtUser = jwtTokenProvider.generateToken(com.quyet.be_quan_ao.model.authen.userDetail.builder()
//                                    .users(user)
//                                    .build()
//                            , userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
//                    System.out.println("jwt " + jwtUser);
////                    response.sendRedirect("/demov1/home");
//                });

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
