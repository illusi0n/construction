package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.util.Mapper;
import lombok.Getter;

import java.util.List;

@Getter
public class OffersDto {
    private final List<OfferDto> offers;

    public OffersDto(List<OfferDto> offers) {
        this.offers = offers;
    }

    public static OffersDto of(List<Offer> offers) {
        return new OffersDto(
                Mapper.OfferMapper.from(offers)
        );
    }
}
