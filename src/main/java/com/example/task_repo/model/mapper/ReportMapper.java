package com.example.task_repo.model.mapper;

import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.dto.response.ResponseForReports.TaskSimpleResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public static Report reportRequestDtoToReportEntity(ReportRequestDto reportRequestDto) {
        return new Report(
                reportRequestDto.getContent(),
                reportRequestDto.getWorkTime()
        );
    }

    public static ReportResponseDto reportToResponse(Report report) {
        return new ReportResponseDto(
                report.getId(),
                report.getContent(),
                report.getWorkTime(),
                report.getDateCreated(),
                report.getDateUpdated(),
                entityToSimpleResponse(report.getTask())
        );
    }

    public static TaskSimpleResponseDto entityToSimpleResponse(Task task) {
        return new TaskSimpleResponseDto(
                task.getId(),
                task.getSummary(),
                task.getDescription(),
                task.getDeadline()
        );
    }}
