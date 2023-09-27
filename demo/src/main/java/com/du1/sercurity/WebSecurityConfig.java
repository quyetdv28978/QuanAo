package com.du1.sercurity;

import com.du1.Exception.RestAuthenticationEntryPoint;
import com.du1.controller.Controller.web.usersController;
import com.du1.model.entity.users;
import com.du1.model.viewModel.CustomOAuth2User;
import com.du1.respon.JpaUsers;
import com.du1.services.CustomOAuth2UserService;
import com.du1.services.SpringSecuritySer;
import com.du1.services.serviceSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    @Autowired
    private serviceSer userService;

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
    @Autowired
    JwtTokenProvider jwtTokenProvider;
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
                        ("/home", "/product", "/shoping-cart", "/signin", "/signinv2",
                                "/signup", "/signout",
                                "/api/**", "/loginJWT", "/oauth/**","/detail")
                .permitAll()
                .antMatchers("/trang-chu", "/quan-tri/san-pham", "/quan-tri/them-san-pham",
                        "/quan-tri/sua-san-pham", "/danh-muc", "/danh-muc/them-danh-muc",
                        "/danh-muc/sua-danh-muc").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(restServicesEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.oauth2Login()
                .loginPage("/signin")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler((request, response, authentication) -> {
                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                    users user = userService.finbyTK(oauthUser.getName());
                    if (user == null) {
                        userService.add(user.builder()
                                .email(oauthUser.getEmail())
                                .tk(oauthUser.getName())
                                .build()
                        );
                    }

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // Trả về jwt cho người dùng.
                    CustomOAuth2User userDetail = (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String jwtUser = jwtTokenProvider.generateToken(com.du1.model.viewModel.userDetail.builder()
                                    .users(user)
                                    .build()
                            , userDetail.getAuthorities().stream().collect(Collectors.toList()).get(0).toString());
                    usersController.jwtUser = jwtUser;
                    response.sendRedirect("/demov1/home");
                });

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/angular/**", "/bootstrap/**", "/css/**", "/fonts/**"
                , "/imagePath/**", "/images/**", "/js/**", "/vendor/**", "/assets/**"
        );
    }
}
