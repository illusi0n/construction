package com.mlinvest.construction.controller.response;

import lombok.Getter;

@Getter
public class ApiErrorDto {

    private final String message;

    private final String errorCode;

    public ApiErrorDto(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
