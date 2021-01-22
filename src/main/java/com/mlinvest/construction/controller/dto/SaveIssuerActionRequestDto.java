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
}
