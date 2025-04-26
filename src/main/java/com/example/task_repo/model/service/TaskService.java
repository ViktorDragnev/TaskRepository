package com.example.task_repo.model.service;


import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.request.TaskRequestDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto addReportToTaskById(long id, ReportRequestDto reportRequestDto);

    TaskResponseDto deleteTask(long id);

    TaskResponseDto updateTask(long id,TaskRequestDto taskRequestDto);

    TaskResponseDto getTaskById(long id);

    List<TaskResponseDto> getAllTasks();
}
