package com.example.task_repo.model.dto.response.responseForTasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportSimpleResponseDto {
    private long id;
    private String content;
    private LocalTime workTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
