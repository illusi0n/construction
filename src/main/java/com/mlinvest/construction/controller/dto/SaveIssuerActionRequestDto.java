package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.ActionType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SaveIssuerActionRequestDto {
    @NotNull
    private ActionType actionType;

    @NotNull
    private Long tenderId;

    @NotNull
    private Long offerToAcceptId;

    public SaveIssuerActionRequestDto(ActionType actionType, Long tenderId, Long offerToAcceptId) {
        this.actionType = actionType;
        this.tenderId = tenderId;
        this.offerToAcceptId = offerToAcceptId;
    }

    public SaveIssuerActionRequestDto() {
    }

    public static SaveIssuerActionRequestDto of(ActionType actionType, Long tenderId, Long offerToAcceptId) {
        return new SaveIssuerActionRequestDto(
                actionType,
                tenderId,
                offerToAcceptId
        );
    }
}
