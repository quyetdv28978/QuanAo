package com.du1.services;

import com.du1.model.entity.users;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class serviceSer implements ServiceIF<users> {
    @Autowired()
    private JpaUsers jpa;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public List<users> getAll() {
        return jpa.findAll();
    }

    @Override
    @Transactional
    public Integer add(users users) {
        Integer check = 0;
        if (users.getMk() == null
                || users.getTk() == null
                || users.getEmail() == null
                || users.getDiachi() == null) {
            check = 0;
        } else if (jpa.findByTk(users.getTk()) != null) {
            check = 1;
        }else if (jpa.findByEmail(users.getEmail()) != null) {
            check = 2;
        } else if (!users.getDiachi().equals(users.getMk())) {
            check = 3;
        } else {
//            users.setMk(passwordEncoder.encode(users.getMk()));
//            check = jpa.save(users).getId();
        }
        return check;
    }

    @Override
    @Transactional
    public int update(users users) {
        return jpa.save(users).getId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
//        Optional option = jpa.findById(Integer.parseInt(id));
//        if (option.isPresent()) {
//            jpa.delete((users)option.get());
//            return 1;
//        }
//         return 0;
    }

    public users finbyName(String email, String name) {
        return jpa.findByEmailAndMk(email, name);
    }

    public users finbyTK(String tk) {
        return jpa.findByTk(tk);
    }

    public Object cd(users o, boolean check) {
        return null;
    }
}
