package com.example.task_repo.model.dto.response.ResponseForReports;

import com.example.task_repo.model.entity.Task;
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
public class ReportResponseDto {
    private long id;
    private String content;
    private LocalTime workTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private TaskSimpleResponseDto task;
}