package com.example.Agenda_C.service;

import com.example.Agenda_C.domain.VerificationToken;
import com.example.Agenda_C.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public void saveConfirmationToken(VerificationToken token){
        verificationTokenRepository.save(token);
    }
    public VerificationToken getToken(String token) throws IllegalAccessError{
        return verificationTokenRepository.findByToken(token);
    }
    public void setConfirmedAt(VerificationToken token){
        token.setConfirmedAt(LocalDateTime.now());
        verificationTokenRepository.save(token);
    }
}
