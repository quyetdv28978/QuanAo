package com.du1.model.viewModel;
import com.du1.model.entity.users;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;


@AllArgsConstructor
public class userDetail implements UserDetails{
    private users users;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (users.getVaitro().getId()==1) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        System.out.println(users.getMk() + " day la mk cua getPasswoek");
        return users.getMk();
    }

    @Override
    public String getUsername() {
        System.out.println(users.getTk() + " day la mk cua getTK");

        return users.getTk();
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
