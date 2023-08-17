package com.du1.model.viewModel;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class sanPhamTop {
    private String idsp, tensp;
    private BigDecimal slsp;
}
