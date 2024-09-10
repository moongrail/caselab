package com.caselab.main.exception.handler;

import com.caselab.main.dto.ApiError;
import com.caselab.main.exception.FileNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class FileExceptionHandler {
    @ExceptionHandler(FileNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError handleFileNotExistException(FileNotExistException ex) {

        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .reason(ex.getCause() != null ? ex.getCause().toString() : ex.getMessage())
                .message(ex.getMessage())
                .timestamp(now())
                .build();
    }
}
