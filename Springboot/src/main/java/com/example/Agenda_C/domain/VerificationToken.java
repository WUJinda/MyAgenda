package com.example.Agenda_C.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "verification_token")
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class VerificationToken {
    private static final int EXPIRATION = 60 * 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken(String token, User user, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.user = user;
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    // standard constructors, getters and setters

}
