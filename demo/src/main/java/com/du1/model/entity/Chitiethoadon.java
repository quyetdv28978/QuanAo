package com.du1.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Chitiethoadon")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Chitiethoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float tongtien, tiengiam;
    private Integer soluong;
    private Date ngaytao;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch =FetchType.LAZY)
    @JoinColumn(name = "idDH")
    private Hoadon hoadon;

    @ManyToOne( fetch =FetchType.LAZY)
    @JoinColumn(name = "idSP")
    private SanPham sanpham;
}
