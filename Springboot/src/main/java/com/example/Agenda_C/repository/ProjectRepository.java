package com.example.Agenda_C.repository;

import com.example.Agenda_C.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByManagerId(Long id);;
}
