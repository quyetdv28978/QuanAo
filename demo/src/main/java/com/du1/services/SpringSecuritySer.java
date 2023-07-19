package com.du1.services;

import com.du1.model.entity.users;
//import com.du1.model.viewModel.userDetail;
import com.du1.model.viewModel.userDetail;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public UserDetails loadUserById(Integer id) {
        users user = jpaUsers.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User khong ton tai voi id : " + id)
        );

        return new userDetail(user);
    }

}
