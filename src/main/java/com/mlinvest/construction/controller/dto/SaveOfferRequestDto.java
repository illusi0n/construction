package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.EntityConstants;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class SaveOfferRequestDto {

    @Size(min = EntityConstants.Offer.MIN_NAME_LENGTH, max = EntityConstants.Offer.MAX_NAME_LENGTH)
    private String description;

    private Long bidderId;

    private Long tenderId;

    public SaveOfferRequestDto(String description, Long bidderId, Long tenderId) {
        this.description = description;
        this.bidderId = bidderId;
        this.tenderId = tenderId;
    }

    public SaveOfferRequestDto() {
    }

    public static SaveOfferRequestDto of(String description, Long bidderId, Long tenderId) {
        return new SaveOfferRequestDto(
                description,
                bidderId,
                tenderId
        );
    }
}