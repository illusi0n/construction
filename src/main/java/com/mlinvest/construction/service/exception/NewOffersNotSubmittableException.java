package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class NewOffersNotSubmittableException extends Exception {
    private final Long tenderId;

    public NewOffersNotSubmittableException(Long tenderId) {
        this.tenderId = tenderId;
    }
}
