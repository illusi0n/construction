package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class OfferNotFoundException extends Exception {
    private final Long offerId;

    public OfferNotFoundException(Long offerId) {
        this.offerId = offerId;
    }
}
