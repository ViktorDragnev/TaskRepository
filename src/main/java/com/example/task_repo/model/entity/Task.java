package com.example.task_repo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String summary;
    private String description;
    private LocalDateTime deadline;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Report> reports;

    public Task(String summary, String description, LocalDateTime deadline) {
        this.summary = summary;
        this.description = description;
        this.deadline = deadline;
        this.reports = new HashSet<>();
    }
}
