package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
    List<Tender> findByIssuer(Issuer issuer);
}
