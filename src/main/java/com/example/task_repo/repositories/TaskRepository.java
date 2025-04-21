package com.example.task_repo.repositories;

import com.example.task_repo.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(long id);
}
