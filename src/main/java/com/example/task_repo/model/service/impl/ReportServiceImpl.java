package com.example.task_repo.model.service.impl;

import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.mapper.ReportMapper;
import com.example.task_repo.model.service.ReportService;
import com.example.task_repo.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
}
