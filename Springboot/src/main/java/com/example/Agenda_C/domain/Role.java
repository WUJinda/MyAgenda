package com.example.Agenda_C.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String label;


}
