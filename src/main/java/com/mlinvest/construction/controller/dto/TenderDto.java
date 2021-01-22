package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Tender;
import lombok.Getter;

@Getter
public class TenderDto {
    private final Long id;
    private final String description;

    public TenderDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TenderDto of(Tender tender) {
        return new TenderDto(
                tender.getId(),
                tender.getDescription()
        );
    }
}
