package com.example.Agenda_C.repository;

import com.example.Agenda_C.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByLabel(String role);
}
