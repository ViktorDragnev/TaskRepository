package com.example.task_repo.model.mapper;

import com.example.task_repo.model.dto.request.TaskRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.dto.response.ResponseForReports.TaskSimpleResponseDto;
import com.example.task_repo.model.dto.response.responseForTasks.ReportSimpleResponseDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.entity.Task;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TaskMapper {

    public static Task requestToTask(TaskRequestDto taskRequestDto) {
        return new Task(
                taskRequestDto.getSummary(),
                taskRequestDto.getDescription(),
                taskRequestDto.getDeadline());
    }

    public static TaskResponseDto entityToResponse(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getSummary(),
                task.getDescription(),
                task.getDeadline(),
                reportEntityListToResponseDto(task.getReports())
        );
    }

    private static Set<ReportSimpleResponseDto> reportEntityListToResponseDto(Set<Report> reportList) {
        Set<ReportSimpleResponseDto> reportResponseDtoSet = new HashSet<>();
        for(Report report : reportList) {
            ReportSimpleResponseDto reportResponseDto = new ReportSimpleResponseDto(
                    report.getId(),
                    report.getContent(),
                    report.getWorkTime(),
                    report.getDateCreated(),
                    report.getDateUpdated()
            );
            reportResponseDtoSet.add(reportResponseDto);
        }
        return reportResponseDtoSet;
    }
}
