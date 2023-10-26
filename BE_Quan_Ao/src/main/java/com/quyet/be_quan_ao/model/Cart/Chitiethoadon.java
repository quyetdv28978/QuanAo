package com.quyet.be_quan_ao.model.Cart;

import com.quyet.be_quan_ao.model.Product.Chitietsp;
import com.quyet.be_quan_ao.model.Product.Product;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "chitiethoadon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Chitiethoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "iddh")
    private Hoadon iddh;

    @ManyToOne
    @JoinColumn(name = "idsp")
    private Chitietsp idsp;

    private BigDecimal tongtien;

    private Integer soluong;

    private Float tiengiam;

    private LocalDate ngaytao;
}
