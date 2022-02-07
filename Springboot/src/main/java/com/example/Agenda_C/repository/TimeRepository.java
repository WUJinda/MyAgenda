package com.example.Agenda_C.repository;

import com.example.Agenda_C.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeRepository extends JpaRepository<Time,Long> {
    List<Time> findAllByUserIdAndDateOfProject(Long currentUser_id, String date);

    List<Time> findAllByUserId(Long id);

    List<Time> findAllByProjectId(Long id);
    //List<Time> findAllByUserId_Date_Of_Project(Long currentUser_id, String date);
}


