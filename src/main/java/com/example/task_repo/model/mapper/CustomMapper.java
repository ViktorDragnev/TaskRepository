package com.example.task_repo.model.mapper;

import com.example.task_repo.model.dto.response.ResponseForReports.ReportResponseDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomMapper {

    private final ModelMapper modelMapper;

    public CustomMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private void configureMappings() {
        modelMapper.typeMap(Task.class, TaskResponseDto.class)
                .addMappings(mapper -> {
                    mapper.map(Task::getId, TaskResponseDto::setId);
                    mapper.map(Task::getSummary, TaskResponseDto::setSummary);
                    mapper.map(Task::getDescription, TaskResponseDto::setDescription);
                    mapper.map(Task::getDeadline, TaskResponseDto::setDeadline);

                    mapper.map(src -> src.getReports() != null ? src.getReports().stream().map(report -> {
                                ReportResponseDto reportDto = new ReportResponseDto();
                                reportDto.setId(report.getId());
                                reportDto.setContent(report.getContent());
                                reportDto.setWorkTime(report.getWorkTime());
                                reportDto.setDateCreated(report.getDateCreated());
                                reportDto.setDateUpdated(report.getDateUpdated());
                                return reportDto;
                            }).collect(Collectors.toSet()) : null,
                            TaskResponseDto::setReports);
                });

        modelMapper.typeMap(Report.class, ReportResponseDto.class)
                .addMappings(mapper -> {
                    mapper.map(Report::getId, ReportResponseDto::setId);
                    mapper.map(Report::getContent, ReportResponseDto::setContent);
                    mapper.map(Report::getWorkTime, ReportResponseDto::setWorkTime);
                    mapper.map(Report::getDateCreated, ReportResponseDto::setDateCreated);
                    mapper.map(Report::getDateUpdated, ReportResponseDto::setDateUpdated);

                    mapper.skip(ReportResponseDto::setTask);
                });
    }

    public TaskResponseDto mapTaskToResponse(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }
}