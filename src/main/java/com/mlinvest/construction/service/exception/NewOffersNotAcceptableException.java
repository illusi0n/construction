package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class NewOffersNotAcceptableException extends Exception {
    private final Long tenderId;

    public NewOffersNotAcceptableException(Long tenderId) {
        this.tenderId = tenderId;
    }
}
