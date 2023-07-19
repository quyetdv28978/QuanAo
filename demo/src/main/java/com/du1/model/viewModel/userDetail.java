package com.du1.model.viewModel;
import com.du1.model.entity.users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;


@AllArgsConstructor
@Getter
@ToString
public class userDetail implements UserDetails{
//    @ToString.Exclude
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
        return users.getMk();
    }

    @Override
    public String getUsername() {

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
