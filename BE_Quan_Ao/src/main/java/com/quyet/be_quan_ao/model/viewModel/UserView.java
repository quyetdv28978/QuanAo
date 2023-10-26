package com.quyet.be_quan_ao.model.viewModel;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserView {
    private Integer id;
    private String acc, pass, vaitro, jwt;
}
