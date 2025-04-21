package com.example.task_repo.controller;

import com.example.task_repo.exception.ResourceNotFoundException;
import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;
import com.example.task_repo.model.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated //Добавено в лабораторно упражнение 8
@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /*
    @PostMapping("/task/{id}")
    public ResponseEntity<ReportResponseDto> create(@PathVariable(name = "id") long taskId, @Valid @RequestBody ReportRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReportResponseDto());
    }
    */

    @GetMapping("/task/{id}")
    public ResponseEntity<List<ReportResponseDto>> getAllByTask(@PathVariable(name = "id") long taskId) throws ResourceNotFoundException {

        if(reportService.getAllReports().isEmpty()){
            throw new ResourceNotFoundException(taskId, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> get(@PathVariable Long id) throws ResourceNotFoundException {
        if ( reportService.getById(id) == null) {
            throw new ResourceNotFoundException(id, ReportResponseDto.class);  //Добавено в лабораторно упражнение 8
        }
        return new ResponseEntity<>(reportService.getById(id), HttpStatus.OK);
    }

    /*
    @GetMapping()
    public ResponseEntity<List<ReportResponseDto>> getByWorkTimeInDateInterval(
            @Valid @ModelAttribute FilterReportDto filter //Добавено в лабораторно упражнение 8
    ) throws ResourceNotFoundException {
        if(reportService.getAllReports().isEmpty()){
            throw new ResourceNotFoundException(0, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }
    }

    @GetMapping("/task/{id}/max-hours-worked")
    public ResponseEntity<List<ReportResponseDto>> getByTaskWithMaxHoursWorked(@PathVariable(name = "id") long taskId) throws ResourceNotFoundException {
        throw new ResourceNotFoundException(taskId, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        //return ResponseEntity.ok(new ArrayList<>());
    }
    */

    @PutMapping("/{id}/update")
    public ResponseEntity<ReportResponseDto> update(@PathVariable Long id, @Valid @RequestBody ReportRequestDto dto) throws ResourceNotFoundException {

        if(reportService.getById(id) == null) {
            throw new ResourceNotFoundException(id, ReportResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        ReportResponseDto reportResponseDto = reportService.updateReport(id, dto);
        return new ResponseEntity<>(reportResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ReportResponseDto> delete(@PathVariable Long id) throws ResourceNotFoundException {
        if(reportService.getById(id) == null) {
            throw new ResourceNotFoundException(id, ReportResponseDto.class);  //Добавено в лабораторно упражнение 8
        }
        ReportResponseDto reportResponseDto = reportService.deleteById(id);
        return new ResponseEntity<>(reportResponseDto, HttpStatus.OK);
    }
}