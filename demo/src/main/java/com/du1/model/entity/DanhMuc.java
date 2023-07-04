package com.du1.model.entity;

import lombok.*;


import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "danhmuc")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ma, tendm;
    private Date ngaytao;
    private Integer trangthai;

    @ManyToMany
    @JoinTable(name = "chitietdanhmuc",
            joinColumns = @JoinColumn(name = "iddm"),
            inverseJoinColumns = @JoinColumn(name = "idsp")
    )

    private Set<SanPham> listSanPham;

}
