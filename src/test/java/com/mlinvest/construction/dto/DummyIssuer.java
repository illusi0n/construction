package com.mlinvest.construction.dto;

import com.mlinvest.construction.persistence.model.Issuer;

public class DummyIssuer extends Issuer {

    public DummyIssuer(String name, Long id) {
        super(name);
        this.id = id;
    }

    public static DummyIssuer of(String name, Long id) {
        return new DummyIssuer(name, id);
    }
}
