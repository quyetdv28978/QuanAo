package com.du1.respon;

import com.du1.model.entity.Chitiethoadon;
import com.du1.model.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface jpaChitiethoadon extends JpaRepository<Chitiethoadon, Integer> {

    @Query("select sum(c.hoadon.tongtien) from Chitiethoadon c where c.hoadon.ngaytt between :dateS and :dateE")
    public Object get(Date dateS, Date dateE);

}
