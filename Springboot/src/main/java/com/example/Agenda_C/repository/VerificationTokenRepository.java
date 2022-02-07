package com.example.Agenda_C.repository;

import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
