package com.quyet.be_quan_ao.respon.sanpham;

import com.quyet.be_quan_ao.model.Product.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResLoaiSanPham extends JpaRepository<LoaiSanPham, Integer> {
}
