package com.example.task_repo.model.service.impl;


import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.request.TaskRequestDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;
import com.example.task_repo.model.entity.Report;
import com.example.task_repo.model.entity.Task;
import com.example.task_repo.model.mapper.CustomMapper;
import com.example.task_repo.model.mapper.TaskMapper;
import com.example.task_repo.model.service.TaskService;
import com.example.task_repo.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private CustomMapper customMapper;

    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        Task task = taskRepository.save(TaskMapper.requestToTask(taskRequestDto));
        return TaskMapper.entityToResponse(task);
    }

    @Override
    public TaskResponseDto addReportToTaskById(long id, ReportRequestDto reportRequestDto) {
        Task task = taskRepository.findById(id).get();
        task.getReports().add(new Report(reportRequestDto.getContent(), reportRequestDto.getWorkTime()));
        taskRepository.save(task);
        return TaskMapper.entityToResponse(task);
    }

    @Override
    public TaskResponseDto deleteTask(long id) {
        Optional<Task> task = taskRepository.findById(id);
        taskRepository.delete(task.get());
        return TaskMapper.entityToResponse(task.get());
    }

    @Override
    public TaskResponseDto updateTask(long id, TaskRequestDto taskRequestDto) {

        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()) {
            if(taskRequestDto.getSummary() != null) {
                task.get().setSummary(taskRequestDto.getSummary());
            }
            if(taskRequestDto.getDescription() != null) {
                task.get().setDescription(taskRequestDto.getDescription());
            }
            if(taskRequestDto.getDeadline() != null) {
                task.get().setDeadline(taskRequestDto.getDeadline());
            }
        }

        taskRepository.save(task.get());
        return TaskMapper.entityToResponse(task.get());
    }

    @Override
    public TaskResponseDto getTaskById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return TaskMapper.entityToResponse(task.get());
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        for (Task task : taskList) {
            TaskResponseDto taskResponseDto = TaskMapper.entityToResponse(task);
            taskResponseDtoList.add(taskResponseDto);
        }
        return taskResponseDtoList;
    }

    @Override
    public List<TaskResponseDto> taskList() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        for(Task task : taskList){
            TaskResponseDto taskDto = customMapper.mapTaskToResponse(task);
            taskResponseDtoList.add(taskDto);
        }
        return taskResponseDtoList;
    }
}
