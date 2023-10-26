package com.quyet.be_quan_ao.service.SerLogin;

import com.quyet.be_quan_ao.model.login.User;
import com.quyet.be_quan_ao.model.viewModel.UserView;

import java.util.List;
import java.util.Optional;

public interface SerLoginIF {
    List<User> getAll();

    List<UserView> getAllUserView();

    Optional<User> add(UserView userView);

    Optional<User> update(UserView userView);

    boolean delete(Integer id);

    Optional<User> checkLogin(UserView userView);
}
