package com.example.Agenda_C.service;

import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean active;
    private GrantedAuthority authority;
    private UserRepository userRepository;


    public MyUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.getEnabled();
        this.authority = new SimpleGrantedAuthority("ROLE_"+user.getRole().getLabel());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }

    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) userRepository.findByUsername(username).get();
    }
}
