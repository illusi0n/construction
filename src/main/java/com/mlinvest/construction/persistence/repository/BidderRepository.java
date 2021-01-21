package com.mlinvest.construction.persistence.repository;

import com.mlinvest.construction.persistence.model.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder, Long> {
}
