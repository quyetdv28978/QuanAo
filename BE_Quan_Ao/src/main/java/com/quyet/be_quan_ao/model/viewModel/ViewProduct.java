package com.quyet.be_quan_ao.model.viewModel;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ViewProduct {
    private Integer idSP, idCtsp, trangThai, soLuong;
    private String tenSanPham, moTa, tieuDe, images;
    private Double giaBan, giaNhap;
    private Integer idKichThuoc, idMauSac, idDongSanPham, idDanhMuc;
}
