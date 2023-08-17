package com.du1.respon;

import com.du1.model.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaHoadon extends JpaRepository<Hoadon, Integer> {
}
