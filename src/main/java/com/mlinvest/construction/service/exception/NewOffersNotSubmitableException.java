package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class NewOffersNotSubmitableException extends Exception {
    private final Long tenderId;

    public NewOffersNotSubmitableException(Long tenderId) {
        this.tenderId = tenderId;
    }
}
