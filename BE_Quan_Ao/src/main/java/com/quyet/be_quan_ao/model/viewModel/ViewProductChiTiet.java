package com.quyet.be_quan_ao.model.viewModel;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ViewProductChiTiet {
    private Integer idctsp, soLuong;
    private String tenSP, mauSac;
    private Double giaBan;
}
