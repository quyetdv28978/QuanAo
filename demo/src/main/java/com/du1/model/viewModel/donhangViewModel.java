package com.du1.model.viewModel;

import com.du1.model.entity.SanPham;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class donhangViewModel {
    private Integer id, idUser;
    private Set<Integer> idsanphams;
    private Double tongTien;
    private Integer soluong;
}
