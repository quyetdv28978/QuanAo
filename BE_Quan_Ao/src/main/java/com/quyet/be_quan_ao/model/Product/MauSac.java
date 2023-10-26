package com.quyet.be_quan_ao.model.Product;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "mausac")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mausac;
}
