package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.gucarsoft.sanalkutuphane.model.user.User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return new User(user.getUsername(), user.getPassword(), true,
                true, true, true, getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(com.gucarsoft.sanalkutuphane.model.user.User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }
}
