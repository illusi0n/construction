package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class TenderNotFoundException extends Exception {
    private final Long tenderId;

    public TenderNotFoundException(Long tenderId) {
        this.tenderId = tenderId;
    }
}
