package com.du1.model.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "images")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Images{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String anh;
    private Date ngaytao;
    @ManyToMany(mappedBy = "images")
    @ToString.Exclude
    @FieldNameConstants.Exclude
    private Set<SanPham> sanphams;
}
