package com.du1.respon;

import com.du1.model.entity.SanPham;
import com.du1.model.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUsers extends JpaRepository<users, Integer> {

    public users findByEmailAndMk(String email, String name);
    public users findByTk(String name);
}
