package com.du1.model.entity;


//import javax.persistence.*;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tk, mk, email, anh, diachi;
    private Date ngaytao, ngaysinh;
    private Integer tuoi, trangthai;
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "vaitro")
    private vaitro vaitro;
}
