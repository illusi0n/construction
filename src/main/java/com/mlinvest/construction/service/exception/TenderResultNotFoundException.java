package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class TenderResultNotFoundException extends Exception {
    private final Long tenderResultId;

    public TenderResultNotFoundException(Long tenderResultId) {
        this.tenderResultId = tenderResultId;
    }
}
