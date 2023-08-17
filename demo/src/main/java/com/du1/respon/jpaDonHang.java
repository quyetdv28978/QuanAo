package com.du1.respon;

import com.du1.model.entity.Chitiethoadon;
import com.du1.model.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaDonHang extends JpaRepository<Chitiethoadon, Integer> {
}
