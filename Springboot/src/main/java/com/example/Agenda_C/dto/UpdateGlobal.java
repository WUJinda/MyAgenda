package com.example.Agenda_C.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class UpdateGlobal {
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;
    private Long managerId;
    private Long roleId;
}
