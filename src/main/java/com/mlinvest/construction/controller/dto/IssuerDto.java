package com.mlinvest.construction.controller.dto;

import com.mlinvest.construction.persistence.model.Issuer;
import lombok.Getter;

@Getter
public class IssuerDto {
    private final Long id;
    private final String name;

    public IssuerDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IssuerDto of(Issuer issuer) {
        return new IssuerDto(
                issuer.getId(),
                issuer.getName()
        );
    }
}
