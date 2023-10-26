package com.quyet.be_quan_ao.model.viewModel;

import lombok.*;

import java.math.BigDecimal;

// idghct: id của giỏ hàng chi tiết
// idspct: id của sản phẩm chi tiet

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Cart {
    private Integer idgh, idghct, idspct, soLuong;
    private BigDecimal tongTien;
    private Double giaBan;
    private String tenSanPham;
}
