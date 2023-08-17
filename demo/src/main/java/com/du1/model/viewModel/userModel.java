package com.du1.model.viewModel;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class userModel {
    private String tk, mk, jwt;
    private Boolean check;
    private Collection<? extends GrantedAuthority> role;
}
