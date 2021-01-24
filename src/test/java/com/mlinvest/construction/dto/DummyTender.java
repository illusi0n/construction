package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderStatus;

public class DummyTender extends Tender {

    public DummyTender(Long id, String description, Issuer issuer, TenderStatus status) {
        super(description, issuer, status);
        this.id = id;
    }

    public static DummyTender of(Long id, String description, Issuer issuer, TenderStatus status) {
        return new DummyTender(
                id,
                description,
                issuer,
                status
        );
    }
}
