package com.mlinvest.construction.service.exception;

public class IssuerNotFoundException extends Exception {

    private final Long issuerId;

    public IssuerNotFoundException(Long issuerId) {
        this.issuerId = issuerId;
    }
}
