package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class IssuerNotFoundException extends Exception {

    private final Long issuerId;

    public IssuerNotFoundException(Long issuerId) {
        this.issuerId = issuerId;
    }
}
