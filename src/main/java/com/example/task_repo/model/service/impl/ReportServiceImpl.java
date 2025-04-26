package com.example.task_repo.model.service.impl;

import com.example.task_repo.model.dto.request.FilterReportDto;
import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.dto.response.responseForTasks.ReportSimpleResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.entity.Task;
import com.example.task_repo.model.mapper.ReportMapper;
import com.example.task_repo.model.service.ReportService;
import com.example.task_repo.model.service.TaskService;
import com.example.task_repo.repositories.ReportRepository;
import com.example.task_repo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;

    public ReportServiceImpl(ReportRepository reportRepository, TaskRepository taskRepository) {
        this.reportRepository = reportRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ReportResponseDto addReport(long id, ReportRequestDto reportRequestDto) {
        Report report = new Report(reportRequestDto.getContent(), reportRequestDto.getWorkTime());
        reportRepository.save(report);
        return ReportMapper.reportToResponse(report);
    }

    @Override
    public ReportResponseDto updateReport(long id, ReportRequestDto reportRequestDto) {
        Report report = reportRepository.findById(id).get();

        if (reportRequestDto.getContent() != null) {
            report.setContent(reportRequestDto.getContent());
        }
        if(reportRequestDto.getWorkTime() != null) {
            report.setWorkTime(reportRequestDto.getWorkTime());
        }
        reportRepository.save(report);

        return ReportMapper.reportToResponse(report);
    }

    @Override
    public ReportResponseDto deleteById(long id) {
        Report report = reportRepository.findById(id).get();
        reportRepository.delete(report);
        return ReportMapper.reportToResponse(report);
    }

    @Override
    public ReportResponseDto getById(long id) {
        Report report = reportRepository.findById(id).get();
        return ReportMapper.reportToResponse(report);
    }

    @Override
    public List<ReportResponseDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportResponseDto> reportsResponseDto = new ArrayList<>();
        for (Report report : reports) {
            reportsResponseDto.add(ReportMapper.reportToResponse(report));
        }
        return reportsResponseDto;
    }

    @Override
    public List<ReportSimpleResponseDto> getReportsInInterval(FilterReportDto filterReportDto) {
        List<Report> reports = new ArrayList<>();

        for (Report report : reportRepository.findAll()) {
            if (!filterReportDto.getFrom().isAfter(report.getDateCreated())
                    && !filterReportDto.getTo().isBefore(report.getDateUpdated())
                    && (filterReportDto.getTime() == null || filterReportDto.getTime().equals(report.getWorkTime()))) {
                reports.add(report);
            }
        }

        return reports
                .stream()
                .map(ReportMapper ::reportToSimpleResponse)
                .toList();
    }

    @Override
    public List<ReportSimpleResponseDto> getReportWithMaxHours(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));

        LocalTime maxWorkTime = LocalTime.MIN;

        for(Report report : task.getReports()){
            if(maxWorkTime.compareTo(report.getWorkTime()) < 0){
                maxWorkTime = report.getWorkTime();
            }
        }

        List<Report> reports = new ArrayList<>();
        for(Report report : task.getReports()){
            if(report.getWorkTime().equals(maxWorkTime)){
                reports.add(report);
            }
        }

        return reports.stream()
                .map(ReportMapper::reportToSimpleResponse)
                .toList();
    }
}
