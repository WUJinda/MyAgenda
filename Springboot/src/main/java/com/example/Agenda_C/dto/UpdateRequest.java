package com.example.Agenda_C.dto;

import lombok.Data;

@Data
public class UpdateRequest {
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
}
