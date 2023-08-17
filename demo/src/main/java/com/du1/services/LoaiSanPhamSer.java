package com.du1.services;

import com.du1.model.entity.loaisanpham;
import com.du1.respon.jpaLoaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoaiSanPhamSer implements ServiceIF<loaisanpham>{

    @Autowired
    private jpaLoaiSanPham jpaLoaiSanPham;

    @Override
    public List<loaisanpham> getAll() {
        return jpaLoaiSanPham.findAll();
    }

    @Override
    @Transactional
    public Integer add(loaisanpham loaisanpham) {
        return jpaLoaiSanPham.save(loaisanpham).getId();
    }

    @Override
    @Transactional
    public int update(loaisanpham loaisanpham) {
        return 0;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    }

    @Override
    public Object cd(loaisanpham o, boolean check) {
        return null;
    }

    public loaisanpham findById(Integer id){
        return jpaLoaiSanPham.findById(id).get();
    }
}
