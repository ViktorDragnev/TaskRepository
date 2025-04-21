package com.example.task_repo.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorDetail {
    private LocalDateTime time;
    private String message;
}