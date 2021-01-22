package com.mlinvest.construction.service.exception;

public class IssuerNotFoundException extends Exception {

    private Long issuerId;

    public IssuerNotFoundException(Long issuerId) {
        this.issuerId = issuerId;
    }
}
