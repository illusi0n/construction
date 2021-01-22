package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.util.Mapper;
import lombok.Getter;

import java.util.List;

@Getter
public class TendersDto {
    private final List<TenderDto> tenders;

    public TendersDto(List<TenderDto> tenders) {
        this.tenders = tenders;
    }

    public static TendersDto of(List<Tender> tenders) {
        return new TendersDto(
                Mapper.TendersMapper.from(tenders)
        );
    }
}
