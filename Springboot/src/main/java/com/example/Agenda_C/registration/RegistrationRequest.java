package com.example.Agenda_C.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long managerId;
    private Long roleId;
}
