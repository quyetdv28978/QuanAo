package com.du1.services;

import com.du1.configuration.config;
import com.du1.model.entity.users;
//import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userDetail;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpringSecuritySer implements UserDetailsService {
    @Autowired
    public JpaUsers jpaUsers;

    @Override
    public UserDetails loadUserByUsername(String tk) throws UsernameNotFoundException {
        users u = jpaUsers.findByTk(tk);
        System.out.println(tk + " null? vai tro: " + u.getVaitro().getId());

        return new userDetail(u);
    }

}
