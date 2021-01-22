package com.mlinvest.construction.service.exception;

import lombok.Getter;

@Getter
public class BidderNotFoundException extends Exception {
    private final Long bidderId;

    public BidderNotFoundException(Long bidderId) {
        this.bidderId = bidderId;
    }
}
