package com.quyet.be_quan_ao.model.login;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "vaitro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tenchucvu;
    private int trangthai;
}
