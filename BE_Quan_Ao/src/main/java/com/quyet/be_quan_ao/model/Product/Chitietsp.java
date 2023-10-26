package com.quyet.be_quan_ao.model.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.awt.*;


@Entity
@Table(name = "chitietsp")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Chitietsp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idanh")
    @JsonIgnore
    private Images idanh;

    @ManyToOne
    @JoinColumn(name = "idsp")
    @JsonIgnore
    private Product idsp;

    @ManyToOne
    @JoinColumn(name = "idms")
    @JsonIgnore
    private MauSac idms;

    @ManyToOne
    @JoinColumn(name = "iddsp")
    @JsonIgnore
    private LoaiSanPham iddsp;

    @ManyToOne
    @JoinColumn(name = "iddm")
    @JsonIgnore
    private Danhmuc iddm;

//    @ManyToOne
//    @JoinColumn(name = "idcl")
//    @JsonIgnore
//    private Cha idcl;

    @ManyToOne
    @JoinColumn(name = "idct")
    @JsonIgnore
    private Kichthuoc idct;

    private Integer soluong;

    private Double gianban;

    private Double giannhap;

}
