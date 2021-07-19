package com.example.velogclonebe.domain.repository;


import com.example.velogclonebe.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String user);
}
