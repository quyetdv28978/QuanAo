package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.model.Product.MauSac;
import com.quyet.be_quan_ao.respon.sanpham.ResMauSac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mausac")
public class SerMauSac implements SerIF<MauSac> {
    @Autowired
    private ResMauSac resMauSac;

    @Override
    public List<MauSac> getAll() {
        return resMauSac.findAll();
    }

    @Override
    public Optional<MauSac> add(MauSac MauSac) {
        return Optional.of(resMauSac.save(MauSac));
    }

    @Override
    public Optional<MauSac> update(MauSac MauSac) {
        return Optional.of(resMauSac.save(MauSac));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resMauSac.existsById(id)) {
            resMauSac.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MauSac> finById(Integer id) {
        return resMauSac.findById(id);
    }
}

