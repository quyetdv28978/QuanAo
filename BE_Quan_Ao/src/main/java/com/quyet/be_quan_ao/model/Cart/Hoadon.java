package com.quyet.be_quan_ao.model.Cart;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hoadon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate ngaytt;

    @Column(name = "trangthai")
    private Integer trangthai;

    @Column(name = "idkh")
    private Integer idkh;
}
