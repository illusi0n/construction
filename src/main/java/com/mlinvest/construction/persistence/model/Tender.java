package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = EntityConstants.Tender.MIN_NAME_LENGTH, max = EntityConstants.Tender.MAX_NAME_LENGTH)
    private String description;

    public Tender() {
    }

    public Tender(String description) {
        this.description = description;
    }

    public static Tender of(String description) {
        return new Tender(description);
    }
}
