package com.example.sdr_app.controllers;

import com.example.sdr_app.dto.LoginRequest;
import com.example.sdr_app.dto.Response;
import com.example.sdr_app.entities.Role;
import com.example.sdr_app.entities.User;
import com.example.sdr_app.exception.CustomNotFoundException;
import com.example.sdr_app.jwt.JwtUtils;
import com.example.sdr_app.repositories.RoleRepository;
import com.example.sdr_app.repositories.UserRepository;
import com.example.sdr_app.services.UserDetailsCustom;
import com.example.sdr_app.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsCustom userDetails;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) throws CustomNotFoundException {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (CustomNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        userService.update(userId, updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userService.delete(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (CustomNotFoundException ex) {
            return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/load/{username}")
    public ResponseEntity<String> loadUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.loadUsername(username));
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userDetails.loadUserByUsername(loginRequest.getUsername());
        if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok()
                    .body(new Response(user, jwtUtils.generateToken(user.getUsername(), user.getRole().getName())));
        }
        return ResponseEntity.badRequest().body("Username or Password invalide check them please");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        Optional<User> user1 = repository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (user1.isPresent()) {
            throw new EntityNotFoundException("founded");
        }
        User newUser = User.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .password(encoder.encode(user.getPassword()))
                .username(user.getUsername())
                .email(user.getEmail()).build();

        Optional<Role> role = roleRepository.findById(user.getRole().getId());
        newUser.setRole(role.get());
        userService.add(newUser);
        return ResponseEntity.ok(newUser);
    }



}