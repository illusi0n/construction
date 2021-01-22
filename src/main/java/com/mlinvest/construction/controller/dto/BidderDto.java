package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Bidder;
import lombok.Getter;

@Getter
public class BidderDto {

    private final Long id;
    private final String name;

    public BidderDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BidderDto of(Bidder bidder) {
        return new BidderDto(
                bidder.getId(),
                bidder.getName()
        );
    }
}
