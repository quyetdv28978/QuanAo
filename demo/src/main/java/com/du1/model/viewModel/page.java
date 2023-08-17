package com.du1.model.viewModel;

import com.du1.model.entity.SanPham;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class page {
    private Integer trang, soLuong, count_page;
    private List list_sanpham;
}
