package com.quyet.be_quan_ao.model.Cart;

import com.quyet.be_quan_ao.model.login.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "giohang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Giohang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "trangthai")
    private Integer trangthai;

    @Column(name = "ngaytao")
    private LocalDate ngaytao;

    @ManyToOne
    @JoinColumn(name = "idkh")
    private User idkh;

    @OneToMany(mappedBy = "idgh", cascade = CascadeType.ALL)
    private List<Giohangchitiet> giohangs;
}
