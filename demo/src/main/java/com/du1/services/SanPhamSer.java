package com.du1.services;

import com.du1.model.entity.Images;
import com.du1.model.entity.SanPham;
import com.du1.model.viewModel.LoaiSanPhamViewModel;
import com.du1.model.viewModel.productViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SanPhamSer implements ServiceIF<productViewModel> {
    @Autowired
    private com.du1.respon.jpaSanPham jpaSanPham;

    @Autowired
    private ImagesSer imagesSer;

    @Autowired
    private LoaiSanPhamSer loaiSanPhamSer;



    @Override
    public List<productViewModel> getAll() {
        List<SanPham> listSP = jpaSanPham.findAll();
        List<productViewModel> listProductView = new ArrayList<>();

        String path ="http://localhost:6969/demov1/imagePath/";


        listSP.forEach((item) -> {
            Set<String> imaga = new HashSet<>();
            item.getImages().forEach(i -> imaga.add(path + i.getAnh()));
            LoaiSanPhamViewModel l = null;
            if (item.getLoaisanpham() != null){
                l = LoaiSanPhamViewModel.builder().id(item.getLoaisanpham().getId())
                        .tenlsp(item.getLoaisanpham().getTenlsp()).trangthai(item.getLoaisanpham().getTrangthai()).build();
            }
                    listProductView.add(new productViewModel(item.getId(),
                            item.getTensanpham(), item.getMota(), item.getTieude(),
                            item.getMa(), item.getNhasanxuat(), item.getSoluong(), item.getTrangthai(),
                            item.getTrongluong(), item.getKichthuoc(), item.getGiaban(), item.getGiagoc()
                            , imaga, null, l));
                }
        );
        return listProductView;
    }

    @Override
    @Transactional
    public int add(productViewModel sanPham) {
       Integer id = jpaSanPham.saveAndFlush((SanPham)cd(sanPham, true)).getId();
        return 0;
    }

    @Override
    public int update(productViewModel sanPham) {
        SanPham s = (SanPham) cd(sanPham, false);
        s.setImages(jpaSanPham.findById(sanPham.getId()).get().getImages());
        s.setLoaisanpham(loaiSanPhamSer.findById(sanPham.getIdlsp()));
        for (String a: sanPham.getImages()
             ) {
            s.getImages().forEach(i ->
                    {
                        String id = a.substring(a.indexOf("+") + 1, a.length());
                        String tenANh = a.substring(0,a.indexOf("+"));
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
    public void delete(Integer id) {
        jpaSanPham.delete(jpaSanPham.findById(id).get());
        }

    @Override
    public Object cd(productViewModel o, boolean check) {
        System.out.println(loaiSanPhamSer.findById(o.getIdlsp()) + " sjfkshfkjshkfjsdlfs");
        Set<Images> imagesReal = new HashSet<>();
        if (check == true) {
            o.getImages().forEach(item -> imagesReal.add(Images.builder().anh(item).ngaytao(new Date()).build()));
        }
        return new SanPham(o.getId(),o.getTensanpham(), o.getMota(),o.getTieude(),
                 o.getMa(),o.getNhasanxuat(),o.getSoluong(), o.getTrangthai().equals("Hiển thị") ? 0 :1,
                o.getTronglong(),o.getKichthuoc(),o.getGiaban(),o.getGiagoc(),imagesReal
                , loaiSanPhamSer.findById(o.getIdlsp())
        );
    }

    public Optional<SanPham> findbyID(Integer id){
        System.out.println("Option: " + jpaSanPham.findById(Integer.valueOf(id)));
        return jpaSanPham.findById(id);
    }

    public List pageAble(int trang){
        Page<SanPham> page = jpaSanPham.findAll(PageRequest.of(trang, 4));
        return page.stream().toList();
    }

}