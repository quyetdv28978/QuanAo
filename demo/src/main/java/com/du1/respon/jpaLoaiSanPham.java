package com.du1.respon;

import com.du1.model.entity.loaisanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaLoaiSanPham extends JpaRepository<loaisanpham, Integer> {
}
