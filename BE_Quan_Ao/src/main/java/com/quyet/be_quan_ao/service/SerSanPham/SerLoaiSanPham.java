package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.model.Product.LoaiSanPham;
import com.quyet.be_quan_ao.model.Product.LoaiSanPham;
import com.quyet.be_quan_ao.respon.sanpham.ResLoaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("loaisanpham")
public class SerLoaiSanPham implements SerIF<LoaiSanPham> {

    @Autowired
    private ResLoaiSanPham resLoaiSanPham;

    @Override
    public List<LoaiSanPham> getAll() {
        return resLoaiSanPham.findAll();
    }

    @Override
    public Optional<LoaiSanPham> add(LoaiSanPham LoaiSanPham) {
        return Optional.of(resLoaiSanPham.save(LoaiSanPham));
    }

    @Override
    public Optional<LoaiSanPham> update(LoaiSanPham LoaiSanPham) {
        return Optional.of(resLoaiSanPham.save(LoaiSanPham));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resLoaiSanPham.findById(id).isPresent()) {
            resLoaiSanPham.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<LoaiSanPham> finById(Integer id) {
        return resLoaiSanPham.findById(id);
    }
}
