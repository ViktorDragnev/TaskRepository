package com.example.task_repo.model.dto.response.ResponseForReports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskSimpleResponseDto {
    private long id;
    private String summary;
    private String description;
    private LocalDateTime deadline;
}
