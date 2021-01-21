package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import static com.mlinvest.construction.persistence.model.EntityConstants.Issuer.MAX_NAME_LENGTH;
import static com.mlinvest.construction.persistence.model.EntityConstants.Issuer.MIN_NAME_LENGTH;

@Entity
@Getter
public class Issuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    private String name;

    public Issuer() {
    }

    public Issuer(String name) {
        this.name = name;
    }

    public static Issuer of(String name) {
        return new Issuer(name);
    }
}
