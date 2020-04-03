package com.koro.notes.exceptions;

import com.koro.notes.controller.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseMessage noteNotFoundExceptionHandler(NoteNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }
}
