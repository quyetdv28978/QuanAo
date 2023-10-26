package com.quyet.be_quan_ao.respon.Cart;

import com.quyet.be_quan_ao.model.Cart.Chitiethoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResChitietHD extends JpaRepository<Chitiethoadon, Integer> {
}
