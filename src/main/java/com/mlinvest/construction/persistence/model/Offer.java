package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = EntityConstants.Offer.MIN_NAME_LENGTH, max = EntityConstants.Offer.MAX_NAME_LENGTH)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bidder_id", nullable = false)
    private Bidder bidder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tender_id", nullable = false)
    private Tender tender;

    public Offer() {
    }

    public Offer(String description, Bidder bidder, Tender tender) {
        this.description = description;
        this.bidder = bidder;
        this.tender = tender;
    }

    public static Offer of(String description, Bidder bidder, Tender tender) {
        return new Offer(description, bidder, tender);
    }
}
