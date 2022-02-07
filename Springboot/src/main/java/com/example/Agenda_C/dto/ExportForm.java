package com.example.Agenda_C.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExportForm {
    private Long userId;
    private String date;
}
