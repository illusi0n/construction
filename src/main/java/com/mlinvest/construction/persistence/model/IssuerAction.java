package com.mlinvest.construction.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
public class IssuerAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private ActionType type;

    @Enumerated(EnumType.STRING)
    @Setter
    private ActionStatus status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bidder_id", nullable = false)
    private Tender tender;

    // this should be under AcceptOfferIssuerAction
    @OneToOne(fetch = FetchType.EAGER)
    private Offer offerToAccept;

    private Instant createdAt;

    public IssuerAction() {
    }

    public IssuerAction(ActionType type, ActionStatus status, Tender tender, Offer offerToAccept,
                        Instant createdAt) {
        this.type = type;
        this.status = status;
        this.tender = tender;
        this.offerToAccept = offerToAccept;
        this.createdAt = createdAt;
    }

    public static IssuerAction of(ActionType type, ActionStatus status, Tender tender, Offer offerToAccept,
                                  Instant createdAt) {
        return new IssuerAction(
                type,
                status,
                tender,
                offerToAccept,
                createdAt
        );
    }
}
