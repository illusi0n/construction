package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = EntityConstants.Tender.MIN_NAME_LENGTH, max = EntityConstants.Tender.MAX_NAME_LENGTH)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issuer_id", nullable = false)
    private Issuer issuer;

    public Tender() {
    }

    public Tender(String description, Issuer issuer) {
        this.description = description;
        this.issuer = issuer;
    }

    public static Tender of(String description, Issuer issuer) {
        return new Tender(description, issuer);
    }
}
