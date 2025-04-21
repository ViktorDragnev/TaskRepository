package com.example.task_repo.repositories;


import com.example.task_repo.model.entity.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, Long> {

    Optional<Report> findById(long id);

    List<Report> findAll();
}
