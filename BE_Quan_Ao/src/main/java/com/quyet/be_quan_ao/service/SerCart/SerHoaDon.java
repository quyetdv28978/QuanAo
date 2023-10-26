package com.quyet.be_quan_ao.service.SerCart;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Cart.Chitiethoadon;
import com.quyet.be_quan_ao.model.Cart.Giohang;
import com.quyet.be_quan_ao.model.Cart.Giohangchitiet;
import com.quyet.be_quan_ao.model.Cart.Hoadon;
import com.quyet.be_quan_ao.model.viewModel.Cart;
import com.quyet.be_quan_ao.respon.Cart.ResChiTietGH;
import com.quyet.be_quan_ao.respon.Cart.ResChitietHD;
import com.quyet.be_quan_ao.respon.Cart.ResGiohang;
import com.quyet.be_quan_ao.respon.Cart.ResHoaDon;
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

@Service("hoadon")
public class SerHoaDon implements SerIF_Pay<Cart> {
    @Autowired
    private ResHoaDon resHoaDon;

    @Autowired
    private ResChitietHD resChitietHD;

    @Autowired
    private SerIF_Cart serGiohang;

    @Autowired
    @Qualifier("chitietsp")
    private SerIF serCTSP;

    @Override
    public List<Cart> getAll() {
        return transferCart(resChitietHD.findAll());
    }

    //    Them san pham vao gio hang dua vao id san pham nhan duoc tu view
    @Override
    @Transactional
    public Optional add(Integer idgh) {
        List<Giohangchitiet> sanphams = ((Giohang) serGiohang.findById(idgh).get()).getGiohangs();

        Hoadon gh = resHoaDon.save(Hoadon.builder()
                .trangthai(1)
                .build());

        sanphams.forEach(i -> {
            resChitietHD.save(Chitiethoadon.builder()
                    .ngaytao(LocalDate.now())
                    .soluong(i.getSoluong())
                    .tongtien(new BigDecimal(i.getSoluong() * i.getIdctsp().getGianban()))
                    .iddh(gh)
                    .idsp(i.getIdctsp())
                    .build());
        });

        deleteCart(idgh);
        return Const.MESS_ADD_SUSSCESS;
    }

    @Override
    public Optional update(Cart giohang) {
        return null;
    }

    @Override
    public Optional delete(Cart giohang) {
        return null;
    }

    @Override
    public Optional findById(Integer id) {
        return null;
    }

    //    Kiem tra so luong san pham > 0
    private Optional validation_giohang(Cart gio) {
        if (gio.getSoLuong() < 1) {
            return Const.MESS_QUALITY_Greater_than_0;
        }
        return Optional.empty();
    }

    //    Chuyen gio hang sang cart view -> giam du lieu gui di
    private List<Cart> transferCart(List<Chitiethoadon> carts) {
        return carts.stream().map(i -> Cart.builder()
                .idgh(i.getIddh().getId())
                .idghct(i.getId())
                .idspct(i.getIdsp().getId())
                .soLuong(i.getSoluong())
                .tongTien(i.getTongtien())
                .tenSanPham(i.getIdsp().getIdsp().getTensanpham())
                .build()
        ).collect(Collectors.toList());
    }

    //    Thanh toan thanh cong
//    Xoa gio hang tuong ung
    private void deleteCart(Integer idgh) {
        serGiohang.delete(idgh);
    }
}
