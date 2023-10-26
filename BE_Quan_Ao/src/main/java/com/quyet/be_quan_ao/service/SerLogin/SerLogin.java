package com.quyet.be_quan_ao.service.SerLogin;

import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.model.viewModel.UserView;
import com.quyet.be_quan_ao.respon.login.ResUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerLogin implements SerLoginIF {
    @Autowired
    private ResUser login;

    @Override
    public List<User> getAll() {
        return login.findAll();
    }

    @Override
    public List<UserView> getAllUserView() {
        return login.findAll().stream()
                .map(i -> UserView.builder()
                        .id(i.getId())
                        .acc(i.getAcc())
                        .pass(i.getPass())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<User> add(UserView userView) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(UserView userView) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Optional<User> checkLogin(UserView userView) {
        return login.findByAccAndPass(userView.getAcc(), userView.getPass());
    }
}
