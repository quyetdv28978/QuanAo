package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.*;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.model.viewModel.ViewProduct;
import com.quyet.be_quan_ao.respon.sanpham.ResChitietSP;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("chitietsp")
public class SerChiTietSP implements SerIF<ViewProduct> {
    @Autowired
    private ResChitietSP resChitietsp;

    @Autowired
    @Qualifier("product")
    private SerIF serSanPham;

    @Autowired
    @Qualifier("images")
    private SerIF serImages;

    @Autowired
    @Qualifier("danhmuc")
    private SerIF serDanhMuc;

    @Autowired
    @Qualifier("mausac")
    private SerIF serMauSac;

    @Autowired
    @Qualifier("kichthuoc")
    private SerIF serKichThuoc;

    @Autowired
    @Qualifier("loaisanpham")
    private SerIF serLSP;

    @Override
    public List<ViewProduct> getAll() {
//        return resChitietsp.findAll();
        return null;
    }

    @Override
    @Transactional
    public Optional add(ViewProduct chitietsp) {
        Optional op = validation(chitietsp);
        if (op.isEmpty()) {
            resChitietsp.save(addRelation(chitietsp));
            return Const.MESS_ADD_SUSSCESS;
        }
        return op;
    }

    @Override
    public Optional update(ViewProduct Chitietsp) {
        return Optional.of(resChitietsp.save(addRelation(Chitietsp)));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resChitietsp.existsById(id)) {
            resChitietsp.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional finById(Integer id) {
        return resChitietsp.findById(id);
    }

    //    Chuyen viewproduct -> chi tiet sp
    //    Them cac phu thuoc cua san pham : dongsp, mausac, iamges...
    private Chitietsp addRelation(ViewProduct viewProduct) {
        Product product = (Product) serSanPham.add(Product.builder()
                .mota(viewProduct.getMoTa())
                .trangthai(viewProduct.getTrangThai())
                .tensanpham(viewProduct.getTenSanPham())
                .build()
        ).get();

        Images iamges = (Images) serImages.add(Images.builder()
                .ma(RandomStringUtils.randomAlphabetic(5))
                .anh(viewProduct.getImages())
                .build()
        ).get();

        LoaiSanPham loaiSanPham = (LoaiSanPham) serLSP.finById(viewProduct.getIdDongSanPham()).get();

        Danhmuc danhmuc = (Danhmuc) serDanhMuc.finById(viewProduct.getIdDanhMuc()).get();

        MauSac mausac = (MauSac) serMauSac.finById(viewProduct.getIdMauSac()).get();

        Kichthuoc kichthuoc = (Kichthuoc) serKichThuoc.finById(viewProduct.getIdMauSac()).get();

        return Chitietsp.builder()
                .idsp(product)
                .iddsp(loaiSanPham)
                .idms(mausac)
                .idct(kichthuoc)
                .iddm(danhmuc)
                .idanh(iamges)
                .gianban(viewProduct.getGiaBan())
                .giannhap(viewProduct.getGiaNhap())
                .soluong(viewProduct.getSoLuong())
                .build();
    }

    private Optional validation(ViewProduct t) {
        if (Objects.isNull(t.getTieuDe()) || Objects.isNull(t.getMoTa())
                || Objects.isNull(t.getTenSanPham()))
            return Const.MESS_NOT_NULL;

        if (t.getTenSanPham().isBlank()
                || t.getMoTa().isBlank()
                || t.getTieuDe().isBlank()
        )
            return Const.MESS_NOT_NULL;

        if (t.getGiaBan() < 0 || t.getGiaNhap() < 0) {
            return Const.MESS_QUALITY_Greater_than_0;
        }
        return Optional.empty();
    }
}

