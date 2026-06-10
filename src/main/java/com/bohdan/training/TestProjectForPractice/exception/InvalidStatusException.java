package com.bohdan.training.TestProjectForPractice.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String message) {
        super(message);
    }

    public InvalidStatusException(String entityName, Long entityId, String statusName) {
        super(entityName + " with id " + entityId +
                " has invalid status for this operation: " + statusName);
    }

    public InvalidStatusException(String entityName, Long entityId, String statusName, String operation) {
        super(entityName + " with id " + entityId +
                " has invalid status '" + statusName +
                "' for operation: " + operation);
    }
}
