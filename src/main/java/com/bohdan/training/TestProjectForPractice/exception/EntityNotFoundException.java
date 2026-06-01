package com.bohdan.training.TestProjectForPractice.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    // Удобный конструктор с id
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }

    public EntityNotFoundException(Long id) {
        this("Entity", id);
    }
}