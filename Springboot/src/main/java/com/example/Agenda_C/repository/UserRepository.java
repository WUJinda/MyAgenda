package com.example.Agenda_C.repository;

import com.example.Agenda_C.domain.Role;
import com.example.Agenda_C.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTokenSignature(String token);
    boolean findByEmail(String email);
    List<User> findByManagerId(Long id);
    List<User> findByRoleId(Long id);
}
