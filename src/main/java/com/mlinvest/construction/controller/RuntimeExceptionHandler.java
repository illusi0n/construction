package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.response.ApiErrorDto;
import com.mlinvest.construction.controller.response.RestResponder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ApiErrorDto> handleConflict(
            RuntimeException ex, WebRequest request) {
        logger.warn("Runtime exception occurred", ex);
        return RestResponder.createInternalErrorResponse();
    }
}
