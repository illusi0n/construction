package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.Bidder;

public class DummyBidder extends Bidder {

    public DummyBidder(String name, Long id) {
        super(name);
        this.id = id;
    }

    public static DummyBidder of(String name, Long id) {
        return new DummyBidder(
                name,
                id
        );
    }
}
