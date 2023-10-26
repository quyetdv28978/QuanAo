package com.quyet.be_quan_ao.respon.sanpham;

import com.quyet.be_quan_ao.model.Product.Chitietsp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResChitietSP extends JpaRepository<Chitietsp, Integer> {
}
