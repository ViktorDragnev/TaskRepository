package com.example.task_repo.model.service;


import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;

import java.util.List;

public interface ReportService {

    ReportResponseDto addReport(long id, ReportRequestDto reportRequestDto);

    ReportResponseDto updateReport(long id, ReportRequestDto reportRequestDto);

    ReportResponseDto deleteById(long id);

    ReportResponseDto getById(long id);

    List<ReportResponseDto> getAllReports();
}
