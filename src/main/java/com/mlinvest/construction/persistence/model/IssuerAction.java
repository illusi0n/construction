package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class IssuerAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bidder_id", nullable = false)
    private Tender tender;

    // this should be under AcceptOfferIssuerAction
    @OneToOne
    private Offer offerToAccept;

    public IssuerAction() {
    }

    public IssuerAction(ActionType actionType, ActionStatus actionStatus, Tender tender, Offer offerToAccept) {
        this.actionType = actionType;
        this.actionStatus = actionStatus;
        this.tender = tender;
        this.offerToAccept = offerToAccept;
    }

    public static IssuerAction of(ActionType actionType, ActionStatus actionStatus, Tender tender, Offer offerToAccept) {
        return new IssuerAction(
                actionType,
                actionStatus,
                tender,
                offerToAccept
        );
    }
}
