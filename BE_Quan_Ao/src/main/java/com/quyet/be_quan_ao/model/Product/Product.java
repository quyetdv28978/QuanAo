package com.quyet.be_quan_ao.model.Product;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "sanpham")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tensanpham, mota;
    private Integer trangthai;

    @OneToMany(mappedBy = "idsp", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Chitietsp> sanphams;
}
