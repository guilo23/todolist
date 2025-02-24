package com.bia.todolist.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolationException extends EntityNotFoundException {
    public DataBindingViolationException(String message) {
        super(message);
    }
}
