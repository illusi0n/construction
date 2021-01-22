package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Tender;
import lombok.Getter;

@Getter
public class TenderDto {
    private final Long id;
    private final String description;
    private final IssuerDto issuer;

    public TenderDto(Long id, String description, IssuerDto issuer) {
        this.id = id;
        this.description = description;
        this.issuer = issuer;
    }

    public static TenderDto of(Tender tender) {
        return new TenderDto(
                tender.getId(),
                tender.getDescription(),
                IssuerDto.of(tender.getIssuer())
        );
    }
}
