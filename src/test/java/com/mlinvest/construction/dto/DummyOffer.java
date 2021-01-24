package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.Bidder;
import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.OfferStatus;
import com.mlinvest.construction.persistence.model.Tender;

public class DummyOffer extends Offer {

    public DummyOffer(String description, Bidder bidder, Tender tender, OfferStatus status, Long id) {
        super(description, bidder, tender, status);
        this.id = id;
    }

    public static DummyOffer of(String description, Bidder bidder, Tender tender, OfferStatus status, Long id) {
        return new DummyOffer(
                description,
                bidder,
                tender,
                status,
                id
        );
    }
}