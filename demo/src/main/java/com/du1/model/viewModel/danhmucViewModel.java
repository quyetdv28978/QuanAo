package com.du1.model.viewModel;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class danhmucViewModel {
    private Integer id;
    private String tendm, ma;
    private Integer trangthai, slsp;
    private Set<String> idSP;

}
