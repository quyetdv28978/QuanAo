package com.du1.services;

import com.du1.model.entity.Chitiethoadon;
import com.du1.model.entity.SanPham;
import com.du1.respon.JpaUsers;
import com.du1.model.entity.Hoadon;
import com.du1.model.viewModel.donhangViewModel;
import com.du1.respon.jpaDonHang;
import com.du1.respon.jpaSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class donhangSer implements ServiceIF<donhangViewModel>{

    @Autowired
    private jpaDonHang jpaDonHang;
    @Autowired
    private jpaSanPham jpaSanPham;
    @Autowired
    private JpaUsers jpaUsers;

    @Override
    public List<donhangViewModel> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Integer add(donhangViewModel donhang) {
        donhang.getIdsanphams().forEach(i ->{
            Chitiethoadon cthd = Chitiethoadon.builder()
                    .ngaytao(new java.sql.Date(new Date().getTime()))
                    .sanpham(jpaSanPham.findById(i).get())
                    .tiengiam(Float.valueOf(0))
                    .tongtien(donhang.getTongTien().floatValue())
                    .soluong(donhang.getSoluong())
                    .hoadon((Hoadon) cd(donhang, true))
                .build();
            jpaDonHang.save(cthd);
        });

        return 0;
    }

    @Override
    @Transactional
    public int update(donhangViewModel donhang) {
        return 0;
    }

    @Override
    @Transactional
    public void delete(Integer id) {

    }

    @Override
    public Object cd(donhangViewModel o, boolean check) {
//Set<SanPham> sanphas = new HashSet<>();
//o.getIdsanphams().forEach(i -> {
//     jpaSanPham.findAll().forEach(j -> {
//         if (j.getId() == i){
//             sanphas.add(j);
//         }
//     });
//});
        return Hoadon.builder().ngaytt(new Date())
                .trangthai(0)
                .tongtien(o.getTongTien())
                .user(jpaUsers.findById(o.getIdUser()).get())
                .build();
    }
}
