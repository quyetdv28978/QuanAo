package com.quyet.be_quan_ao.service.SerCart;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Cart.Giohang;
import com.quyet.be_quan_ao.model.Cart.Giohangchitiet;
import com.quyet.be_quan_ao.model.Product.Chitietsp;
import com.quyet.be_quan_ao.model.viewModel.Cart;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.respon.Cart.ResChiTietGH;
import com.quyet.be_quan_ao.respon.Cart.ResGiohang;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("giohang")
public class SerGioHang implements SerIF_Cart<Cart> {
    @Autowired
    private ResGiohang resGiohang;

    @Autowired
    private ResChiTietGH resChiTietGH;

    @Autowired
    @Qualifier("chitietsp")
    private SerIF serCTSP;

    @Override
    public List<Cart> getAll() {
        return transferCart(resChiTietGH.findAll());
    }

    //    Them san pham vao gio hang dua vao id san pham nhan duoc tu view
    @Override
    @Transactional
    public Optional add(Cart giohang) {
        Optional validation = validation_giohang(giohang);
        if (!validation.isEmpty())
            return validation;

        Chitietsp ctsp = (Chitietsp) serCTSP.finById(giohang.getIdspct()).get();
        Giohang gh = resGiohang.save(Giohang.builder()
                .ngaytao(LocalDate.now())
                .trangthai(0)
                .build());

        Giohangchitiet ghct = Giohangchitiet.builder()
                .soluong(giohang.getSoLuong())
                .tongtien(new BigDecimal(giohang.getSoLuong() * giohang.getGiaBan()))
                .idgh(gh)
                .idctsp(ctsp)
                .build();

        resChiTietGH.save(ghct);

        return Const.MESS_ADD_SUSSCESS;
    }

    @Override
    public Optional update(Cart giohang) {
        return null;
    }

    @Override
    public Optional delete(Integer giohang) {
        resGiohang.deleteById(giohang);
        return null;
    }

    @Override
    public Optional findById(Integer id) {
        return resGiohang.findById(id);
    }

    //    Kiem tra so luong san pham > 0
    private Optional validation_giohang(Cart gio) {
        if (gio.getSoLuong() < 1) {
            return Const.MESS_QUALITY_Greater_than_0;
        }
        return Optional.empty();
    }

    //    Chuyen gio hang sang cart view -> giam du lieu gui di
    private List<Cart> transferCart(List<Giohangchitiet> carts) {
        return carts.stream().map(i -> Cart.builder()
                .idgh(i.getIdgh().getId())
                .idghct(i.getId())
                .idspct(i.getIdctsp().getId())
                .soLuong(i.getSoluong())
                .tongTien(i.getTongtien())
                .tenSanPham(i.getIdctsp().getIdsp().getTensanpham())
                .build()
        ).collect(Collectors.toList());
    }
}
