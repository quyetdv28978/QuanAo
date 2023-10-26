package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.Danhmuc;
import com.quyet.be_quan_ao.model.Product.Kichthuoc;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.respon.sanpham.ResDanhMuc;
import com.quyet.be_quan_ao.respon.sanpham.ResKichThuoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("kichthuoc")
public class SerKichThuoc implements SerIF<Kichthuoc> {
    @Autowired
    private ResKichThuoc resDanhmuc;

    @Override
    public List<Kichthuoc> getAll() {
        return resDanhmuc.findAll();
    }

    @Override
    public Optional<Kichthuoc> add(Kichthuoc Danhmuc) {
        Danhmuc.setKichthuoc(Danhmuc.getKichthuoc().trim());
        if (Danhmuc.getKichthuoc().trim().length() == 0)
            return Optional.empty();
        return Optional.of(resDanhmuc.save(Danhmuc));
    }

    @Override
    public Optional update(Kichthuoc Danhmuc) {
        if (!resDanhmuc.existsById(Danhmuc.getId()))
            return Const.MESS_ID_INVALID;

        return Optional.of(resDanhmuc.save(Danhmuc));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resDanhmuc.existsById(id)) {
            resDanhmuc.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Kichthuoc> finById(Integer id) {
        return resDanhmuc.findById(id);
    }
}

