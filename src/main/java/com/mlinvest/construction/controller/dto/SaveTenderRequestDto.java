package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.EntityConstants;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class SaveTenderRequestDto {

    private Long issuerId;

    @Size(min = EntityConstants.Tender.MIN_NAME_LENGTH, max = EntityConstants.Tender.MAX_NAME_LENGTH)
    private String description;
}
