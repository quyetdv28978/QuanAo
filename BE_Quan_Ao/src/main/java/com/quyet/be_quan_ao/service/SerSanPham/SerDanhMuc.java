package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.model.Product.Danhmuc;
import com.quyet.be_quan_ao.respon.sanpham.ResDanhMuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "danhmuc")
public class SerDanhMuc implements SerIF<Danhmuc> {

    @Autowired
    private ResDanhMuc resDanhMuc;

    @Override
    public List<Danhmuc> getAll() {
        return resDanhMuc.findAll();
    }

    @Override
    public Optional<Danhmuc> add(Danhmuc danhmuc) {
        return Optional.of(resDanhMuc.save(danhmuc));
    }

    @Override
    public Optional<Danhmuc> update(Danhmuc danhmuc) {
        return Optional.of(resDanhMuc.save(danhmuc));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resDanhMuc.findById(id).isPresent()) {
            resDanhMuc.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Danhmuc> finById(Integer id) {
        return resDanhMuc.findById(id);
    }
}
