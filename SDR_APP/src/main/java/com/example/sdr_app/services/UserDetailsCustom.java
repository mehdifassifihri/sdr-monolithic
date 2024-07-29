package com.example.sdr_app.services;

import com.example.sdr_app.entities.User;
import com.example.sdr_app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustom implements UserDetailsService {
    @Autowired
    private UserRepository userrepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("username not found"));
    }

}
