package com.example.sdr_app.controllers;

import com.example.sdr_app.entities.Role;
import com.example.sdr_app.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    RoleServices roleService;

    @PostMapping()
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role role1 = roleService.add(role);
        return new ResponseEntity<>(role1, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roleList = roleService.findAll();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

}
