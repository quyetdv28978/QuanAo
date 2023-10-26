package com.quyet.be_quan_ao.respon.login;

import com.quyet.be_quan_ao.model.login.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResRole extends JpaRepository<VaiTro, Integer> {
}
