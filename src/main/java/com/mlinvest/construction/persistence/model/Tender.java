package com.mlinvest.construction.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToMany(mappedBy = "tender", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Offer> offers;

    @OneToOne(mappedBy = "tender", fetch = FetchType.LAZY, optional = false)
    private TenderResult result;

    @Setter
    private TenderStatus status;

    public Tender() {
    }

    public Tender(String description, Issuer issuer, TenderStatus status) {
        this.description = description;
        this.issuer = issuer;
        this.status = status;
    }

    public static Tender of(String description, Issuer issuer, TenderStatus status) {
        return new Tender(description, issuer, status);
    }
}
