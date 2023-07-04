package com.du1.respon;

import com.du1.model.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaSanPham extends JpaRepository<SanPham, Integer> {
    public SanPham findByMa(String ma);
}
