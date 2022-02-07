package com.example.Agenda_C.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    public Project(String title, String description, User manager) {
        this.title = title;
        this.description = description;
        this.manager = manager;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
