package com.quyet.be_quan_ao.service.Authen;

import com.quyet.be_quan_ao.model.authen.userDetail;
import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.respon.login.ResUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SpringSecuritySer implements UserDetailsService {
    @Autowired
    public ResUser jpaUsers;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User u = jpaUsers.findByAcc(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println(u);
        return new userDetail(u);
    }

    @Transactional
    public UserDetails loadUserById(Integer id) {
        User user = jpaUsers.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User khong ton tai voi id : " + id)
        );

        return new userDetail(user);
    }

}
