package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.TenderResult;
import lombok.Getter;

@Getter
public class TenderResultDto {

    private final Long id;
    private final OfferDto acceptedOffer;
    private final TenderDto tender;

    public TenderResultDto(Long id, OfferDto acceptedOffer, TenderDto tender) {
        this.id = id;
        this.acceptedOffer = acceptedOffer;
        this.tender = tender;
    }

    public static TenderResultDto of(TenderResult tenderResult) {
        return new TenderResultDto(
                tenderResult.getId(),
                OfferDto.of(tenderResult.getAcceptedOffer()),
                TenderDto.of(tenderResult.getTender())
        );
    }
}
