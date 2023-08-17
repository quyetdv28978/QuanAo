package com.du1.model.viewModel;

import com.du1.model.entity.Images;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class productViewModel {
    private Integer id;
    private String tensanpham, mota, tieude, ma,nhasanxuat;
    private Integer soluong,trangthai;
    private Float tronglong,kichthuoc;
    private Float giaban, giagoc;
    private List<String> images;
    private Integer idlsp;
    private LoaiSanPhamViewModel loaisanphamviewmodel;

    public productViewModel (String tensanpham, String mota, String tieude, String ma, Float giaBan, List image){
        this.tensanpham = tensanpham;
        this.mota = mota;
        this.tieude = tieude;
        this.ma = ma;
        this.giaban = giaBan;
        this.images = image;

    }
}
