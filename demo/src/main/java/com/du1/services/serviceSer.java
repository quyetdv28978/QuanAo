package com.du1.services;

import com.du1.model.entity.users;
import com.du1.respon.JpaUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class serviceSer implements ServiceIF<users> {
    @Autowired()
   private JpaUsers jpa;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<users> getAll() {
        return jpa.findAll();
    }

    @Override
    public int add(users users) {
            users.setMk(passwordEncoder.encode(users.getMk()));
        return jpa.save(users).getId();
    }

    @Override
    public int update(users users) {
        return jpa.save(users).getId();
    }

    @Override
    public void delete(Integer id) {
//        Optional option = jpa.findById(Integer.parseInt(id));
//        if (option.isPresent()) {
//            jpa.delete((users)option.get());
//            return 1;
//        }
//         return 0;
    }

    public users finbyName(String email, String name){
        return jpa.findByEmailAndMk(email, name);
    }

    public users finbyTK(String tk){
        return jpa.findByTk(tk);
    }

    public Object cd(users o, boolean check){
        return null;
    }
}
