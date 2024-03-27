package com.ming.site.security;

import com.ming.site.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailAdapter
        extends User
        implements UserDetails {

    public UserDetailAdapter() {

    }

    public UserDetailAdapter(User user) {
        this.setUsername(user.getUsername());
        this.setAddresses(user.getAddresses());
        this.setCategories(user.getCategories());
        this.setId(user.getId());
        this.setMail(user.getMail());
        this.setCreateAt(user.getCreateAt());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPassword(user.getPassword());
        this.setRoles(user.getRoles());
        this.setUpgradeAt(user.getUpgradeAt());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return null;
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
