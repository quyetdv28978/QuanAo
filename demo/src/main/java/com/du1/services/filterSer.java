package com.du1.services;

import com.du1.model.entity.SanPham;
import com.du1.model.viewModel.productViewModel;
import com.du1.respon.jpaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class filterSer {
    @Autowired
    private jpaFilter jpaFilter;

    public List finallByLsp(Integer id, Integer trang) {
        List list_sanpham = new ArrayList();
        List list_sanphamShow = new ArrayList();
        Page<SanPham> page = jpaFilter.findAllByLoaisanphamId(id,PageRequest.of(trang, 8));
        page.stream().forEach(i -> {
            List list_Images = new ArrayList();
            i.getImages().forEach(j -> list_Images.add("http://localhost:6969/imagePath/" + j.getAnh()));
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
}
