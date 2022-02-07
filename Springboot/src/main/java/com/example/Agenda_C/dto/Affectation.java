package com.example.Agenda_C.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Affectation {
    private Long managerId;
    private Long projectId;
}
