package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderResult;

public class DummyTenderResult extends TenderResult {

    public DummyTenderResult(Tender tender, Offer acceptedOffer, Long id) {
        super(tender, acceptedOffer);
        this.id = id;
    }

    public static DummyTenderResult of(Tender tender, Offer acceptedOffer, Long id) {
        return new DummyTenderResult(
                tender,
                acceptedOffer,
                id
        );
    }
}
