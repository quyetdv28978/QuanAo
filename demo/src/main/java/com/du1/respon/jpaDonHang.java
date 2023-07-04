package com.du1.respon;

import com.du1.model.entity.donhang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaDonHang extends JpaRepository<donhang, Integer> {
}
