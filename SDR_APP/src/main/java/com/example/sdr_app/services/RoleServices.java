package com.example.sdr_app.services;

import com.example.sdr_app.dao.Dao;
import com.example.sdr_app.entities.Role;
import com.example.sdr_app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices implements Dao<Role> {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role add(Role o) {
        return roleRepository.save(o);
    }

    @Override
    public Role update(int id, Role o) {
        return null;
    }

    @Override
    public Role delete(int id) {
        return null;
    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}