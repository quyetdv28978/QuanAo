package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.model.Product.Images;
import com.quyet.be_quan_ao.respon.sanpham.ResImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("images")
public class SerImages implements SerIF<Images> {

    @Autowired
    private ResImages resImages;

    @Override
    public List<Images> getAll() {
        return resImages.findAll();
    }

    @Override
    public Optional<Images> add(Images images) {
        return Optional.of(resImages.save(images));
    }

    @Override
    public Optional<Images> update(Images images) {
        return Optional.of(resImages.save(images));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resImages.existsById(id)) {
            resImages.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Images> finById(Integer id) {
        return resImages.findById(id);
    }
}
