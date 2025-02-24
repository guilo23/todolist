package com.bia.todolist.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBidingException extends DataIntegrityViolationException {

    public DataBidingException(String file) {
        super(file);
    }
}
