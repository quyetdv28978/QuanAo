package com.du1.model.viewModel;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ThongKe {
private Date dateS, dateE;
private Integer time;
private Double tongTien;
}
