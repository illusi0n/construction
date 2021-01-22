package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Offer;
import lombok.Getter;

@Getter
public class OfferDto {
    private final Long id;
    private final String description;
    private final TenderDto tender;
    private final BidderDto bidder;

    public OfferDto(Long id, String description, TenderDto tender, BidderDto bidder) {
        this.id = id;
        this.description = description;
        this.tender = tender;
        this.bidder = bidder;
    }

    public static OfferDto of(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getDescription(),
                TenderDto.of(offer.getTender()),
                BidderDto.of(offer.getBidder())
        );
    }
}
