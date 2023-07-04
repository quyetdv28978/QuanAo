package com.du1.model.entity;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "donhang")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class donhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date ngaytt;
    private Integer tinhtrang, soluong;
    private Double tongtien;
    @OneToOne
    @JoinColumn(name = "idkh")
    @ToString.Exclude
    private users user;

    @ManyToMany
    @JoinTable(name = "chitiethoadon",
    joinColumns = @JoinColumn(name = "idDH"),
            inverseJoinColumns = @JoinColumn(name = "idSP")
    )
    @ToString.Exclude
    private Set<SanPham> sanphams;
}
