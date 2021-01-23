package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.ActionStatus;
import com.mlinvest.construction.persistence.model.ActionType;
import com.mlinvest.construction.persistence.model.IssuerAction;
import lombok.Getter;

@Getter
public class IssuerActionDto {
    private final Long id;
    private final ActionType actionType;
    private final ActionStatus actionStatus;
    private final TenderDto tender;
    private final OfferDto offerToAccept;

    public IssuerActionDto(Long id, ActionType actionType, ActionStatus actionStatus, TenderDto tender, OfferDto offerToAccept) {
        this.id = id;
        this.actionType = actionType;
        this.actionStatus = actionStatus;
        this.tender = tender;
        this.offerToAccept = offerToAccept;
    }

    public static IssuerActionDto of(IssuerAction issuerAction) {
        return new IssuerActionDto(
                issuerAction.getId(),
                issuerAction.getType(),
                issuerAction.getStatus(),
                TenderDto.of(issuerAction.getTender()),
                OfferDto.of(issuerAction.getOfferToAccept())
        );
    }
}
