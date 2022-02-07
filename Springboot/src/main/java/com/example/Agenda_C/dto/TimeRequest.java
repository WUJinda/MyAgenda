package com.example.Agenda_C.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TimeRequest {
    private LocalDateTime date_start;
    private LocalDateTime date_end;
    private Long projectId;
}
