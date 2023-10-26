package com.quyet.be_quan_ao.respon.login;

import com.quyet.be_quan_ao.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResUser extends JpaRepository<User, Integer> {
    Optional<User> findByAccAndPass(String acc, String pass);

    User findByAcc(String name);
}
