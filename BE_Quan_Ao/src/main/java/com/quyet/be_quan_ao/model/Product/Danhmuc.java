package com.quyet.be_quan_ao.model.Product;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "danhmuc")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Danhmuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ma;

    private String tendm;

    private LocalDate ngaytao;
}
