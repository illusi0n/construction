package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class IssuerActionNotFoundException extends Exception {
    private final Long issuerActionId;

    public IssuerActionNotFoundException(Long issuerActionId) {
        this.issuerActionId = issuerActionId;
    }
}
