package com.quyet.be_quan_ao.model.Cart;

import com.quyet.be_quan_ao.model.Product.Chitietsp;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "giohangchitiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Giohangchitiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "soluong")
    private Integer soluong;

    @Column(name = "tongtien")
    private BigDecimal tongtien;

    @ManyToOne
    @JoinColumn(name = "idctsp")
    private Chitietsp idctsp;

    @ManyToOne
    @JoinColumn(name = "idgh")
    private Giohang idgh;
}
