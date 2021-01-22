package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Getter
public class TenderResult {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Tender tender;

    @OneToOne
    private Offer acceptedOffer;

    public TenderResult(Tender tender, Offer acceptedOffer) {
        this.tender = tender;
        this.acceptedOffer = acceptedOffer;
    }

    public static TenderResult of(Tender tender, Offer acceptedOffer) {
        return new TenderResult(tender, acceptedOffer);
    }
}
