package com.du1.respon;

import com.du1.model.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface jpaFilter extends JpaRepository<SanPham, Integer> {

    @Query("from SanPham s where s.loaisanpham.id = ?1")
    public Page findAllByLoaisanphamId(Integer id, PageRequest page);
}
