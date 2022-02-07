package com.example.Agenda_C.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "time")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date_start")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    @Column(name = "date_of_project")
    private String dateOfProject;

    public Time(LocalDateTime date_start,
                LocalDateTime date_end,
                String date_of_project,
                User user, Project project) {
        this.dateStart = date_start;
        this.dateEnd = date_end;
        this.dateOfProject = date_of_project;
        this.user = user;
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
