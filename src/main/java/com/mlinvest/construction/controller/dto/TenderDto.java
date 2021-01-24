package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderStatus;
import lombok.Getter;

@Getter
public class TenderDto {
    private final Long id;
    private final String description;
    private final IssuerDto issuer;
    private final TenderStatus status;

    public TenderDto(Long id, String description, IssuerDto issuer, TenderStatus status) {
        this.id = id;
        this.description = description;
        this.issuer = issuer;
        this.status = status;
    }

    public static TenderDto of(Tender tender) {
        return new TenderDto(
                tender.getId(),
                tender.getDescription(),
                IssuerDto.of(tender.getIssuer()),
                tender.getStatus()
        );
    }
}
