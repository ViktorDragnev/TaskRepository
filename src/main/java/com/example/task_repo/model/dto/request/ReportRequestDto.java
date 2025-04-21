package com.example.task_repo.model.dto.request;

import com.example.task_repo.model.validator.AfterMidnight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class ReportRequestDto {
    @NotBlank(message = "Content is required") //Добавено в лабораторно упражнение 8
    @Size(min = 10, max = 2500, message = "Content must be at least 10 characters and no more than 2500") //Добавено в лабораторно упражнение 8
    private String content;

    @NotNull(message = "WorkTime is required") //Добавено в лабораторно упражнение 8
    @AfterMidnight //Добавено в лабораторно упражнение 8
    private LocalTime workTime;
}