package com.quyet.be_quan_ao.model.Product;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ma;

    private String anh;

    private LocalDate ngaytao;
}
