package com.du1.respon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaDanhMuc extends JpaRepository<com.du1.model.entity.DanhMuc, Integer> {
}
