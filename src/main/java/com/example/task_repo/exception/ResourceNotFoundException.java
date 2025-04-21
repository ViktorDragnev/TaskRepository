package com.example.task_repo.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(long id, Class<?> item) {
        super(String.format("Resource with id = %d from class = %s", id,  item.getName()));
    }
}