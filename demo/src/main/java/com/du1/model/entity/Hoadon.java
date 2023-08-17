package com.du1.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Hoadon")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date ngaytt;
    private Integer trangthai;
    private Double tongtien;
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "idkh")
    @ToString.Exclude
    private users user;
}
