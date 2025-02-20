package com.bia.todolist.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolationException extends EntityNotFoundException {
    public DataBindingViolationException(String message) {
        super(message);
    }
}
