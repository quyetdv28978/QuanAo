package com.du1.services;

import com.du1.model.entity.SanPham;
import com.du1.respon.JpaUsers;
import com.du1.model.entity.donhang;
import com.du1.model.viewModel.donhangViewModel;
import com.du1.respon.jpaDonHang;
import com.du1.respon.jpaSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public int add(donhangViewModel donhang) {
        System.out.println((donhang)cd(donhang, true));
//        System.out.println((donhang)((donhang) cd(donhang, true)).getSanPhams());
        return jpaDonHang.save((donhang)cd(donhang, true)).getId();
    }

    @Override
    public int update(donhangViewModel donhang) {
        return 0;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Object cd(donhangViewModel o, boolean check) {
Set<SanPham> sanphas = new HashSet<>();
o.getIdsanphams().forEach(i -> {
     jpaSanPham.findAll().forEach(j -> {
         if (j.getId() == i){
             sanphas.add(j);
         }
     });
});
        return donhang.builder().ngaytt(new Date())
                .tinhtrang(0).soluong(o.getSoluong())
                .tongtien(o.getTongTien())
//                .user(jpaUsers.findById(o.getIdUser()).get())
                .sanphams(sanphas).build();
    }
}
