package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.*;

import java.time.Instant;

public class DummyIssuerAction extends IssuerAction {

    public DummyIssuerAction(ActionType type, ActionStatus status, Tender tender, Offer offerToAccept, Instant createdAt,
                             Long id) {
        super(type, status, tender, offerToAccept, createdAt);
        this.id = id;
    }

    public static DummyIssuerAction of(ActionType type, ActionStatus status, Tender tender, Offer offerToAccept, Instant createdAt,
                                       Long id) {
        return new DummyIssuerAction(
                type,
                status,
                tender,
                offerToAccept,
                createdAt,
                id
        );
    }
}
