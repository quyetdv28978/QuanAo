package com.quyet.be_quan_ao.respon.Cart;

import com.quyet.be_quan_ao.model.Cart.Chitiethoadon;
import com.quyet.be_quan_ao.model.Cart.Giohangchitiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResChiTietGH extends JpaRepository<Giohangchitiet, Integer> {
}
