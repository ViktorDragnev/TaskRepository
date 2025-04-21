package com.example.task_repo.controller;


import com.example.task_repo.exception.ResourceNotFoundException;
import com.example.task_repo.model.dto.request.ReportRequestDto;
import com.example.task_repo.model.dto.request.TaskRequestDto;
import com.example.task_repo.model.dto.response.responseForTasks.TaskResponseDto;
import com.example.task_repo.model.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskRequestDto dto) {
        TaskResponseDto taskResponseDto = taskService.addTask(dto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> create(@PathVariable(name = "id") long taskId, @Valid @RequestBody ReportRequestDto dto) {
        TaskResponseDto taskResponseDto = taskService.addReportToTaskById(taskId, dto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponseDto>> getAll() throws ResourceNotFoundException {

        if(taskService.getAllTasks().isEmpty()){
            throw new ResourceNotFoundException(0, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        List<TaskResponseDto> taskResponseDtos = taskService.getAllTasks();
        return new ResponseEntity<>(taskResponseDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/v2")
    public ResponseEntity<List<TaskResponseDto>> getListV2() throws ResourceNotFoundException {

        if(taskService.getAllTasks().isEmpty()){
            throw new ResourceNotFoundException(0, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        List<TaskResponseDto> taskResponseDtos = taskService.taskList();
        return new ResponseEntity<>(taskResponseDtos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> get(@Valid @PathVariable Long id) throws ResourceNotFoundException {

        if(taskService.getTaskById(id) == null){
            throw new ResourceNotFoundException(id, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        TaskResponseDto taskResponseDto = taskService.getTaskById(id);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id,@Valid  @RequestBody TaskRequestDto dto) throws ResourceNotFoundException {

        if(taskService.getTaskById(id) == null) {
            throw new ResourceNotFoundException(id, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        TaskResponseDto taskResponseDto = taskService.updateTask(id, dto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<TaskResponseDto> delete(@Valid @PathVariable Long id) throws ResourceNotFoundException {

        if(taskService.getTaskById(id) == null) {
            throw new ResourceNotFoundException(id, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        }

        TaskResponseDto taskResponseDto = taskService.deleteTask(id);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }
}