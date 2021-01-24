package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.ActionStatus;
import com.mlinvest.construction.persistence.model.ActionType;
import com.mlinvest.construction.persistence.model.IssuerAction;
import lombok.Getter;

import java.time.Instant;

@Getter
public class IssuerActionDto {
    private final Long id;
    private final ActionType type;
    private final ActionStatus status;
    private final TenderDto tender;
    private final OfferDto offerToAccept;
    private final Instant createdAt;

    public IssuerActionDto(Long id, ActionType type, ActionStatus status, TenderDto tender, OfferDto offerToAccept,
                           Instant createdAt) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.tender = tender;
        this.offerToAccept = offerToAccept;
        this.createdAt = createdAt;
    }

    public static IssuerActionDto of(IssuerAction issuerAction) {
        return new IssuerActionDto(
                issuerAction.getId(),
                issuerAction.getType(),
                issuerAction.getStatus(),
                TenderDto.of(issuerAction.getTender()),
                OfferDto.of(issuerAction.getOfferToAccept()),
                issuerAction.getCreatedAt()
        );
    }
}
