package com.du1.model.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "vaitro")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class vaitro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tenchucvu")
    private String tenchucvu;
    @Column(name = "trangthai")
    private int trangthai;
}
