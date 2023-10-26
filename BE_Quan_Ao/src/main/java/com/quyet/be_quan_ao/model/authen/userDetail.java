package com.quyet.be_quan_ao.model.authen;

import com.quyet.be_quan_ao.model.login.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

// Chứa thông tin người dùng
@AllArgsConstructor
@Getter
@ToString
@Builder
public class userDetail implements UserDetails {
    private User users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (users.getVaitro().getId() == 1) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        } else return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return users.getPass();
    }

    @Override
    public String getUsername() {
        return users.getAcc();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
