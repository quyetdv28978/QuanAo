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
public class productViewModel {
    private int id;
    private String tensanpham, mota, tieude, ma,nhasanxuat;
    private Integer soluong,trangthai;
    private Float tronglong,kichthuoc;
    private Float giaban, giagoc;
    private Set<String> images;
    private Integer idlsp;
    private LoaiSanPhamViewModel loaisanphamviewmodel;
}
