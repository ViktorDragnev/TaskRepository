package com.example.task_repo.model.dto.request;


import com.example.task_repo.model.validator.AfterMidnight;
import com.example.task_repo.model.validator.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ValidDateRange
public class FilterReportDto {
    @NotNull(message = "Time is required")
    @AfterMidnight
    private LocalTime time;

    @NotNull(message = "Date from is required")
    private LocalDateTime from;

    @NotNull(message = "Date to is required")
    private LocalDateTime to;
}
