package com.du1.services;

import com.du1.model.entity.DanhMuc;
import com.du1.model.entity.SanPham;
import com.du1.model.viewModel.danhmucViewModel;
import com.du1.respon.jpaDanhMuc;
import com.du1.respon.jpaSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DanhMucSer implements ServiceIF<danhmucViewModel> {

    @Autowired
    private jpaDanhMuc jpaDanhMuc;

    @Autowired
    private jpaSanPham jpaSanPham;


    @Override
    public List<danhmucViewModel> getAll() {
        List<danhmucViewModel> listDM = new ArrayList<>();
        jpaDanhMuc.findAll().forEach(item -> listDM.add(new danhmucViewModel
                (item.getId(), item.getTendm(), item.getMa(), item.getTrangthai(),
                        item.getListSanPham().size(), null)));
//        listDM.add(new danhmucViewModel());
        return listDM;
    }

    @Override
    @Transactional
    public Integer add(danhmucViewModel danhMuc) {
//        return jpaDanhMuc.save((DanhMuc) cd(danhMuc, true)).getId();
        jpaDanhMuc.save((DanhMuc) cd(danhMuc, true));
        return 0;
    }

    @Override
    @Transactional
    public int update(danhmucViewModel danhMuc) {
        System.out.println((DanhMuc) cd(danhMuc, false) + " danhmuc cua");
            return jpaDanhMuc.save((DanhMuc) cd(danhMuc, false)).getId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
         jpaDanhMuc.delete(jpaDanhMuc.findById(Integer.valueOf(id)).get());
    }

    @Override
    public Object cd(danhmucViewModel o, boolean check) {
        Set<SanPham> listSP = new HashSet<>();

        if (check == true) {
            o.getIdSP().forEach(i -> listSP.add(jpaSanPham.findByMa(i)));
            return DanhMuc.builder().ma(o.getMa())
                    .ngaytao(new Date(new java.util.Date().getTime())).tendm(o.getTendm())
                    .trangthai(o.getTrangthai()).listSanPham(listSP).build();
        }
if (o.getTendm() == null) {
    DanhMuc d = jpaDanhMuc.findById(o.getId()).get();
    o.getIdSP().forEach(i -> d.getListSanPham().remove(jpaSanPham.findByMa(i)));
    return d;
}
        DanhMuc d = jpaDanhMuc.findById(o.getId()).get();
        o.getIdSP().forEach(i -> d.getListSanPham().add(jpaSanPham.findByMa(i)));
        return DanhMuc.builder().id(d.getId()).ma(d.getMa()).ngaytao(d.getNgaytao())
                .trangthai(o.getTrangthai()).listSanPham(d.getListSanPham())
                .tendm(o.getTendm()).build()
                ;
    }

    public DanhMuc finbyID (Integer id){
        DanhMuc dm = jpaDanhMuc.findById(id).get();
        Set<SanPham> idSP = new HashSet<>();
                dm.getListSanPham().forEach(i -> idSP.add(
                        SanPham.builder().id(i.getId())
                                .ma(i.getMa())
                                .tensanpham(i.getTensanpham())
                                .giaban(i.getGiaban())
                                .nhasanxuat(i.getNhasanxuat())
                                .build()
                ));

        return dm.builder().id(dm.getId()).ma(dm.getMa()).tendm(dm.getTendm()).trangthai(dm.getTrangthai())
                .listSanPham(idSP).build();
    }
}
