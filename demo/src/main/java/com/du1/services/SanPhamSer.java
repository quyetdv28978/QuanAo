package com.du1.services;

import com.du1.model.entity.Images;
import com.du1.model.entity.SanPham;
import com.du1.model.viewModel.LoaiSanPhamViewModel;
import com.du1.model.viewModel.page;
import com.du1.model.viewModel.productViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SanPhamSer implements SanPhamSerIF<productViewModel> {
    @Autowired
    private com.du1.respon.jpaSanPham jpaSanPham;

    @Autowired
    private ImagesSer imagesSer;

    @Autowired
    private LoaiSanPhamSer loaiSanPhamSer;


    @Override
    public List<productViewModel> getAll(List<SanPham> list) {
       return epList(list);
    }

    public List<SanPham> getAll2(Integer sl, Integer id) {
        return jpaSanPham.getAllSanPhamBySL(sl, id);
    }

    public List<SanPham> getAllBySL(Integer sl) {
        return epList(jpaSanPham.getAllSanPhamBySL(sl));
    }

    @Override
    public List<productViewModel> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Integer add(productViewModel sanPham) {
        if (sanPham.getImages().size() != 3) {
            return 1;
        }
        SanPham sanPham1 = (SanPham) cd(sanPham, true);
        sanPham1.setMa(new Random().nextInt(10000) + "");
        Integer id = jpaSanPham.save(sanPham1).getId();
        return 0;
    }

    @Override
    @Transactional
    public int update(productViewModel sanPham) {
        SanPham s = (SanPham) cd(sanPham, false);
        s.setId(sanPham.getId());
        s.setImages(jpaSanPham.findById(sanPham.getId()).get().getImages());
        s.setLoaisanpham(loaiSanPhamSer.findById(sanPham.getIdlsp()));
        for (String a : sanPham.getImages()
        ) {
            s.getImages().forEach(i ->
                    {
                        String id = a.substring(a.indexOf("+") + 1, a.length());
                        String tenANh = a.substring(0, a.indexOf("+"));
                        if (i.getId() == Integer.parseInt(id)) {
                            i.setAnh(tenANh);
                        }
                    }
            );
        }
        jpaSanPham.save(s);

        return 0;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (jpaSanPham.findById(id).isPresent()) {
            jpaSanPham.delete(jpaSanPham.findById(id).get());
        }
    }

    @Override
    public Object cd(productViewModel o, boolean check) {
        Set<Images> imagesReal = new HashSet<>();
        if (check == true) {
            o.getImages().forEach(item -> imagesReal.add(Images.builder().anh(item).ngaytao(new Date()).build()));
        }
        return SanPham.builder().tensanpham(o.getTensanpham()).mota(o.getMota()).tieude(o.getTieude())
                .ma(o.getMa()).nhasanxuat(o.getNhasanxuat()).soluong(o.getSoluong()).trangthai(o.getTrangthai().equals("Hiển thị") ? 0 : 1)
                .trongluong(o.getTronglong()).kichthuoc(o.getKichthuoc()).giaban(o.getGiaban()).giagoc(o.getGiagoc()).images(imagesReal)
                .loaisanpham(loaiSanPhamSer.findById(o.getIdlsp()))
                .build();
    }

    public Optional<SanPham> findbyID(Integer id) {
        return jpaSanPham.findById(id);
    }

    public List pageAble(int trang) {
        List list_sanpham = new ArrayList();
        List list_sanphamShow = new ArrayList();
        Page<SanPham> page = jpaSanPham.findAll(PageRequest.of(trang, 8));
        page.stream().forEach(i -> {
            List list_Images = new ArrayList();
            i.getImages().forEach(j -> list_Images.add("http://localhost:6969/demov1/imagePath/" + j.getAnh()));
            list_sanpham.add(new productViewModel(i.getTensanpham(), i.getMota(), i.getTieude(),
                    i.getMa(), i.getGiaban(), list_Images));
        });
        list_sanphamShow.add(com.du1.model.viewModel.page.builder().count_page(page.getTotalPages())
                .soLuong(Integer.valueOf(Long.toString(page.getNumberOfElements())))
                .trang(trang)
                .list_sanpham(list_sanpham)
                .count_page(page.getTotalPages())
                .build());

        return list_sanphamShow;
    }

    @Override
    public List getSanPhamByLoaiSanPham(Integer id) {
        List<SanPham> list_sanpham = jpaSanPham.findAll().stream()
                .filter(i -> ((SanPham) i).getLoaisanpham().getId().equals(id))
                .collect(Collectors.toList());
        List list = new ArrayList();

        list_sanpham.forEach(i ->
                list.add(productViewModel.builder()
                        .id(i.getId())
                        .ma(i.getMa())
                        .giaban(i.getGiaban())
                        .soluong(i.getSoluong())
                        .tensanpham(i.getTensanpham())
                        .loaisanphamviewmodel(LoaiSanPhamViewModel.builder().
                                id(i.getLoaisanpham().getId()).
                                tenlsp(i.getLoaisanpham().getTenlsp()).build())
                        .nhasanxuat(i.getNhasanxuat())
                        .build()
                )
        );
        return list;
    }

    private List epList(List list) {
        List<SanPham> listSP = list;
        List<productViewModel> listProductView = new ArrayList<>();
        String path = "http://localhost:6969/demov1/imagePath/";
        listSP.forEach((item) -> {
                    List<String> imaga = new ArrayList<>();
                    item.getImages().stream().sorted(Comparator.comparing(Images::getId)).forEach(i -> imaga.add(path + i.getAnh()));
                    LoaiSanPhamViewModel l = null;
                    if (item.getLoaisanpham() != null) {
                        l = LoaiSanPhamViewModel.builder().id(item.getLoaisanpham().getId())
                                .tenlsp(item.getLoaisanpham().getTenlsp()).trangthai(item.getLoaisanpham().getTrangthai()).build();
                    }
                    listProductView.add(new productViewModel(item.getId(),
                            item.getTensanpham(), item.getMota(), item.getTieude(),
                            item.getMa(), item.getNhasanxuat(), item.getSoluong(), item.getTrangthai(),
                            item.getTrongluong(), item.getKichthuoc(), item.getGiaban(), item.getGiagoc()
                            , imaga
                            , null, l));
                }
        );
        return listProductView;
    }
}