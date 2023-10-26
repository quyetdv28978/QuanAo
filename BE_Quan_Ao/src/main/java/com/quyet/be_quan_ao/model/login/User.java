package com.quyet.be_quan_ao.model.login;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String acc, pass, email, anh, diachi;
    private Integer trangthai;
    private LocalDate ngaytao, ngaysinh;

    @OneToOne
    @JoinColumn(name = "vaitro")
    private VaiTro vaitro;

}
