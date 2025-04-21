package com.example.task_repo.model.dto.response.responseForTasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDto {
    private long id;
    private String summary;
    private String description;
    private LocalDateTime deadline;
    private Set<ReportSimpleResponseDto> reports;
}