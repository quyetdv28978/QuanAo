package com.quyet.be_quan_ao.respon.Cart;

import com.quyet.be_quan_ao.model.Cart.Giohang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResGiohang extends JpaRepository<Giohang, Integer> {
}
