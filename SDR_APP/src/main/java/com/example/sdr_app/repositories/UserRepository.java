package com.example.sdr_app.repositories;

import com.example.sdr_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

    @Query("Select u From User u where u.username = ?1 or u.email = ?2")
    public Optional<User> findByUsernameOrEmail(String username, String email);
}
