package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
