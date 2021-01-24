package com.mlinvest.construction.persistence.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import static com.mlinvest.construction.persistence.model.EntityConstants.Issuer.MAX_NAME_LENGTH;
import static com.mlinvest.construction.persistence.model.EntityConstants.Issuer.MIN_NAME_LENGTH;

@Entity
@Getter
public class Issuer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    private String name;

    @OneToMany(mappedBy = "issuer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tender> tenders;

    public Issuer() {
    }

    protected Issuer(String name) {
        this.name = name;
    }

    public static Issuer of(String name) {
        return new Issuer(name);
    }
}
