package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.OfferStatus;
import lombok.Getter;

@Getter
public class OfferDto {
    private final Long id;
    private final String description;
    private final TenderDto tender;
    private final BidderDto bidder;
    private final OfferStatus status;

    public OfferDto(Long id, String description, TenderDto tender, BidderDto bidder, OfferStatus status) {
        this.id = id;
        this.description = description;
        this.tender = tender;
        this.bidder = bidder;
        this.status = status;
    }

    public static OfferDto of(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getDescription(),
                TenderDto.of(offer.getTender()),
                BidderDto.of(offer.getBidder()),
                offer.getStatus()
        );
    }
}