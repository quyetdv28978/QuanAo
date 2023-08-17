package com.du1.services;

import com.du1.model.entity.SanPham;

import java.util.List;
import java.util.Optional;

public interface SanPhamSerIF<Q> extends ServiceIF<Q>{
    public List<Q> getAll(List<SanPham> list);
    public Optional<SanPham> findbyID(Integer id);

    public List pageAble(int trang);

    public List getSanPhamByLoaiSanPham(Integer id);
}
