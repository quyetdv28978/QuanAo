package com.du1.model.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "SANPHAM")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanPham{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tensanpham, mota, tieude, ma,nhasanxuat;
    private Integer soluong, trangthai;
    private Float trongluong,kichthuoc;
    private Float giaban, giagoc;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch =FetchType.LAZY)
    @JoinTable(name = "chitietanh",
            joinColumns = @JoinColumn(name = "idsp"),
            inverseJoinColumns = @JoinColumn(name = "idimage")
    )
    @ToString.Exclude
    private Set<Images> images;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "idlsp")
    @ToString.Exclude
    private loaisanpham loaisanpham;

    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.REMOVE, fetch =FetchType.LAZY)
    @ToString.Exclude
    private Set<Chitiethoadon> list_chitiethoadon;
}
