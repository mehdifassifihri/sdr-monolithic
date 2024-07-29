package com.example.sdr_app.services;

import com.example.sdr_app.dao.Dao;
import com.example.sdr_app.entities.User;
import com.example.sdr_app.exception.CustomNotFoundException;
import com.example.sdr_app.jwt.JwtUtils;
import com.example.sdr_app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService implements Dao<User> {
    private final UserRepository userRepository;

    JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User add(User o) {
        return userRepository.save(o);
    }

    @Override
    public User update(int id, User o) {
        return userRepository.findById(id).map((user) -> {
            user.setNom(o.getNom());
            user.setPrenom(o.getPrenom());
            user.setEmail(o.getEmail());
            user.setUsername(o.getUsername());
            user.setPassword(o.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new CustomNotFoundException("User not found with ID : " + id));
    }

    @Override
    public User delete(int id) {
        User temp = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
        return temp;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new CustomNotFoundException("No users found");
        }
        return userList;
    }


    public String loadUsername(String username) {
        User userFound = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("User not found with username: " + username));
        return userFound.getUsername();
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("User not found with username: " + username));
    }






}