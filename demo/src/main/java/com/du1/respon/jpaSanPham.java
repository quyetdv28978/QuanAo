package com.du1.respon;

import com.du1.model.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface jpaSanPham extends JpaRepository<SanPham, Integer> {
    public SanPham findByMa(String ma);

    @Query(value = "select *\n" +
            "from sanpham s join loaisanpham l on s.idlsp = l.id where l.id = :id limit :sl", nativeQuery = true)
    public List<SanPham> getAllSanPhamBySL(Integer sl, Integer id);

    @Query(value = "select * from SanPham limit :sl", nativeQuery = true)
    public List<SanPham> getAllSanPhamBySL(Integer sl);

}
