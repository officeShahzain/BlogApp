package com.springboot.blogApp.security.config;

import com.springboot.blogApp.dao.UserRepository;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFound("User not found","email Id "+username,0));
        return user;
    }
}
